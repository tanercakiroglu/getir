package com.example.reading.is.good.converter;

import com.example.reading.is.good.entity.Product;
import com.example.reading.is.good.model.ProductDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityToDtoConverter  implements Converter<Product, ProductDto> {
    @Override
    public ProductDto convert(Product product) {
        return ProductDto.builder()
                .author(product.getAuthor())
                .price(product.getPrice())
                .id(product.getId())
                .name(product.getName())
                .quantity(product.getQuantity())
                .build();
    }
}
