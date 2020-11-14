package com.example.reading.is.good.controller.impl;

import com.example.reading.is.good.aspect.loggable.Loggable;
import com.example.reading.is.good.controller.IOrderController;
import com.example.reading.is.good.model.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Loggable
public class OrderController implements IOrderController {

    @Override
    public ResponseEntity<ApiResponse> getOrder(String id) {
        String sasdnsa="22";
        sasdnsa.chars();
        return ResponseEntity.ok(new ApiResponse());
    }
}


