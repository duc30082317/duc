    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Brand;
import model.Category;
import model.Product;

/**
 *
 * @author Duy
 */
public class ProductDAO extends DBContext {
    
    //ham tra ve list product
    public List<Product> getAllProduct() {
        //khoi tao list
        List<Product> list = new ArrayList<>();
        try {
            //khoi tao cau lenh query
            String sql = "SELECT * FROM Products_HE171168";
            //gui cau lenh sang sql server
            PreparedStatement statement = connection.prepareStatement(sql);
            //nhan ket qua tra ve tu sql
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product a = new Product();
                a.setId(rs.getString(1));
                a.setCategoryId(rs.getString(2));
                a.setBrandId(rs.getString(3));
                a.setName(rs.getString(4));
                a.setImage(rs.getString(5));
                a.setPrice(rs.getString(6));
                a.setStock(rs.getString(7));
                a.setCreated_date(rs.getString(8));
                a.setDescription(rs.getString(9));
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;

    }

    public List<Product> getAllProductByBrand(String brand) {
        List<Product> list = new ArrayList<>();

        try {
            String sql = "SELECT * FROM Products_HE171168 where BrandId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, brand);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product a = new Product();
                a.setId(rs.getString(1));
                a.setCategoryId(rs.getString(2));
                a.setBrandId(rs.getString(3));
                a.setName(rs.getString(4));
                a.setImage(rs.getString(5));
                a.setPrice(rs.getString(6));
                a.setStock(rs.getString(7));
                a.setCreated_date(rs.getString(8));
                a.setDescription(rs.getString(9));
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;

    }

    public List<Product> getAllProductByCategory(String category) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Products_HE171168 where CategoryId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, category);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product a = new Product();
                a.setId(rs.getString(1));
                a.setCategoryId(rs.getString(2));
                a.setBrandId(rs.getString(3));
                a.setName(rs.getString(4));
                a.setImage(rs.getString(5));
                a.setPrice(rs.getString(6));
                a.setStock(rs.getString(7));
                a.setCreated_date(rs.getString(8));
                a.setDescription(rs.getString(9));
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;

    }

