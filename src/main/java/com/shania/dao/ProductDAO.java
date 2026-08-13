package com.shania.dao;

import com.shania.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {
    //To add the product to the db it must receive the Product object as a complete set of details
    boolean createProduct(Product product) throws SQLException;
    Product getProductById(int id) throws SQLException;
    List<Product> getAllProducts() throws SQLException;
    boolean updateProduct(Product product) throws SQLException;
    boolean deleteProduct(int id) throws SQLException;
}
