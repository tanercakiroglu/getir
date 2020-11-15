package com.example.reading.is.good.exception;

public class UserExistException extends Exception {

    private static final long serialVersionUID = 7718828512143293558L;

    private final String code;


    public UserExistException(String code) {
        super();
        this.code = code;
    }

    public UserExistException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }

    public UserExistException(String message, String code) {
        super(message);
        this.code = code;
    }

    public UserExistException(Throwable cause, String code) {
        super(cause);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
