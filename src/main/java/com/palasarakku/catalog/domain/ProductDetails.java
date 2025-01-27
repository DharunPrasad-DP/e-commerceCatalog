package com.palasarakku.catalog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;

/**
 * Here we have product specifications
 *    Product's name
 *    Product's id
 *    Product's stock
 *    Product's createdDate
 *    Product's dimensions
 *    Product's weight
 */
@Document(collection = "productDetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetails {
    @Id
    private String productId;
    private String productName;
    private Long price;
    private int stockLimit;
    private Long createdDate;
    private Long weight;
}
