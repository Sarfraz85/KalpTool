package com.kalp.tool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kalp.tool.model.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    // You can define custom query methods here if needed
}
