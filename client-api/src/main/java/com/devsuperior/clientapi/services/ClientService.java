package com.devsuperior.clientapi.services;

import com.devsuperior.clientapi.dto.ClientDTO;
import com.devsuperior.clientapi.entities.Client;
import com.devsuperior.clientapi.repositories.ClientRepository;
import com.devsuperior.clientapi.services.exceptions.DatabaseException;
import com.devsuperior.clientapi.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){

        Page<Client> result = repository
                .findAll(pageable);

        return result.map(x -> new ClientDTO(x));
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){

        Client client = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente inexistente!"));

        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto){

        var client = new Client();
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setIncome(dto.getIncome());
        client.setBirthDate(dto.getBirthDate());
        client.setChildren(dto.getChildren());
        client = repository.save(client);

        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto){
        try{
            Client client = repository.getReferenceById(id);
            client.setName(dto.getName());
            client.setCpf(dto.getCpf());
            client.setIncome(dto.getIncome());
            client.setBirthDate(dto.getBirthDate());
            client.setChildren(dto.getChildren());
            client = repository.save(client);

            return new ClientDTO(client);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Cliente inexistente!");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){

        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Cliente inexistente!");
        }

        try{
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha na integridade referencial.");
        }
    }
}
