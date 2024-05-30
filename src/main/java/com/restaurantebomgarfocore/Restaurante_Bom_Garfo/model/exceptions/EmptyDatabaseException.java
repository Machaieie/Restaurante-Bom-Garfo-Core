package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class EmptyDatabaseException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public EmptyDatabaseException(String message){
        super(message);
    }
    
}