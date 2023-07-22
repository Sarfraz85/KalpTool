package com.kalp.tool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kalp.tool.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // You can define custom query methods here if needed
}
