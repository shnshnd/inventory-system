package com.shania.ui;

import com.shania.dao.ProductDAO;
import com.shania.dao.ProductDAOImpl;
import com.shania.model.Product;
import com.shania.service.ProductService;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class ProductUI {
    private ProductDAO productDAO = new ProductDAOImpl();
    private ProductService productService = new ProductService(productDAO);

    public boolean displayVerifiedProduct(int id) throws SQLException{
        Product product = productService.getExistingProduct(id);
        if (product == null) {
            System.out.println("Product ID: " + id + " Doesn't Exist");
            return false;
        }
        else {
            System.out.println("Product ID: " + id + " Exist");
            System.out.println("Product Details: ");
            System.out.println(product);
            return true;
        }
    }
    public void displayAllProducts() throws SQLException{
        List<Product> products = productService.getAllExistingProducts();
        if (products.isEmpty()) {
            System.out.println("No Products Found");
        } else {
            for (Product product : products) {
                System.out.println("Product Details: ");
                System.out.println(product);
            }
        }
    }
    public void createProduct(String createName, BigDecimal createPrice, int createQuantity) throws SQLException{
        Product product = new Product(createName, createPrice, createQuantity);
        if (productService.createProduct(product)) System.out.println("Successfully Created");
    }
    public void updateProduct(int id, String nameToUpdate,BigDecimal priceToUpdate, int quantityToUpdate) throws SQLException{
        Product productUpdate = new Product(id, nameToUpdate, priceToUpdate, quantityToUpdate);
        if (productService.updateProduct(productUpdate)) System.out.println("Successfully Updated");
    }
    public void deleteProduct(int id) throws SQLException{
        if(productService.deleteProduct(id)) System.out.println("Successfully Deleted");
    }
}
