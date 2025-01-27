package com.palasarakku.catalog.service;

import com.palasarakku.catalog.dto.ProductDetailsRequestDto;
import com.palasarakku.catalog.dto.ProductDetailsResponseDto;

import java.util.List;

public interface CatalogService {

    ProductDetailsResponseDto getProductById(String productId) throws NoSuchFieldException;

    ProductDetailsResponseDto getProductByExactName(String productName);

    List<ProductDetailsResponseDto> getAllProducts();

    String saveProductDetails(ProductDetailsRequestDto productDetailsRequestDto);

    String updateProductName(String productId, String productName) throws NoSuchFieldException;

    boolean deleteProductById(String productId) throws NoSuchFieldException;
}
