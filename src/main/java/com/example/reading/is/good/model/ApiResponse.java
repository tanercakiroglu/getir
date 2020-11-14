package com.example.reading.is.good.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {

    int status;
    Object data;

    public ApiResponse() {
        this.status= HttpStatus.OK.value();
        this.data="success";
    }
    public ApiResponse(int status, Object data) {
        this.status = status;
        this.data = data;
    }
}
