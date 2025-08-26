package com.example.librarysystem.repository;

import com.example.librarysystem.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    List<Borrow> findByUserId(Long userId);  // ✅ Find all borrows by user ID
}
