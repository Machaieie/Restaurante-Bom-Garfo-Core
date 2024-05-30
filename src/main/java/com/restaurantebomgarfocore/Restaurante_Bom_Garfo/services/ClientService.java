package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.interfaces.CRUDInterface;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.Client;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.exceptions.DuplicatedEntitiesException;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.exceptions.EmptyDatabaseException;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.exceptions.ResourceNotFoundException;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.repository.ClientRepository;

@Service
public class ClientService implements CRUDInterface<Client,Long>{

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client save(Client entity) throws IllegalArgumentException {
          boolean clienteEncontrado = clientRepository.existsByNomeAndApelido(entity.getFirstName(), entity.getLastName());

        if(clienteEncontrado){
            throw new DuplicatedEntitiesException("Erro. Já existe um cliente com o mesmo nome e apelido.");
        }
        return clientRepository.save(entity);
    }

    @Override
    public Client findById(Long id) throws IllegalArgumentException {
       Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            return client.get();
        } else {
            throw new ResourceNotFoundException("Cliente não encontrado com o ID: " + id);
        }
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = clientRepository.findAll();
        if(clients.isEmpty()){
            throw new EmptyDatabaseException("Não tem clientes na base de dados");
        }
        return clients;
    }

    @Override
    public void deleteById(Long id) throws IllegalArgumentException {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente não encontrado com o ID: " + id);
        }
        clientRepository.deleteById(id);
    }
    
}
