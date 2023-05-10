package com.spti.shopping.controller;

import java.security.InvalidParameterException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.spti.shopping.model.Product;
import com.spti.shopping.service.ProductServices;

@RestController
@RequestMapping("/api")
public class ProductController{

    @Autowired
    private ProductServices productServices;

    @GetMapping("/products")
    public List<Product> getProducts(){
        System.out.println("\n Entered Product Controller \n");
        return productServices.getAll();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable String id){
        return productServices.getProductById(id);
    }

    @GetMapping("/productsByName/{name}")
    public List<Product> getProductByName(@PathVariable String name) {
        System.out.println("name in controller: " + name);

        if (isValidName(name)) {
            return productServices.getProductByName(name);
        } else {
            throw new InvalidParameterException("Invalid name parameter");
        }
    }

    private boolean isValidName(String name) {
        // Define una expresión regular que permita solo caracteres alfabéticos y espacios
        String regex = "^[a-zA-Z\\s]+$";
    
        // Valida el parámetro 'name' utilizando la expresión regular
        if (name.matches(regex)) {
            return true;
        } else {
            return false;
        }
    }

}

