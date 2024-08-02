package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.interfaces.CRUDInterface;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.Prato;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.exceptions.ResourceNotFoundException;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.repository.PratoRepository;

@Service
public class PratoService implements CRUDInterface<Prato, Long> {

    @Autowired
    private PratoRepository pratoRepository;

    @Override
    public Prato save(Prato entity) throws IllegalArgumentException {
        if (entity == null) {
            throw new IllegalArgumentException("Prato cannot be null");
        }
        return pratoRepository.save(entity);
    }

    @Override
    public Prato findById(Long id) throws ResourceNotFoundException {
        return pratoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prato with id: " + id + " not found"));
    }

    @Override
    public List<Prato> findAll() {
        return pratoRepository.findAll();
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        Prato prato = pratoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prato with id: " + id + " not found"));
        pratoRepository.delete(prato);
    }

    public Prato update(Long id, Prato pratoDetails) {
        Prato existingPrato = pratoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prato with id: " + id + " not found"));
    
        existingPrato.setNomeItem(pratoDetails.getNomeItem());
        existingPrato.setPrecoUnitario(pratoDetails.getPrecoUnitario());
        existingPrato.setTipo_Prato(pratoDetails.getTipo_Prato());
    
        return pratoRepository.save(existingPrato);
    }
    public long countAllPratos() {
        return pratoRepository.count();
    }
}
