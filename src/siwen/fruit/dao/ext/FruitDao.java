package siwen.fruit.dao.ext;


import siwen.fruit.dao.base.BaseDao;
import siwen.fruit.pojo.Fruit;

import java.util.List;

/**
 * @projectName: LearnJava
 * @package: com.siwen.fruit.dao.impl
 * @className: FruitDAOImpl
 * @author: 749291
 * @description: implements the add and delete db-operations of the table of fruit
 * @date: 2/7/2023 9:43 PM
 * @version: 1.0
 */

public class FruitDao extends BaseDao<Fruit> {
    public int add(Fruit fruit) throws Exception {
        String sql = "INSERT INTO fruit(name, price, inventory)\n" + "values (?, ?, ?)";
        return super.update(sql, fruit.getName(), fruit.getPrice(), fruit.getInventory());
    }

    /**
     * @param fruit the fruit to delete
     * @return the effected number of table field
     */
    public int delete(Fruit fruit) throws Exception {
        String sql = "DELETE FROM fruit\n" + "WHERE name = ?";
        return super.update(sql, fruit.getName());
    }

    public int deleteById(Integer id) throws Exception {
        String sql = "DELETE FROM fruit\n" + "WHERE id = ?";
        return super.update(sql, id);
    }

    public int updateByName(Fruit fruit) throws Exception {
        String sql = "UPDATE fruit\n" + "set name = ?, price = ?, inventory = ?, remark = ?\n" + "WHERE id = ?";

        return super.update(sql, fruit.getName(), fruit.getPrice(), fruit.getInventory(), fruit.getRemark(), fruit.getId());
    }

    public int insert(Fruit fruit) throws Exception {
        String sql = "INSERT INTO fruit(name, price, inventory, remark)\n" + "values (?, ?, ?, ?)";
        return super.update(sql, fruit.getName(), fruit.getPrice(), fruit.getInventory(), fruit.getRemark());
    }

    public List<Fruit> queryAll() throws Exception {
        String sql = "SELECT * FROM fruit";
        return super.query(sql);
    }


    public Fruit queryByName(String name) throws Exception {
        String sql = "SELECT * \n" + "FROM fruit\n" + "WHERE name = ?";

        List<Fruit> resultList = super.query(sql, name);
        if (resultList != null && !resultList.isEmpty()) {
            return resultList.get(0);
        }
        return null;
    }

    public List<Fruit> queryByPrice(Double price) throws Exception {
        String sql = "SELECT * \n" + "FROM fruit\n" + "WHERE price = ?";


        return super.query(sql, price);
    }

    public List<Fruit> queryByInventory(Integer inventory) throws Exception {
        String sql = "SELECT * \n" + "FROM fruit\n" + "WHERE inventory = ?";

        return super.query(sql, inventory);
    }

    public List<Fruit> queryAllLimited(String nameKeyword, Integer pageNo) throws Exception {
        String sql = "SELECT *\n" +
                "FROM fruit\n" +
                "WHERE name like(\"%" + nameKeyword + "%\")\n" +
                "limit ?, 5";
        return super.query(sql, (pageNo - 1) * 5);
    }

    public int getCurMaxId() throws Exception {
        String sql = "SELECT id FROM fruit";
        return super.query(sql).stream().map(Fruit::getId).max(Integer::compareTo).get();
    }

    public int getTotalRecords() throws Exception {
        return super.query("SELECT id FROM fruit").size();
    }

    public int getTotalRecords(String keyword) throws Exception {
        String sql = "SELECT id\n" +
                "FROM fruit\n" +
                "WHERE name like(\"%" + keyword + "%\")\n";
        return super.query(sql).size();
    }
}