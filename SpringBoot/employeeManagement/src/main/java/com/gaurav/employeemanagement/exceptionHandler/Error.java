package com.gaurav.employeemanagement.exceptionHandler;

import org.springframework.http.HttpStatus;

public class Error
{
    private HttpStatus httpStatus;
    private String message;

    public Error() {
    }

    public Error(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
