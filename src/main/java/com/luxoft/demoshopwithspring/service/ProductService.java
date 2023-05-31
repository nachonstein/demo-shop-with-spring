package com.luxoft.demoshopwithspring.service;

import com.luxoft.demoshopwithspring.domain.ProductDto;
import com.luxoft.demoshopwithspring.exception.ShopException;

import java.util.List;


public interface ProductService {
    ProductDto addProductToInventory(ProductDto productDto);
    ProductDto removeProductFromInventory(ProductDto productDto) throws ShopException;

    List<ProductDto> getCurrentStock();
}
