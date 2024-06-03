package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.controller;

import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.Reserva;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.dto.ReservaDTO;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    // Endpoint para criar uma nova reserva
    @PostMapping("/adicionarReserva")
    public ResponseEntity<String> createReserva(@RequestBody ReservaDTO reservaDTO) {
        String response = reservaService.save(reservaDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Endpoint para buscar todas as reservas
    @GetMapping("/pegarTodos")
    public ResponseEntity<List<Reserva>> getAllReservas() {
        List<Reserva> reservas = reservaService.findAll();
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }

    // Endpoint para buscar uma reserva por ID
    @GetMapping("/reserva/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Long id) {
        Reserva reserva = reservaService.findById(id);
        return new ResponseEntity<>(reserva, HttpStatus.OK);
    }

    // Endpoint para atualizar uma reserva por ID
    @PutMapping("/reserva/{id}")
    public ResponseEntity<String> updateReserva(@PathVariable Long id, @RequestBody ReservaDTO reservaDTO) {
        String response = reservaService.update(id, reservaDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Endpoint para deletar uma reserva por ID
    @DeleteMapping("/reserva/{id}")
    public ResponseEntity<String> deleteReserva(@PathVariable Long id) {
        String response = reservaService.deleteById(id);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
