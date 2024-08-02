package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.controller;

import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.Pedido;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.dto.ItemPedidoDTO;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.dto.PedidoDTO;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.dto.PedidoDetalhadoDTO;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.exceptions.ResourceNotFoundException;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/pedidos")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // Endpoint para criar um novo pedido
    @PostMapping("/adicionarPedido")
    public ResponseEntity<PedidoDTO> createPedido(@RequestBody PedidoDTO pedidoDTO) {
        PedidoDTO savedPedidoDTO = pedidoService.save(pedidoDTO);
        return new ResponseEntity<>(savedPedidoDTO, HttpStatus.CREATED);
    }

    // Endpoint para buscar todos os pedidos
    @GetMapping("pedidos")
    public ResponseEntity<List<PedidoDTO>> getAllPedidos() {
        List<PedidoDTO> pedidos = pedidoService.findAll();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    // Endpoint para buscar um pedido por ID
    @GetMapping("/pedido/{id}")
    public ResponseEntity<PedidoDTO> getPedidoById(@PathVariable Long id) {
        PedidoDTO pedidoDTO = pedidoService.findById(id);
        return new ResponseEntity<>(pedidoDTO, HttpStatus.OK);
    }

    // Endpoint para excluir um pedido por ID
    @DeleteMapping("/pedido/{id}")
    public ResponseEntity<Void> deletePedidoById(@PathVariable Long id) {
        pedidoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/detalhados")
    public ResponseEntity<List<PedidoDetalhadoDTO>> getAllPedidosDetalhados() {
        List<PedidoDetalhadoDTO> pedidosDetalhados = pedidoService.getAllPedidosDetalhados();
        return ResponseEntity.ok(pedidosDetalhados);
    }
    @GetMapping("/contarPedidos")
    public ResponseEntity<Long> countAllReservas() {
        long totalPedidos = pedidoService.countAllPedidos();
        return new ResponseEntity<>(totalPedidos, HttpStatus.OK);

    }

}
