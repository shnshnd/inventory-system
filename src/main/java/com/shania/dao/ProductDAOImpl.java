package com.shania.dao;

import com.shania.model.Product;
import com.shania.util.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    //IMPROVEMENTS:
    // Proper Error Logging,
    // Layer per method for checking if product exist
    //
    @Override//Continue: Checking how to handle the display
    public boolean createProduct(Product product) {
        String sql = "INSERT INTO products(name, price, stock_quantity) VALUES(?, ?, ?)";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1, product.getName());
            pstmt.setBigDecimal(2, product.getPrice());
            pstmt.setInt(3, product.getStockQuantity());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Product getProductById(int id){
        String sql = "SELECT id, name, price, stock_quantity, created_at FROM products WHERE id = ?";//using * instead of specifying the columns is said to be not ideal
        try(Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setInt(1, id);
            try(ResultSet rs = pstmt.executeQuery()) {
                //LEARN why the executeQuery() didn't work when I put the searchQuery inside as the argument, then work after I remove it
                //Ans: because in the prepareStatement(sql) the query is already precompiled and stored
                //putting the sql as argument for excuteQuery() will only break the parameter binding.
                if (rs.next()) {
                    //IMPROVEMENTS: extract mapping into a separate method to make it clean and so it can be re-use for getAllProducts()
                    int resultId = rs.getInt("id");
                    String resultName = rs.getString("name");
                    BigDecimal resultPrice = rs.getBigDecimal("price");
                    int resultStockQuantity = rs.getInt("stock_quantity");
                    LocalDateTime resultDateCreated = rs.getObject("created_at", LocalDateTime.class);
                    //rs.getTimestamp("created_at").toLocalDateTime();
                    return new Product(resultId, resultName, resultPrice, resultStockQuantity, resultDateCreated);
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        //IMPROVEMENTS: GPT Suggestion - Later (when we add Service layer): 👉 I may ask you to convert to Optional
        return null;//using just null is vulnerable for NullPointerException is what your trying to access turns out null.
    }


    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT id, name, price, stock_quantity, created_at FROM products";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()){
            while(rs.next()){
                //It is recommended to separate the extraction from the construction for cleaner program(readability)
                int id = rs.getInt("id");
                String name = rs.getString("name");
                BigDecimal price = rs.getBigDecimal("price");
                int stockQuantity = rs.getInt("stock_quantity");

                Timestamp ts = rs.getTimestamp("created_at");
                LocalDateTime createdAt = (ts != null) ? ts.toLocalDateTime() : null;
                //LocalDateTime resultCreatedAt = rs.getObject("created_at", LocalDateTime.class);
                Product product = new Product(id, name, price, stockQuantity, createdAt);
                productList.add(product);
            }
        } catch(SQLException e){
            e.printStackTrace();
            //print stack trace (for now)
            //IMPROVEMENTS: GPT Suggestion - throw runtime exception (better later)
        }
        return productList;//It is better to use productList for better consistency, this will also return an empty list if there's no data helps the NullPointerException
        //List.of() - This is actually good practice (immutable empty list 👏). But I already created List<Product> productList = new ArrayList<>();
    }

    @Override //REVIEW: ChatGPT suggestions
    public boolean updateProduct(Product product){
        //IMPROVEMENT: Validate Input
        // Ex if product is null → throw IllegalArgumentException
        //if product.getId() <= 0 → invalid update target //Updating without valid ID is logically wrong.
        String sql = "UPDATE products SET name = ?, price = ?, stock_quantity = ? WHERE id = ?";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, product.getName());
            pstmt.setBigDecimal(2, product.getPrice());
            pstmt.setInt(3, product.getStockQuantity());
            pstmt.setInt(4, product.getId());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();//throw new RuntimeException(e);//Learn the different exception/errors and the different use of "throw". WHERE and WHAT are they used
            return false;//putting the return false inside the catch keeps failures handled together
            //IMPROVEMENTS: Enterprise systems often use: Custom Exceptions instead return false (though it is okay to use return false for small/medium apps)
        }
    }

    @Override
    public boolean deleteProduct(int id){
        //Improvements Suggestion Gpt Optional:
        //final String sql //Query should not change
        //logging
        //Check existence
        //Validate the action before deleting completely(optional)
        String sql = "DELETE FROM products WHERE id = ?";//REVIEW: if I should add final keyword for this string
        try(Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
