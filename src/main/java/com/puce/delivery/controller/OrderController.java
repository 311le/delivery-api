package com.puce.delivery.controller;

import com.puce.delivery.model.Order;
import com.puce.delivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        // Enviar la orden creada a los clientes suscritos
        messagingTemplate.convertAndSend("/topic/orders", createdOrder);
        return createdOrder;
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PutMapping("/{id}/status")
    public Order updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        Order updatedOrder = orderService.updateOrderStatus(id, status);
        // Enviar la orden actualizada a los clientes suscritos
        messagingTemplate.convertAndSend("/topic/orders", updatedOrder);
        return updatedOrder;
    }

    @DeleteMapping("/{id}")
    public Order deleteOrder(@PathVariable Long id) {
        Order deletedOrder = orderService.deleteOrder(id);
        // Enviar la orden eliminada a los clientes suscritos (opcional, si necesitas notificar eliminación)
        messagingTemplate.convertAndSend("/topic/orders", deletedOrder);
        return deletedOrder;
    }
}
