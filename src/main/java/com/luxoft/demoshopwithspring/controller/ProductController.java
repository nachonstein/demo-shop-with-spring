package com.luxoft.demoshopwithspring.controller;

import com.luxoft.demoshopwithspring.domain.ProductDto;
import com.luxoft.demoshopwithspring.exception.ShopException;
import com.luxoft.demoshopwithspring.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public void addProductToInventory(@RequestBody ProductDto productDto){
        productService.addProductToInventory(productDto);
    }
    @DeleteMapping
    public void removeProductFromInventory(@RequestBody ProductDto productDto) throws ShopException {
        productService.removeProductFromInventory(productDto);
    }

    @GetMapping
    public List<ProductDto> getCurrentStock(){
        return productService.getCurrentStock();
    }
}
