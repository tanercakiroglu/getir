package com.example.reading.is.good.controller.impl;

import com.example.reading.is.good.aspect.loggable.Loggable;
import com.example.reading.is.good.controller.IProductController;
import com.example.reading.is.good.model.ApiResponse;
import com.example.reading.is.good.service.IProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Loggable
public class ProductController  implements IProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @Override
    public ResponseEntity<ApiResponse> getProductById(Long id) {
        return ResponseEntity.ok(new ApiResponse(productService.getProductById(id)));
    }
}
