package com.kalp.tool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kalp.tool.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    // You can define custom query methods here if needed
}
