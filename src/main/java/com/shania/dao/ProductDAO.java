package com.shania.dao;

import com.shania.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {
    boolean createProduct(Product product) throws SQLException;
    Product getProductById(int id) throws SQLException;
    List<Product> getAllProducts() throws SQLException;
    boolean updateProduct(Product product) throws SQLException;
    boolean deleteProduct(int id) throws SQLException;
}
