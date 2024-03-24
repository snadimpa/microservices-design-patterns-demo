package com.demo.service;

import com.demo.dto.ProductEvent;
import com.demo.dto.ProductRatingDto;
import com.demo.entity.Product;
import com.demo.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductQueryService {

    @Autowired
    private ProductRepository repository;

//    @Autowired
//    private RatingServiceClient ratingServiceClient;

    @Autowired
    private RatingService ratingService;

    private Logger log = LoggerFactory.getLogger(ProductQueryService.class);
    public List<ProductEvent> getProducts() {
        log.info("getProducts with ratings---");

        List<ProductEvent> productList = new ArrayList<>() ;

        repository.findAll().forEach(product -> {
           productList.add(ProductEvent.of(product, ratingService.getRating(product.getId()).getBody()));
        });

        return productList;
    }

    public ProductEvent getProductDto(Long productId) {
        log.info("getProducts by id with ratings---");

        var product = repository.findById(productId);
        return ProductEvent.of(product.orElse(new Product()), ratingService.getRating(productId).getBody());
    }
}
