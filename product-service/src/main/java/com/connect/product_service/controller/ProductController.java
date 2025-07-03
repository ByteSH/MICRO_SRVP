package com.connect.product_service.controller;


import com.connect.product_service.dto.ProductRequest;
import com.connect.product_service.dto.ProductResponse;
import com.connect.product_service.entry.Product;
import com.connect.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody ProductRequest productRequest){
        productService.addProduct(productRequest);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }
}