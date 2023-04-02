package siwen.servlets;

import siwen.fruit.dao.ext.FruitDao;
import siwen.fruit.pojo.Fruit;
import siwen.utils.GetFruitInstance;
import siwen.utils.StringUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @projectName: LearnJava
 * @package: siwen.servlets
 * @className: IndexServlet
 * @author: 749291
 * @description: TODO
 * @date: 2/8/2023 10:51 PM
 * @version: 1.0
 */

@WebServlet("/fruit.do")
public class FruitService extends ViewBaseServlet {
    private FruitDao fruitDao = new FruitDao();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String operate = req.getParameter("operate");
        if (StringUtils.isNull(operate)) {
            operate = "index";
        }

        Class<FruitService> clazz = FruitService.class;
        try {
            Method toInvokeMethod = clazz.getDeclaredMethod(operate, HttpServletRequest.class, HttpServletResponse.class);
            toInvokeMethod.setAccessible(true);
            toInvokeMethod.invoke(this, req, resp);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void index(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        HttpSession session = req.getSession();
        String oper = req.getParameter("oper");
        String pageNoParameter = req.getParameter("pageNo");
        Integer pageNo = (!StringUtils.isNull(pageNoParameter)) ? Integer.valueOf(pageNoParameter) : 1;
        String keyword;

        if (!StringUtils.isNull(oper) && oper.equals("search")) {
            session.setAttribute("curPageNo", 1);
            keyword = req.getParameter("search_keyword");
            session.setAttribute("search_keyword", keyword);
        } else {
            keyword = (String) session.getAttribute("search_keyword");
        }
        if (keyword == null) {
            keyword = "";
        }
        try {
            List<Fruit> fruitList = fruitDao.queryAllLimited(keyword, pageNo);
            int totalRecords = fruitDao.getTotalRecords(keyword);
            session.setAttribute("fruit_list", fruitList);
            session.setAttribute("curPageNo", pageNo);
            session.setAttribute("totalPage", (totalRecords - 1) / 5 + 1);
            super.processTemplate("index", req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void addPre(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        super.processTemplate("add", req, resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Fruit fruit = GetFruitInstance.getFruitInstanceByHttpRequestParameters(req);
            fruitDao.insert(fruit);
            resp.sendRedirect("fruit.do");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) {
        String fruitName = req.getParameter("name");
        if (!StringUtils.isNull(fruitName)) {
            try {
                Fruit fruit = fruitDao.queryByName(fruitName);
                req.setAttribute("fruit", fruit);
                super.processTemplate("edit", req, resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) {
        Integer id = Integer.valueOf(req.getParameter("id"));

        try {
            fruitDao.deleteById(id);
            resp.sendRedirect("fruit.do");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Fruit fruit = GetFruitInstance.getFruitInstanceByHttpRequestParameters(req);
            fruitDao.updateByName(fruit);
            resp.sendRedirect("fruit.do");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}