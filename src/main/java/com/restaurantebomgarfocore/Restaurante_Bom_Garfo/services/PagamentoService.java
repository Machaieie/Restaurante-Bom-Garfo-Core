package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.Pagamento;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.Reserva;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.exceptions.EmptyDatabaseException;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.exceptions.ResourceNotFoundException;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.repository.PagamentoRepository;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.repository.ReservaRepository;

@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Transactional
    public Pagamento save(Pagamento pagamento) {
        
        Reserva reserva = reservaRepository.findById(pagamento.getReserva().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Reserva não encontrada para o pagamento"));
        
        pagamento.setReserva(reserva); 
        return pagamentoRepository.save(pagamento);
    }

    public Pagamento findById(long id) {
        return pagamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pagamento com id: " + id + " não encontrado"));
    }

    public List<Pagamento> findAll() {
        List<Pagamento> pagamentos = pagamentoRepository.findAll();
        if (pagamentos.isEmpty()) {
            throw new EmptyDatabaseException("Nenhum pagamento foi encontrado");
        }
        return pagamentos;
    }
}
