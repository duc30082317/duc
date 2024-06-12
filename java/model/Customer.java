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
public class Customer {
    private String id;
    private String username;
    private String fullname;
    private String password;
    private String phone;
    private String address;
    private String roleId;

    public Customer() {
    }

    public Customer(String id) {
        this.id = id;
    }

    public Customer(String id, String username, String fullname, String password, String phone, String address, String roleId) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.roleId = roleId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoleId() {
        return roleId.trim();
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", username=" + username + ", fullname=" + fullname + ", password=" + password + ", phone=" + phone + ", address=" + address + ", roleId=" + roleId + '}';
    }
    
    
}
