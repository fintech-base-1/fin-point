package com.fp.finpoint.advice;


import com.fp.finpoint.global.exception.BusinessLogicException;
import com.fp.finpoint.global.exception.response.ErrorResponse;
import io.lettuce.core.RedisConnectionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse methodArgumentValidExceptionHandler(MethodArgumentNotValidException e) {
        return ErrorResponse.of(e.getBindingResult());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse constraintViolationExceptionHandler(ConstraintViolationException e) {
        return ErrorResponse.of(e.getConstraintViolations());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorResponse httpRequestMethodSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        return ErrorResponse.of(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse nullPointerExceptionHandler(NullPointerException e) {
        return ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> businessLogicExceptionHandler(BusinessLogicException e) {
        final ErrorResponse response = ErrorResponse.of(e.getExceptionCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleRedisConnectionException(RedisConnectionException e) {
        final ErrorResponse response = ErrorResponse.of(e);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
