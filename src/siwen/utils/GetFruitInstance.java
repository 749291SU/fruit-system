package siwen.utils;

import siwen.fruit.pojo.Fruit;

import javax.servlet.http.HttpServletRequest;

/**
 * @projectName: JavaWeb
 * @package: siwen.utils
 * @className: GetFruitParameter
 * @author: 749291
 * @description: utils for get Fruit instance by parameter of HttpRequestServlet object
 * @date: 2/10/2023 9:38 PM
 * @version: 1.0
 */

public class GetFruitInstance {
    private GetFruitInstance() { }

    public static Fruit getFruitInstanceByHttpRequestParameters(HttpServletRequest req) {
        String idParameter = req.getParameter("id");
        Integer id = StringUtils.isNull(idParameter) ? null : Integer.parseInt(idParameter);

        String nameParameter = req.getParameter("name");
        String name = StringUtils.isNull(nameParameter) ? null : nameParameter;

        String priceParameter = req.getParameter("price");
        Double price = StringUtils.isNull(priceParameter) ? null : Double.parseDouble(priceParameter);

        String inventoryParameters = req.getParameter("inventory");
        Integer inventory = StringUtils.isNull(inventoryParameters) ? null : Integer.parseInt(inventoryParameters);

        String remark = req.getParameter("remark");
        return new Fruit(id, name, price, inventory,
                remark);
    }
}
