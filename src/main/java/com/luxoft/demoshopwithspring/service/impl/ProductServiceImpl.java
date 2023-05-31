package com.luxoft.demoshopwithspring.service.impl;

import com.luxoft.demoshopwithspring.domain.ProductDto;
import com.luxoft.demoshopwithspring.exception.ShopException;
import com.luxoft.demoshopwithspring.repository.ProductRepository;
import com.luxoft.demoshopwithspring.repository.entity.Product;
import com.luxoft.demoshopwithspring.service.ProductService;
import com.luxoft.demoshopwithspring.utils.mapper.ProductMapper;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Async
    @Transactional
    @Override
    @Retryable(value = {SQLException.class}, maxAttempts = 4, backoff = @Backoff(1000))
    public ProductDto addProductToInventory(ProductDto productDto){
        Optional<Product> productEntity = productRepository.findByNameAndBrandAndQuality(productDto.getName(), productDto.getBrand(),
                productDto.getQuality().name());

        Product productSaved;

        if (productEntity.isPresent()){
            Product product = productEntity.get();
            product.setStock(product.getStock() + productDto.getStock());

            productSaved = productRepository.save(product);
        } else {
            productSaved = productRepository.save(productMapper.domainToEntity(productDto));
        }

        return productMapper.entityToDomain(productSaved);
    }


    @Async
    @Transactional
    @Override
    @Retryable(value = {SQLException.class, ShopException.class}, maxAttempts = 4, backoff = @Backoff(1000))
    public ProductDto removeProductFromInventory(ProductDto productDto) throws ShopException {
        Optional<Product> productEntity = productRepository.findByNameAndBrandAndQuality(productDto.getName(), productDto.getBrand(),
                productDto.getQuality().name());

        Product productSaved;

        if (productEntity.isPresent()){
            Product product = productEntity.get();
            product.setStock(product.getStock() - productDto.getStock());

            if (product.getStock() <0) {
                throw new ShopException("Negative stock is not allowed");
            }

            productSaved = productRepository.save(product);
        } else {
            throw new ShopException("Negative stock is not allowed");
        }


        return productMapper.entityToDomain(productSaved);
    }

    @Override
    public List<ProductDto> getCurrentStock() {
        return productMapper.entityListToDomainList(productRepository.findAll());
    }
}
