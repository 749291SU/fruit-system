package siwen.servlets;


import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @projectName: JavaWeb
 * @package: siwen.servlets
 * @className: ViewBaseServlet
 * @author: 749291
 * @description: TODO
 * @date: 2/9/2023 12:59 AM
 * @version: 1.0
 */

public class ViewBaseServlet extends HttpServlet {
    private TemplateEngine templateEngine;

    @Override
    public void init() {
        // 获取ServletContext对象
        ServletContext servletContext = this.getServletContext();

        // 创建Thymeleaf解析器对象
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);

        // 给解析器对象设置参数
        templateResolver.setTemplateMode(TemplateMode.HTML);

        // 设置前缀
        String viewPrefix = servletContext.getInitParameter("view-prefix");
        templateResolver.setPrefix(viewPrefix);

        String viewsuffix = servletContext.getInitParameter("view-suffix");
        templateResolver.setSuffix(viewsuffix);

        // 设置缓存过期时间
        templateResolver.setCacheTTLMs(60000L);

        // 设置是否缓存
        templateResolver.setCacheable(true);

        // 设置服务器编码方式
        templateResolver.setCharacterEncoding("utf-8");

        // 创建模板引擎对象
        templateEngine = new TemplateEngine();

        // 给模板引擎对象设置模板解析器
        templateEngine.setTemplateResolver(templateResolver);
    }

    protected void processTemplate(String templateName, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 1.设置响应体内容烈性和字符集
        resp.setContentType("text/html;charset=UTF-8");

        // 2.创建WebContext对象
        WebContext webContext = new WebContext(req, resp, getServletContext());

        // 3.处理模板数据
        templateEngine.process(templateName, webContext, resp.getWriter());
    }
}