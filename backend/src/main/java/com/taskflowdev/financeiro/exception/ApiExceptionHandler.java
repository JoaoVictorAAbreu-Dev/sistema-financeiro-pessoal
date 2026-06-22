package com.taskflowdev.financeiro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    ProblemDetail handleIllegalArgument(IllegalArgumentException ex) {
        ProblemDetail body = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        body.setTitle("Business rule violation");
        body.setDetail(ex.getMessage());
        return body;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ProblemDetail handleValidation(MethodArgumentNotValidException ex) {
        ProblemDetail body = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        body.setTitle("Validation failed");
        body.setDetail("The request payload is invalid");
        return body;
    }
}
