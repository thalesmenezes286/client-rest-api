package com.devsuperior.clientapi.repositories;

import com.devsuperior.clientapi.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
