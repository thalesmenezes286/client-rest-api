package com.devsuperior.clientapi.services;

import com.devsuperior.clientapi.dto.ClientDTO;
import com.devsuperior.clientapi.entities.Client;
import com.devsuperior.clientapi.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
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
}
