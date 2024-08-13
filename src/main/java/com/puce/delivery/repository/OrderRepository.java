package com.puce.delivery.repository;

import com.puce.delivery.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Buscar órdenes por el ID del usuario
    List<Order> findByUserId(Long userId);
    
    // Eliminar órdenes por el ID del usuario
    void deleteByUserId(Long userId);
}
