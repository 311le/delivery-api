package com.puce.delivery.model;

import java.util.List;

public class UserResponse {

    private User user;
    private List<Order> orders;
    private List<Notification> notifications;

    public UserResponse(User user, List<Order> orders, List<Notification> notifications) {
        this.user = user;
        this.orders = orders;
        this.notifications = notifications;
    }

    // Getters and setters

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}
