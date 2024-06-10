package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.controller;

import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.Reserva;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.dto.ReservaDTO;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.dto.ReservaResponseDTO;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.services.PedidoService;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reservas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private PedidoService pedidoService;

    // Endpoint para criar uma nova reserva
    @PostMapping("/adicionarReserva")
    public ResponseEntity<String> createReserva(@RequestBody ReservaDTO reservaDTO) {
        String response = reservaService.save(reservaDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Endpoint para buscar todas as reservas
    @GetMapping("/pegarTodos")
    public ResponseEntity<List<ReservaResponseDTO>> getAllReservas() {
        List<ReservaResponseDTO> reservaDTOs = reservaService.findAll();
        return new ResponseEntity<>(reservaDTOs, HttpStatus.OK);
    }

    // Endpoint para buscar uma reserva por ID
    @GetMapping("/reserva/{id}")
    public ResponseEntity<ReservaResponseDTO> getReservaById(@PathVariable Long id) {
        Reserva reserva = reservaService.findById(id);
        ReservaResponseDTO responseDTO = new ReservaResponseDTO(
                reserva.getId(),
                reserva.getDate(),
                reserva.getTime(),
                reserva.getNumberPeople(),
                reserva.getObservations(),
                reserva.getFirstName(),
                reserva.getLastName(),
                reserva.getEmail(),
                reserva.getPhone());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
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

    @GetMapping("/{reservaId}/conta")
    public double getTotalConta(@PathVariable Long reservaId) {
        return pedidoService.getTotalContaByReservaId(reservaId);
    }

    // Endpoint para contar todas as reservas já efetuadas
    @GetMapping("/contarReservas")
    public ResponseEntity<Long> countAllReservas() {
        long totalReservas = reservaService.countAllReservas();
        return new ResponseEntity<>(totalReservas, HttpStatus.OK);
    }
}
