package com.example.reading.is.good.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError extends ApiResponse implements Serializable {
    
    private static final long serialVersionUID = 212031948106464727L;
    List<String> errors;
    LocalDateTime timestamp;

    @Builder
    public ApiError(int status, Object data, List<String> errors, LocalDateTime timestamp) {
        super(status, data);
        this.errors = errors;
        this.timestamp = timestamp;
    }
}