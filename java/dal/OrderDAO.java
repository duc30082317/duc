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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Cart;
import model.CartItem;
import model.Customer;
import model.Order;
import model.OrderDetail;

/**
 *
 * @author Duy
 */
public class OrderDAO extends DBContext {
 public void DeleteOrder(String ooid) {
        String sql = "delete from Orders_HE171168 where OrderId = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = connection;
            ps = conn.prepareStatement(sql);
            ps.setString(1, ooid);
            ps.executeUpdate();
            
        } catch (Exception e) {
        }
    }
 public void updateStock(String productId, int quantity) {
    String updateStockSQL = "UPDATE Products_HE171168 SET Stock = Stock + ? WHERE Id = ?";
    Connection conn = null;
    PreparedStatement ps = null;
    try {
        conn = connection;
        ps = conn.prepareStatement(updateStockSQL);
        ps.setInt(1, quantity);
        ps.setString(2, productId);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (ps != null) ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
  public void DeleteOrderDetail(String odid) {
        String sql = "delete from OrderDetails_HE171168 where OrderId = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = connection;
            ps = conn.prepareStatement(sql);
            ps.setString(1, odid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
 
    private static String INSERT_ORDER = "INSERT INTO [dbo].[Orders_HE171168]\n"
            + "           ([UserId]\n"
            + "           ,[Order_date]\n"
            + "           ,[Total]\n"
            + "           ,[Notes])\n"
            + "     VALUES\n"
            + "           (?\n"
            + "           ,GETDATE()\n"
            + "           ,?\n"
            + "           ,?)";

    private static String INSERT_ORDER_DETAIL = "INSERT INTO [dbo].[OrderDetails_HE171168]\n"
            + "           ([OrderId]\n"
            + "           ,[ProductId]\n"
            + "           ,[Price]\n"
            + "           ,[Quantity])\n"
            + "     VALUES\n"
            + "           (?\n"
            + "           ,?\n"
            + "           ,?\n"
            + "           ,?)";
        private static String GETBY_USERID = "select * from [Orders_HE171168] where userid = ?";
        private static String GETODETAILBY_OID = "select * from [OrderDetails_HE171168] where orderid = ?";

    public void insertOrder(Customer u, Cart cart) throws ClassNotFoundException, SQLException {
        Connection conn = connection;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = INSERT_ORDER;
            ps = conn.prepareStatement(sql);
            ps.setString(1, u.getId());
            ps.setDouble(2, cart.getTotalMoney());
            ps.setString(3, cart.getNote());
            ps.executeUpdate();
            String sql1 = "select top 1[OrderId] from [Orders_HE171168] order by [OrderId] desc";
            ps = conn.prepareStatement(sql1);
            rs = ps.executeQuery();
            if (rs.next()) {
                int oid = rs.getInt(1);
                for (CartItem item : cart.getItems()) {
                    String sql2 = INSERT_ORDER_DETAIL;
                    ps = conn.prepareStatement(sql2);
                    ps.setInt(1, oid);
                    ps.setString(2, item.getProduct().getId());
                    ps.setString(3, item.getProduct().getPrice());
                    ps.setInt(4, item.getQuantity());
                    ps.executeUpdate();
                }
            }
            String sql3 = "update [dbo].[Products_HE171168] set [Stock] = [Stock] - ? "
                    + "where id = ?";
            ps = conn.prepareStatement(sql3);
            for (CartItem item : cart.getItems()) {
                ps.setInt(1, item.getQuantity());
                ps.setString(2, item.getProduct().getId());
                ps.executeUpdate();
            }

        } catch (Exception e) {
        }
    }
   public ArrayList<Order> getAllOrderBuyId(String uid) throws ClassNotFoundException, SQLException {
    ArrayList<Order> ol = new ArrayList<>();
    String sql = GETBY_USERID;
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, uid);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Customer customer = getCustomerById(rs.getString("UserId"));
            ol.add(new Order(rs.getString(1), customer, rs.getString(3), rs.getString(4), rs.getString(5)));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return ol;
}
   public ArrayList<Order> getAllOrdersWithCustomerInfo() throws ClassNotFoundException, SQLException {
    ArrayList<Order> orders = new ArrayList<>();
    String sql = "SELECT * FROM Orders_HE171168";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String orderId = rs.getString("OrderId");
            String userId = rs.getString("UserId");
            String orderDate = rs.getString("Order_date");
            String total = rs.getString("Total");
            String notes = rs.getString("Notes");
            CustomerDAO customerDAO = new CustomerDAO();
            Customer customer = customerDAO.getUserByID(userId);
            Order order = new Order(orderId, customer, orderDate, total, notes);
            orders.add(order);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return orders;
}

   public Customer getCustomerById(String userId) throws SQLException {
    CustomerDAO customerDAO = new CustomerDAO();
    return customerDAO.getUserByID(userId);
}
    ProductDAO dao = new ProductDAO();
    public ArrayList<OrderDetail> getAllOrderDetailByoId(String oid) throws ClassNotFoundException, SQLException {
        ArrayList<OrderDetail> odl = new ArrayList<>();
        String sql = GETODETAILBY_OID;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, oid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                odl.add(new OrderDetail(rs.getInt(1), rs.getInt(2), dao.getProductById(rs.getInt(3)), rs.getDouble(4), rs.getInt(5)));
            }
        } catch (Exception e) {
        }
        return odl;
    }
public List<Map<String, Object>> countAllProductQuantities() {
    List<Map<String, Object>> productInfos = new ArrayList<>();
    String countAllProductQuantitiesSQL = "SELECT p.Name, p.Id, p.Price, p.Stock, SUM(od.Quantity) AS TotalQuantity " +
                                          "FROM OrderDetails_HE171168 od " +
                                          "INNER JOIN Products_HE171168 p ON od.ProductId = p.Id " +
                                          "GROUP BY p.Name, p.Id, p.Price, p.Stock";
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
        conn = connection;
        ps = conn.prepareStatement(countAllProductQuantitiesSQL);
        rs = ps.executeQuery();
        while (rs.next()) {
            Map<String, Object> productInfo = new HashMap<>();
            productInfo.put("Name", rs.getString("Name"));
            productInfo.put("Id", rs.getInt("Id"));
            productInfo.put("Price", rs.getInt("Price"));
            productInfo.put("Stock", rs.getInt("Stock"));
            productInfo.put("TotalQuantity", rs.getInt("TotalQuantity"));
            productInfos.add(productInfo);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return productInfos;
}



public static void main(String[] args) {
    OrderDAO orderDAO = new OrderDAO();
    List<Map<String, Object>> allProductInfos = orderDAO.countAllProductQuantities();
    for (Map<String, Object> productInfo : allProductInfos) {
        System.out.println("Product Name: " + productInfo.get("Name"));
        System.out.println("Product ID: " + productInfo.get("Id"));
        System.out.println("Product Price: " + productInfo.get("Price"));
        System.out.println("Product Stock: " + productInfo.get("Stock"));
        System.out.println("Quantity Sold: " + productInfo.get("TotalQuantity"));
        System.out.println("----------------------------------");
    }
}

}
