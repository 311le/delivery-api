package com.puce.delivery.service;

import com.puce.delivery.exception.ResourceNotFoundException;
import com.puce.delivery.model.Order;
import com.puce.delivery.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;  // Añadido para WebSocket

    public Order createOrder(Order order) {
        Order createdOrder = orderRepository.save(order);
        messagingTemplate.convertAndSend("/topic/orders", createdOrder);  // Enviar mensaje a través de WebSocket
        return createdOrder;
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
    }

    public Order updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);
        messagingTemplate.convertAndSend("/topic/orders", updatedOrder);  // Enviar mensaje a través de WebSocket
        return updatedOrder;
    }

    public Order deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
        orderRepository.deleteById(id);
        messagingTemplate.convertAndSend("/topic/orders", order);  // Enviar mensaje a través de WebSocket.
        return order;
    }
}
