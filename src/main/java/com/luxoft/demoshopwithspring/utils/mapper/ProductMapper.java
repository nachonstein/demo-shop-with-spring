package com.luxoft.demoshopwithspring.utils.mapper;

import com.luxoft.demoshopwithspring.domain.ProductDto;
import com.luxoft.demoshopwithspring.messages.ProductEvent;
import com.luxoft.demoshopwithspring.repository.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel =  MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    ProductDto entityToDomain(Product product);
    Product domainToEntity(ProductDto productDto);
    ProductEvent domainToEvent(ProductDto productDto);
    List<ProductDto> entityListToDomainList(List<Product> products);
}
