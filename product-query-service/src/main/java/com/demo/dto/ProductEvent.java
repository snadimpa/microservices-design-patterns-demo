package com.demo.dto;

import com.demo.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ProductEvent {
    private Product product;
    private ProductRatingDto productRating;

}
