package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.controller;

import java.nio.file.AccessDeniedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.exceptions.DuplicatedEntitiesException;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.exceptions.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicatedEntitiesException.class)
    public ResponseEntity<Object> handleDuplicatedEntityException(DuplicatedEntitiesException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.CONFLICT.value() );
        errorResponse.setMessage(ex.getMessage());
        
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = { BadCredentialsException.class })
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected ResponseEntity<ErrorResponse> handleBadCredencialsException(BadCredentialsException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.FORBIDDEN.value() );
        errorResponse.setMessage(ex.getMessage());
       

        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

}
