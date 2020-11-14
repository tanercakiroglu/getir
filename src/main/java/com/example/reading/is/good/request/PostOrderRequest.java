package com.example.reading.is.good.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class PostOrderRequest implements Serializable {

    private Long productId;
    private Long quantity;
}
