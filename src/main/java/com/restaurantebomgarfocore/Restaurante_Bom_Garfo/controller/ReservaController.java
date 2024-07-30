package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.controller;

import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.Reserva;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.dto.ReservaDTO;
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

     // Endpoint para deletar uma reserva por ID ou vários IDs separados por vírgula
     @DeleteMapping("/reserva/{ids}")
     public ResponseEntity<String> deleteReserva(@PathVariable String ids) {
         String[] idArray = ids.split(",");
         for (String id : idArray) {
             reservaService.deleteById(Long.parseLong(id));
         }
         return new ResponseEntity<>("Reservas deletadas com sucesso", HttpStatus.NO_CONTENT);
     }

    @GetMapping("/{reservaId}/conta")
    public double getTotalConta(@PathVariable Long reservaId) {
        return pedidoService.getTotalContaByReservaId(reservaId);
    }
}
