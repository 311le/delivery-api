package com.puce.delivery.repository;

import com.puce.delivery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByName(String name); // Añadir este método
}
