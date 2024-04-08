package com.demo.service;

import com.demo.dto.ProductEvent;
import com.demo.dto.ProductRatingDto;
import com.demo.entity.Product;
import com.demo.repository.ProductRepository;
import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Observed
public class ProductQueryService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    RestTemplate restTemplate;

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

//    ProductRatingDto getRatings(long prodId) {
//        return restTemplate.getForObject("http://localhost:9293/ratings/{prodId}", ProductRatingDto.class, prodId);
//
//    }
}
