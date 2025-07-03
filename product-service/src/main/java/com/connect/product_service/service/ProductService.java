package com.connect.product_service.service;

import com.connect.product_service.dto.ProductRequest;
import com.connect.product_service.dto.ProductResponse;
import com.connect.product_service.entry.Product;
import com.connect.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void addProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }


    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
}