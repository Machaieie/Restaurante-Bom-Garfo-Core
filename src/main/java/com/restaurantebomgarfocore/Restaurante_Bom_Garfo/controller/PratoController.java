package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.Prato;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.exceptions.ResourceNotFoundException;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.services.PratoService;

@RestController
@RequestMapping("/api/pratos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PratoController {

    @Autowired
    private PratoService pratoService;

    // Endpoint para criar um novo prato
    @PostMapping("/criar")
    public ResponseEntity<Prato> createPrato(@RequestBody Prato prato) {
        Prato createdPrato = pratoService.save(prato);
        return new ResponseEntity<>(createdPrato, HttpStatus.CREATED);
    }

    // Endpoint para buscar todos os pratos
    @GetMapping("/todos")
    public ResponseEntity<List<Prato>> getAllPratos() {
        List<Prato> pratos = pratoService.findAll();
        return new ResponseEntity<>(pratos, HttpStatus.OK);
    }

    // Endpoint para buscar um prato por ID
    @GetMapping("/prato/{id}")
    public ResponseEntity<Prato> getPratoById(@PathVariable Long id) {
        try {
            Prato prato = pratoService.findById(id);
            return new ResponseEntity<>(prato, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para atualizar um prato por ID
    @PutMapping("/prato/{id}")
    public ResponseEntity<Prato> updatePrato(@PathVariable Long id, @RequestBody Prato pratoDetails) {
        try {
            Prato updatedPrato = pratoService.update(id, pratoDetails);
            return new ResponseEntity<>(updatedPrato, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para excluir um prato por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrato(@PathVariable Long id) {
        try {
            pratoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/contarPratos")
    public ResponseEntity<Long> countAllReservas() {
        long totalPratos = pratoService.countAllPratos();
        return new ResponseEntity<>(totalPratos, HttpStatus.OK);
    }
}
