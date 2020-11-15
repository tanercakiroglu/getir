package com.example.reading.is.good.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class CustomerSignUpRequest implements Serializable {

    private static final long serialVersionUID = 7225132210826557032L;
    private static final String PasswordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    @Email(message = "${email.is.not.valid}")
    private String username;
    @Pattern(regexp = PasswordRegex,message = "{password.should.be.strong}")
    private String password;

    @NotBlank(message = "{name.is.not.valid}")
    private String name;

    @NotBlank(message = "{surname.is.not.valid}")
    private String surname;

}
