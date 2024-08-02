package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.dto;

import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.enums.StatusPedido;

public record PedidoDetalhadoDTO(
    String nomeCliente,
    String nomePrato,
    String tipoPrato,
    StatusPedido statusPedido,
    int quantidade
) {}
