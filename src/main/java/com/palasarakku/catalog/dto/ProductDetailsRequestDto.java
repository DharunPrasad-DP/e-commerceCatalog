package com.palasarakku.catalog.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetailsRequestDto {
    @NotBlank(message = "Product id cannot be blank/null")
    @Pattern(regexp = "Dp-\\d{5}", message = "Product code must start with 'DP-' followed by 5 digits")
    private String productId;

    @NotBlank(message = "Product name cannot be blank/null")
    @Size(min = 3, max = 50, message = "Product name must be between 3 and 50 characters")
    private String productName;

    @Min(value = 10, message = "Price must be at least 10")
    @Max(value = 10000, message = "Price cannot exceed 10000")
    private Long price;

    @Min(value = 0, message = "Stock must be at least 0")
    @Max(value = 100, message = "Stock cannot exceed 100")
    private int stockLimit;

    @JsonIgnore
    private Long createdDate;

    @Min(value = 1, message = "Weight must be at least 1 gram ")
    @Max(value = 10000, message = "Weight cannot exceed 10 kgs")
    private Long weight;
}
