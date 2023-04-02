package siwen.fruit.dao.base;


import siwen.fruit.utils.Jdbc;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: LearnJava
 * @package: com.siwen.fruit.dao.base
 * @className: BaseDao
 * @author: 749291
 * @description: TODO
 * @date: 2/8/2023 4:59 PM
 * @version: 1.0
 */

public abstract class BaseDao<T> {
    protected int update(String sql, Object... params) throws Exception {
        Connection connection = Jdbc.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            int effectedRows = 0;
            if (preparedStatement != null) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
                effectedRows = preparedStatement.executeUpdate();
            }
            preparedStatement.close();
            if (connection.getAutoCommit()) {
                connection.close();
            }
            return effectedRows;
        }
    }

    protected List<T> query(String sql, Object... params) throws Exception {
        Connection connection = Jdbc.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            List<T> resultList = null;
            if (preparedStatement != null) {
                resultList = new ArrayList<>();
                ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
                Class actualTypeArgument = (Class) parameterizedType.getActualTypeArguments()[0];

                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }

                ResultSet resultSet = preparedStatement.executeQuery();
                ResultSetMetaData metaData = resultSet.getMetaData();

                int columnCount = metaData.getColumnCount();
                while (resultSet.next()) {
                    T t = (T) actualTypeArgument.getDeclaredConstructor().newInstance();
                    for (int i = 1; i <= columnCount; i++) {
                        String propertyName = metaData.getColumnLabel(i);
                        Object propertyValue = resultSet.getObject(i);

                        Field field = actualTypeArgument.getDeclaredField(propertyName);
                        field.setAccessible(true);
                        field.set(t, propertyValue);
                    }
                    resultList.add(t);
                }
                resultSet.close();
                preparedStatement.close();
            }
            if (connection.getAutoCommit()) {
                connection.close();
            }
            return resultList;
        }
    }

}