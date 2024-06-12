/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Brand;
import model.Category;

/**
 *
 * @author Duy
 */
public class CategoryDAO extends DBContext{
    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();

        try {
            String sql = "SELECT * FROM Categories_HE171168";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Category a = new Category();
                a.setId(rs.getString(1));
                a.setName(rs.getString(2));
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;

    }
}
