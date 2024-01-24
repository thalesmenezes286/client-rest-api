package com.devsuperior.clientapi.controllers;

import com.devsuperior.clientapi.dto.ClientDTO;
import com.devsuperior.clientapi.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable){

        Page<ClientDTO> dto = service.findAll(pageable);

        return ResponseEntity.ok(dto);
    }
}

