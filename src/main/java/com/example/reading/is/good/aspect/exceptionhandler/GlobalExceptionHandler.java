package com.example.reading.is.good.aspect.exceptionhandler;

import com.example.reading.is.good.aspect.loggable.Loggable;
import com.example.reading.is.good.model.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice(basePackages = {"com.example.reading.is.good.controller"})
@Loggable
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String SOMETHING_WENT_WRONG = "Something went wrong...";
    public static final String RESOURCE_NOT_FOUND = "Resource not found";


    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity<ApiError> defaultErrorHandler(Exception ex) {
        LOGGER.error("System Exception", ex);
        final ApiError errors = getApiError(HttpStatus.INTERNAL_SERVER_ERROR, SOMETHING_WENT_WRONG);
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ApiError> illegalArgumentExceptionErrorHandler(IllegalArgumentException ex) {
        LOGGER.error("IllegalArgumentException Exception", ex);
        final ApiError errors = getApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ApiError> entityNotFoundException(EntityNotFoundException ex) {
        LOGGER.error("EntityNotFoundException Exception", ex);
        final ApiError errors = getApiError(HttpStatus.NOT_FOUND, RESOURCE_NOT_FOUND);
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final List<String> details = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> details.add(String.format("%s %s",fieldError.getField(),fieldError.getDefaultMessage())));
        final ApiError errors = getApiError(details);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    private ApiError getApiError(HttpStatus status, String errorMessage) {
        return ApiError
                .builder()
                .errors(Collections.singletonList(errorMessage))
                .status(status.value())
                .timestamp(LocalDateTime.now())
                .build();
    }

    private ApiError getApiError(List<String> errorMessage) {
        return ApiError
                .builder()
                .errors(errorMessage)
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();
    }

}
