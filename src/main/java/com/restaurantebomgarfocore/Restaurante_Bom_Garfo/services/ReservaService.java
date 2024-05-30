package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.Client;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.Reserva;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.dto.ReservaDTO;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.exceptions.EmptyDatabaseException;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.exceptions.ResourceNotFoundException;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.repository.ClientRepository;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.repository.ReservaRepository;

public class ReservaService{

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClientRepository clientRepository;

    public Reserva save(ReservaDTO entity) throws ResourceNotFoundException {
       Client client = clientRepository.findById(entity.clientId()).orElseThrow(()->  new ResourceNotFoundException("Cliente  com o ID: " + entity.clientId()+" não encontrado!") );
         Reserva reserva = new Reserva();
         reserva.setTime(entity.time());
         reserva.setNumberPeople(entity.numberPeople());
         reserva.setObservations(entity.observations());
         reserva.setClient(client);
         reserva.setDate(entity.date());
        
        return reservaRepository.save(reserva);
    }

    
    public Reserva findById(Long id) throws IllegalArgumentException {
        return reservaRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Reserva com id: " + id + " não encontrada"));
    }

    
    public List<Reserva> findAll() {
        List<Reserva> reservas = reservaRepository.findAll();
        if (reservas.isEmpty()) {
            throw new EmptyDatabaseException("Nenhuma reserva foi encontrada");
        }
        return reservas;
    }

    
    public void deleteById(Long id) throws IllegalArgumentException {
     Reserva foundedReserva =   reservaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Reserva com id: " + id + " não encontrada"));
            reservaRepository.delete(foundedReserva);
    }

    public void update(long id, ReservaDTO entity) {
        Reserva foundedReserva = reservaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Reserva com id: " + id + " não encontrada"));
        
        // Update the existing reserva with the new values
        foundedReserva.setTime(entity.time());
        foundedReserva.setNumberPeople(entity.numberPeople());
        foundedReserva.setObservations(entity.observations());
        foundedReserva.setDate(entity.date());
        
        // Save the updated reserva
        reservaRepository.save(foundedReserva);
    }
    
    
}
