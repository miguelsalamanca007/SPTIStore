package com.spti.shopping.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import com.spti.shopping.model.Product;

@Service
public class ProductServices {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired JdbcTemplate db;
    
    public List<Product> getAll(){
        System.out.println("\n Entered Product Services \n");
        String sql = "SELECT * FROM products";
        System.out.println("Query \n" + sql + " \n");
        List<Product> products = db.query(sql, new BeanPropertyRowMapper<>(Product.class));
        System.out.println("\n Result:" + products + "\n");

        return products;
    }

    public Product getProductById(String id) {
        String sql = "SELECT * FROM products WHERE id=" + id;
        Product product = db.queryForObject(sql, new BeanPropertyRowMapper<>(Product.class));
        return product;
    }

    public List<Product> getProductByName(String name) {
        String sql = "SELECT * FROM products WHERE name LIKE :name";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name + "%");
        List<Product> products = namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(Product.class));
        return products;
    }

}
