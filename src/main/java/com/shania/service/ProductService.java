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
    public boolean validateName(String name) {
        return !name.isEmpty();
    }
    public boolean validatePrice(BigDecimal price) {
        return price.compareTo(BigDecimal.ZERO) > 0;
    }
    public boolean validateQuantity(int quantity) {
        return quantity >= 0;
    }

    public Product getExistingProduct(int id) {
        try {
            return dao.getProductById(id);
        }catch(SQLException e){
            return null;
        }
    }
    public List<Product> getAllExistingProducts(){
        try{
            return dao.getAllProducts();
        } catch(SQLException e){
            return null;
        }
    }

    public boolean createProduct(Product product){
        try {
            return dao.createProduct(product);
        } catch (SQLException e) {
            return false;
        }
    }
    public boolean updateProduct(Product product){
        try{
            return dao.updateProduct(product);
        } catch(SQLException e){
            return false;
        }
    }
    public boolean deleteProduct(int id){
        try{
            return dao.deleteProduct(id);
        }catch(SQLException e){
            return false;
        }
    }
}
