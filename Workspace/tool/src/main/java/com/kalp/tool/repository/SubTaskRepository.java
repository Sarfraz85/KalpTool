package com.kalp.tool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kalp.tool.model.SubTask;

@Repository
public interface SubTaskRepository extends JpaRepository<SubTask, Long> {
    // You can define custom query methods here if needed
}
