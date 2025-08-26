package com.example.librarysystem.repository;

import com.example.librarysystem.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
    List<Resource> findByBookId(Long bookId);
}
