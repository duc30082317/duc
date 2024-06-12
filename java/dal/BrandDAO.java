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

/**
 *
 * @author Duy
 */
public class BrandDAO extends DBContext{
    public List<Brand> getAllBrand() {
        List<Brand> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Brands_HE171168";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Brand a = new Brand();
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
