/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Brand;
import model.Customer;

/**
 *
 * @author Duy
 */
public class CustomerDAO extends DBContext {
     
    public Customer getByUserNamePassword(String username, String password) {
        try {
            String sql = "Select * from Customers_HE171168 where username = ? and password = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            //set username va password lan luot vao dau hoi cham 1 va 2 trong sql query 
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Customer account = new Customer();
                //lay gia tri cua cac cot trong kq tra ve sql va set no vao cac truong cua customer
                account.setId(rs.getString(1));
                account.setUsername(rs.getString(2));
                account.setFullname(rs.getString(3));
                account.setPassword(rs.getString(4));
                account.setPhone(rs.getString(5));
                account.setAddress(rs.getString(6));
                account.setRoleId(rs.getString(7));

                return account;
            }
            //sau khi lay xong thong tin, reseult set dong lai giai phong tai nguyen
            rs.close();
        } catch (SQLException ex) {
        }
        //khong tim thay hoac co loi, tra ve null
        return null;
    }
    public List<Customer> getAll(){
        List<Customer> list = new ArrayList<>();
        String sql="select * from Customers_HE171168";
        try{
           PreparedStatement ps = connection.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
               Customer c = new Customer(rs.getString("id"),
                       rs.getString("username"),
                       rs.getString("fullname"),
                       rs.getString("password"),
                       rs.getString("phone"),
                       rs.getString("address"),
                       rs.getString("roleId"));
               list.add(c);
           }
        }catch(SQLException e){
          System.out.println(e);  
        }
        return list;
    }

    public void inserUser(String username, String fullname, String password, String phone, String address) {
        String sql = "INSERT INTO [dbo].[Customers_HE171168]\n"
                + "           ([Username]\n"
                + "           ,[Fullname]\n"
                + "           ,[Password]\n"
                + "           ,[Phone]\n"
                + "           ,[Address]\n"
                + "           ,[RoleId])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           , 'US')";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, fullname);
            ps.setString(3, password);
            ps.setString(4, password);
            ps.setString(5, address);
            
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void DeleteCustomer(String cid) {
        String sql = "delete from Customers_HE171168 where id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = connection;
            ps = conn.prepareStatement(sql);
            ps.setString(1, cid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    public Customer getUserByID(String userID) {
    Customer customer = null;
    String sql = "SELECT * FROM Customers_HE171168 WHERE id = ?";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, userID);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            customer = new Customer();
            customer.setId(rs.getString("id"));
            customer.setUsername(rs.getString("username"));
            customer.setFullname(rs.getString("fullname"));
            customer.setPassword(rs.getString("password"));
            customer.setPhone(rs.getString("phone"));
            customer.setAddress(rs.getString("address"));
            customer.setRoleId(rs.getString("roleId"));
        }
        rs.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return customer;
}
     public Customer checkUserExit(String username) {
        try {
            String sql = "Select * from Customers_HE171168 where username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            //set username va password lan luot vao dau hoi cham 1 va 2 trong sql query 
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Customer account = new Customer();
                //lay gia tri cua cac cot trong kq tra ve sql va set no vao cac truong cua customer
                account.setId(rs.getString(1));
                account.setUsername(rs.getString(2));
                account.setFullname(rs.getString(3));
                account.setPassword(rs.getString(4));
                account.setPhone(rs.getString(5));
                account.setAddress(rs.getString(6));
                account.setRoleId(rs.getString(7));

                return account;
            }
        } catch (SQLException ex) {
        }
        //khong tim thay hoac co loi, tra ve null
        return null;
    }
public static void main(String[] args) {
    CustomerDAO customerDAO = new CustomerDAO();
    String userIDToRetrieve = "1"; // 
    Customer customer = customerDAO.getUserByID(userIDToRetrieve);
    
    if (customer != null) {
        // In ra thông tin của khách hàng nếu tìm thấy
        System.out.println("ID: " + customer.getId());
        System.out.println("Username: " + customer.getUsername());
        System.out.println("Fullname: " + customer.getFullname());
        System.out.println("Password: " + customer.getPassword());
        System.out.println("Phone: " + customer.getPhone());
        System.out.println("Address: " + customer.getAddress());
        System.out.println("RoleID: " + customer.getRoleId());
    } else {
        // Nếu không tìm thấy khách hàng, thông báo cho người dùng
        System.out.println("Không tìm thấy khách hàng với ID: " + userIDToRetrieve);
    }
}
}
