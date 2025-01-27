package com.palasarakku.catalog.service;

import com.palasarakku.catalog.domain.ProductDetails;
import com.palasarakku.catalog.dto.ProductDetailsRequestDto;
import com.palasarakku.catalog.dto.ProductDetailsResponseDto;
import com.palasarakku.catalog.repository.CatalogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CatalogRepository catalogRepository;

    // Convert Domain to ResponseDTO for a given domain
    private ProductDetailsResponseDto convertDomainToResponseDTO(ProductDetails product) {
        ProductDetailsResponseDto productDetailsDto = new ProductDetailsResponseDto();
        BeanUtils.copyProperties(product, productDetailsDto);
        return productDetailsDto;
    }

    // Convert DTO to Domain
    private ProductDetails convertToDomain(ProductDetailsRequestDto productDTO) {
        ProductDetails productDetails = new ProductDetails();
        BeanUtils.copyProperties(productDTO, productDetails);
        return productDetails;
    }

    @Override
    public ProductDetailsResponseDto getProductById(String productId) throws NoSuchFieldException {
        Optional<ProductDetails> productDetails = catalogRepository.findById(productId);
        if (productDetails.isEmpty()) {
            throw new NoSuchFieldException("Product not found in DB");
        }
        return convertDomainToResponseDTO(productDetails.get());
    }

    @Override
    public ProductDetailsResponseDto getProductByExactName(String productName) {
        return convertDomainToResponseDTO(catalogRepository.findByProductName(productName).get());
    }

    @Override
    public List<ProductDetailsResponseDto> getAllProducts() {
        return catalogRepository.findAll().stream()
                .map(productDetails -> ProductDetailsResponseDto.builder()
                        .productName(productDetails.getProductName())
                        .stockLimit(productDetails.getStockLimit())
                        .price(productDetails.getPrice())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public String saveProductDetails(ProductDetailsRequestDto productDetailsRequestDto) {
        productDetailsRequestDto.setCreatedDate(System.currentTimeMillis());
        catalogRepository.save(convertToDomain(productDetailsRequestDto));
        return "Product details saved to catalog";
    }

    @Override
    public String updateProductName(String productId, String updatedName) throws NoSuchFieldException {
        ProductDetails productDetails = catalogRepository.findById(productId).get();
        productDetails.setProductName(updatedName);
        catalogRepository.save(productDetails);
        return "Product name updated successfully";
    }

    @Override
    public boolean deleteProductById(String productId) throws NoSuchFieldException {
        if (getProductById(productId) != null) {
            catalogRepository.deleteById(productId);
            return true;
        }
        return false;
    }
}
