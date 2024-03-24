package com.demo.controller;

import com.demo.dto.ProductEvent;
import com.demo.service.ProductQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductQueryController {

    private Logger log = LoggerFactory.getLogger(ProductQueryController.class);

    @Autowired
    private ProductQueryService queryService;
    @GetMapping
    public List<ProductEvent> fetchAllProducts(){
        log.info("fetchAllProducts");
        return queryService.getProducts();
    }

    @GetMapping("{productId}")
    public ProductEvent getProduct(@PathVariable Long productId){
        log.info("getProduct");

        return queryService.getProductDto(productId);
    }



}
