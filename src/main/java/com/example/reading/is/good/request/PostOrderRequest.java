package com.example.reading.is.good.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class PostOrderRequest implements Serializable {

    @NotNull(message = "{product.is.not.valid}")
    @Digits(integer = 18,fraction = 0,message = "{product.is.not.valid}")
    @Min(value = 0,message = "{product.is.not.valid}")
    private Long productId;

    @NotNull(message = "{quantity.is.not.valid}")
    @Digits(integer = 18,fraction = 0,message = "{quantity.is.not.valid}")
    @Min(value = 0,message = "{quantity.is.not.valid}")
    private Long quantity;
}
