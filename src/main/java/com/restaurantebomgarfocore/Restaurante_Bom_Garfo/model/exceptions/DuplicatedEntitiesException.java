package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicatedEntitiesException  extends RuntimeException{

    public DuplicatedEntitiesException(String message){
        super(message);
    }
    
}
