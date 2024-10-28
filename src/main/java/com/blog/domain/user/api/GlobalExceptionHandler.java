package com.blog.domain.user.api;

import com.blog.domain.user.config.exception.BusinessLogicException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<Map<String, String>> handleBusinessLogicException(BusinessLogicException e) {
        Map<String, String> response = new HashMap<>();
        response.put("message",e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
