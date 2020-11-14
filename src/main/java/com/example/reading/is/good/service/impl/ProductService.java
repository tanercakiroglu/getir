package com.example.reading.is.good.service.impl;

import com.example.reading.is.good.converter.ProductEntityToDtoConverter;
import com.example.reading.is.good.model.ProductDto;
import com.example.reading.is.good.repository.IProductRepository;
import com.example.reading.is.good.service.IProductService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ProductService implements IProductService {

    private final IProductRepository productRepository;
    private final ProductEntityToDtoConverter productEntityToDtoConverter;

    public ProductService(IProductRepository productRepository, ProductEntityToDtoConverter productEntityToDtoConverter) {

        this.productRepository = productRepository;
        this.productEntityToDtoConverter = productEntityToDtoConverter;
    }

    @Override
    public ProductDto getProductById(Long id) {
        var product =productRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("product not Found"));
        return productEntityToDtoConverter.convert(product);
    }
}
