package com.example.reading.is.good.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class CustomerSignInRequest implements Serializable {

    private static final long serialVersionUID = -176742497879678044L;

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
