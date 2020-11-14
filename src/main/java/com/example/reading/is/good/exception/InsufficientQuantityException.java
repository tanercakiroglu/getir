package com.example.reading.is.good.exception;

public class InsufficientQuantityException extends Exception {

    private static final long serialVersionUID = 7718828512143293558L;

    private final String code;


    public InsufficientQuantityException(String code) {
        super();
        this.code = code;
    }

    public InsufficientQuantityException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }

    public InsufficientQuantityException(String message, String code) {
        super(message);
        this.code = code;
    }

    public InsufficientQuantityException(Throwable cause, String code) {
        super(cause);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
