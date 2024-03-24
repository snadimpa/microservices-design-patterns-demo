package com.demo.service;

import com.demo.dto.ProductRatingDto;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.Collections;

@HttpExchange
public interface RatingService {

    Logger log = LoggerFactory.getLogger(RatingService.class);

    @Bulkhead(name = "ratingServiceBulkhead", fallbackMethod = "getDefault")
    @GetExchange("ratings/{prodId}")
    public ResponseEntity<ProductRatingDto> getRating(@PathVariable("prodId") Long prodId);



    default  ResponseEntity<ProductRatingDto> getDefault(Throwable ex){
        log.info("getDefault---");
        log.error("Rating service is busy : {}", ex.getLocalizedMessage());
        log.debug("Returning default Rating");
        return new ResponseEntity<>(ProductRatingDto.of(0, Collections.emptyList()), HttpStatus.ACCEPTED);
    }

}
