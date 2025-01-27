package com.palasarakku.catalog.controller;

import com.palasarakku.catalog.dto.ProductDetailsRequestDto;
import com.palasarakku.catalog.dto.ProductDetailsResponseDto;
import com.palasarakku.catalog.service.CatalogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.base.path}")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping(value = "/getProductById/{id}")
    public ResponseEntity<ProductDetailsResponseDto> getProductById(@PathVariable("id") String productId) throws NoSuchFieldException {
        return new ResponseEntity<>(catalogService.getProductById(productId), HttpStatus.OK);
    }

    @GetMapping(value = "/getProductByExactName")
    public ResponseEntity<ProductDetailsResponseDto> getProductByName(@RequestParam String name) {
        return new ResponseEntity<>(catalogService.getProductByExactName(name), HttpStatus.OK);
    }

    @GetMapping(value = "/listAllProducts")
    public ResponseEntity<List<ProductDetailsResponseDto>> listAllProducts(){
        return new ResponseEntity<>(catalogService.getAllProducts(),HttpStatus.OK);
    }

    @PostMapping(value = "/saveProductDetails")
    public ResponseEntity<String> saveProductCatalog(@Valid @RequestBody ProductDetailsRequestDto productDetailsRequestDto){
        return new ResponseEntity<>(catalogService.saveProductDetails(productDetailsRequestDto),HttpStatus.OK);
    }

    @PostMapping(value = "/updateProductName/{id}")
    public ResponseEntity<String> updateProductDetails(@PathVariable("id") String id, @RequestParam String name) throws NoSuchFieldException {
        return new ResponseEntity<>(catalogService.updateProductName(id,name),HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteProduct/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable("id") String id) throws NoSuchFieldException {
        if (catalogService.deleteProductById(id)){
            return new ResponseEntity<>("Product details deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Details are not deleted for the given productId :: "+id,HttpStatus.NOT_FOUND);
    }

}
