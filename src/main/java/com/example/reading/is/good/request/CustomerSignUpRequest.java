package com.example.reading.is.good.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CustomerSignUpRequest implements Serializable {

    private static final long serialVersionUID = 7225132210826557032L;

    private Long id;

    private String username;

    private String password;

    private String name;

    private String surname;

}
