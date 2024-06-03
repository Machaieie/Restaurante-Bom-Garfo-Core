package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.controller;

import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.Pagamento;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    // Endpoint para criar um novo pagamento
    @PostMapping
    public ResponseEntity<Pagamento> createPagamento(@RequestBody Pagamento pagamento) {
        Pagamento savedPagamento = pagamentoService.save(pagamento);
        return new ResponseEntity<>(savedPagamento, HttpStatus.CREATED);
    }

    // Endpoint para buscar todos os pagamentos
    @GetMapping
    public ResponseEntity<List<Pagamento>> getAllPagamentos() {
        List<Pagamento> pagamentos = pagamentoService.findAll();
        return new ResponseEntity<>(pagamentos, HttpStatus.OK);
    }

    // Endpoint para buscar um pagamento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> getPagamentoById(@PathVariable long id) {
        Pagamento pagamento = pagamentoService.findById(id);
        return new ResponseEntity<>(pagamento, HttpStatus.OK);
    }
}
