package com.demo.controller;

import com.demo.dto.ProductRatingDto;
import com.demo.service.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("ratings")
public class RatingController {

    private Logger log = LoggerFactory.getLogger(RatingController.class);


    @Autowired
    private RatingService ratingService;

    @GetMapping("{prodId}")
    public ResponseEntity<ProductRatingDto> getRating(@PathVariable int prodId) throws InterruptedException {
        log.info("getRating");
        var productRatingDto = this.ratingService.getRatingForProduct(prodId);
        return this.failRandomly(productRatingDto);
    }

    private ResponseEntity<ProductRatingDto> failRandomly(ProductRatingDto productRatingDto) throws InterruptedException {
        // simulate delay
//        Thread.sleep(100);
//        // simulate failure
//        var random = ThreadLocalRandom.current().nextInt(1, 4);
//        if (random < 3)
//            return ResponseEntity.status(500).build();
        log.info("return rating");

        return ResponseEntity.ok(productRatingDto);
    }
}
