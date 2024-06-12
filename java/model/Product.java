/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Duy
 */
public class Product {
    private String id;
    private String categoryId;
    private Category category;
    private Brand brand;
    private String brandId;
    private String name;
    private String image;
    private String price;
    private String stock;
    private String created_date;
    private String description;

    public Product() {
    }

    public Product(String id, String categoryId, String brandId, String name, String image, String price, String stock, String created_date, String description) {
        this.id = id;
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.name = name;
        this.image = image;
        this.price = price;
        this.stock = stock;
        this.created_date = created_date;
        this.description = description;
    }

    public Product(String id, Category category, Brand brand, String name, String image, String price, String stock, String created_date, String description) {
        this.id = id;
        this.category = category;
        this.brand = brand;
        this.name = name;
        this.image = image;
        this.price = price;
        this.stock = stock;
        this.created_date = created_date;
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", categoryId=" + categoryId + ", category=" + category.toString() + ", brand=" + brand.toString() + ", brandId=" + brandId + ", name=" + name + ", image=" + image + ", price=" + price + ", stock=" + stock + ", created_date=" + created_date + ", description=" + description + '}';
    }
    
    
    
}
