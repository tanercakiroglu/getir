package com.example.reading.is.good.service;

import com.example.reading.is.good.model.ProductDto;

public interface IProductService {

    ProductDto getProductById(Long id);
}
