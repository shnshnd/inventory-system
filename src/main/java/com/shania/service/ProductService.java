package com.shania.service;

import com.shania.dao.ProductDAO;
import com.shania.model.Product;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private ProductDAO dao;

    public ProductService(){}
    public ProductService(ProductDAO dao){
        this.dao = dao;
    }

    public boolean validateId(int id) {
        return id > 0;
    }
    public boolean validateName(String name) { return !name.isEmpty(); }
    public boolean validatePrice(BigDecimal price) { return price.compareTo(BigDecimal.ZERO) > 0; }
    public boolean validateQuantity(int quantity) { return quantity >= 0; }

    public Product getExistingProduct(int id) throws SQLException{
        try {
            return dao.getProductById(id);
        }catch(SQLException e){
            throw new SQLException("Database Error in Getting Existing Product", e);
        }
    }
    public List<Product> getAllExistingProducts() throws SQLException{
        try{
            return dao.getAllProducts();
        } catch(SQLException e){
            throw new SQLException("Database Error in Getting All Products", e);
        }
    }

    public boolean createProduct(Product product) throws SQLException{
        try {
            return dao.createProduct(product);
        } catch (SQLException e) {
            throw new SQLException("Database Error in Creating the Product");
        }
    }
    public boolean updateProduct(Product product) throws SQLException{
        try{
            return dao.updateProduct(product);
        } catch(SQLException e){
            throw new SQLException("Database Error in Updating the Product");
        }
    }
    public boolean deleteProduct(int id) throws SQLException{
        try{
            return dao.deleteProduct(id);
        }catch(SQLException e){
            throw new SQLException("Database Error in Deleting the Product");
        }
    }
}