    public List<Product> getAllProductByName(String name) {
        List<Product> list = new ArrayList<>();

        try {
            String sql = "SELECT * FROM Products_HE171168 where name like ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product a = new Product();
                a.setId(rs.getString(1));
                a.setCategoryId(rs.getString(2));
                a.setBrandId(rs.getString(3));
                a.setName(rs.getString(4));
                a.setImage(rs.getString(5));
                a.setPrice(rs.getString(6));
                a.setStock(rs.getString(7));
                a.setCreated_date(rs.getString(8));
                a.setDescription(rs.getString(9));
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;

    }

    public Product getProductById(int pid) {
        String sql = " select * from [prj3012].[dbo].[Products_HE171168] \n"
                + "  where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
                return p;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Category getCategoryById(int pid) {
        String sql = " select * from [prj3012].[dbo].[Categories_HE171168] \n"
                + "  where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category p = new Category(rs.getString(1), rs.getString(2));
                return p;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Brand getBrandById(int pid) {
        String sql = " select * from [prj3012].[dbo].[brands_HE171168] \n"
                + "  where [BrandId] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Brand p = new Brand(rs.getString(1), rs.getString(2));
                return p;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public ArrayList<Product> getProduct() {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "  select * from [Products_HE171168] ";
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = connection;
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getString(1), getCategoryById(rs.getInt(2)), getBrandById(rs.getInt(3)), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<Category> getCategory() {
        ArrayList<Category> list = new ArrayList<>();
        String sql = "  select * from [Categories_HE171168] ";
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = connection;
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getString(1), rs.getString(2)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    public int getSoldStockByProductId(String productId) {
    int soldStock = 0;
    String sql = "SELECT SUM(Quantity) FROM Products_HE171168 WHERE Id = ?";
    try {
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, productId);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            soldStock = rs.getInt(1);
        }
    } catch (Exception e) {
        System.out.println("Error while getting sold stock: " + e);
    }
    return soldStock;
}

    public ArrayList<Brand> getBrand() {
        ArrayList<Brand> list = new ArrayList<>();
        String sql = "  select * from [Brands_HE171168] ";
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = connection;
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Brand(rs.getString(1), rs.getString(2)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void AddProduct(String name, String price, int stock, String img, String descri, String category, String brand) {
        String sql = "INSERT INTO [dbo].[Products_HE171168]\n"
                + "           ([CategoryId]\n"
                + "           ,[BrandId]\n"
                + "           ,[Name]\n"
                + "           ,[Image]\n"
                + "           ,[Price]\n"
                + "           ,[Stock]\n"
                + "           ,[Created_date]\n"
                + "           ,[Description])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,GETDATE()\n"
                + "           ,?)";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection conn = connection;
            ps = conn.prepareStatement(sql);
            ps.setString(1, category);
            ps.setString(2, brand);
            ps.setInt(6, stock);
            ps.setString(4, img);
            ps.setString(7, descri);
            ps.setString(5, price);
            ps.setString(3, name);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void DeleteProduct(String pid) {
        String sql = "delete from products_HE171168 where id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = connection;
            ps = conn.prepareStatement(sql);
            ps.setString(1, pid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void UpdateProduct(int id, String name, String price, int stock, String img, String descri, String category, String brand) {
        String sql = "UPDATE [dbo].[Products_HE171168]\n"
                + "   SET [CategoryId] = ?\n"
                + "      ,[BrandId] = ?\n"
                + "      ,[Name] = ?\n"
                + "      ,[Image] = ?\n"
                + "      ,[Price] = ?\n"
                + "      ,[Stock] = ?\n"
                + "      ,[Description] = ?\n"
                + " WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = connection;
            ps = conn.prepareStatement(sql);
            ps.setString(1, category);
            ps.setString(2, brand);
            ps.setString(3, name);
            ps.setString(5, price);
            ps.setInt(6, stock);
            ps.setString(4, img);
            ps.setString(7, descri);
            ps.setInt(8, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public int getTotalProduct(){
        String sql ="select count(*) from Products_HE171168";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }
  public static void main(String[] args) {
    ProductDAO productDAO = new ProductDAO();
    
    // Thay đổi thông tin sản phẩm muốn cập nhật
    int productIdToUpdate = 1; // ID của sản phẩm cần cập nhật
    String newName = "Dior";
    String newPrice = "1530";
    int newStock = 100;
    String newImg = "new_image_url";
    String newDescri = "description";
    String newCategory = "1"; // ID của danh mục mới
    String newBrand = "1"; // ID của thương hiệu mới
    
    // Thực hiện cập nhật thông tin sản phẩm
    productDAO.UpdateProduct(productIdToUpdate, newName, newPrice, newStock, newImg, newDescri, newCategory, newBrand);
    
    // Kiểm tra lại thông tin của sản phẩm sau khi cập nhật
    Product updatedProduct = productDAO.getProductById(productIdToUpdate);
    if (updatedProduct != null) {
        System.out.println("Updated Product:");
        System.out.println("ID: " + updatedProduct.getId());
        System.out.println("Category ID: " + updatedProduct.getCategoryId());
        System.out.println("Brand ID: " + updatedProduct.getBrandId());
        System.out.println("Name: " + updatedProduct.getName());
        System.out.println("Image: " + updatedProduct.getImage());
        System.out.println("Price: " + updatedProduct.getPrice());
        System.out.println("Stock: " + updatedProduct.getStock());
        System.out.println("Created Date: " + updatedProduct.getCreated_date());
        System.out.println("Description: " + updatedProduct.getDescription());
        System.out.println("---------------------");
    } else {
        System.out.println("Failed to retrieve updated product information.");
    }
}

}
