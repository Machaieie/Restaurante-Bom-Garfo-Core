package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.services;

import java.util.List;
import java.util.stream.Collectors;

import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.ItemPedido;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.Pedido;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.Reserva;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.dto.ItemPedidoDTO;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.dto.PedidoDTO;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.exceptions.EmptyDatabaseException;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.exceptions.ResourceNotFoundException;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.repository.PedidoRepository;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.repository.ReservaRepository;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.interfaces.CRUDInterface;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
public class PedidoService implements CRUDInterface<PedidoDTO, Long> {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Transactional
    @Override
    public PedidoDTO save(PedidoDTO pedidoDTO) {
        // Converter PedidoDTO para Pedido
        Pedido pedido = new Pedido();

        // Verificar se a reserva existe
        Reserva reserva = reservaRepository.findById(pedidoDTO.reserva_id())
                .orElseThrow(() -> new ResourceNotFoundException("Reserva não encontrada"));

        // Configurar a reserva do pedido
        pedido.setReserva(reserva);

        // Converter e adicionar os itens do pedido
        List<ItemPedido> itensPedido = pedidoDTO.itensPedido().stream().map(itemPedidoDTO -> {
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setDescricaoItem(itemPedidoDTO.descricao());
            itemPedido.setQuantidade(itemPedidoDTO.quantidade());
            itemPedido.setNomeItem(itemPedidoDTO.nome());
            itemPedido.setPedido(pedido); // Associar o item ao pedido
            return itemPedido;
        }).collect(Collectors.toList());

        // Configurar os itens do pedido
        pedido.setItensPedido(itensPedido);

        // Salvar o pedido e obter o pedido salvo
        Pedido savedPedido = pedidoRepository.save(pedido);

        // Converter o pedido salvo de volta para PedidoDTO
        List<ItemPedidoDTO> savedItensPedidoDTO = savedPedido.getItensPedido().stream().map(itemPedido -> {
            return new ItemPedidoDTO(itemPedido.getDescricaoItem(), itemPedido.getQuantidade(),
                    itemPedido.getNomeItem());
        }).collect(Collectors.toList());

        return new PedidoDTO(savedPedido.getReserva().getId(), savedItensPedidoDTO);
    }

    @Override
    public List<PedidoDTO> findAll() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        if (pedidos.isEmpty()) {
            throw new EmptyDatabaseException("Nenhum pedido foi encontrado");
        }
        return null;
    }

    @Override
    public PedidoDTO findById(Long id) throws ResourceNotFoundException {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));

        List<ItemPedidoDTO> itensPedidoDTO = pedido.getItensPedido().stream()
                .map(itemPedido -> new ItemPedidoDTO(itemPedido.getDescricaoItem(), itemPedido.getQuantidade(),
                        itemPedido.getNomeItem()))
                .collect(Collectors.toList());

        return new PedidoDTO(pedido.getReserva().getId(), itensPedidoDTO);
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));
        pedidoRepository.delete(pedido);
    }

}
