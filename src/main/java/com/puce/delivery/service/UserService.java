package com.puce.delivery.service;

import com.puce.delivery.exception.ResourceNotFoundException;
import com.puce.delivery.model.*;
import com.puce.delivery.repository.OrderRepository;
import com.puce.delivery.repository.NotificationRepository;
import com.puce.delivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

        List<Order> orders = orderRepository.findByUserId(id);
        List<Notification> notifications = notificationRepository.findByUserId(id);

        return new UserResponse(user, orders, notifications);
    }

    public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPasswordHash(updatedUser.getPasswordHash());
        return userRepository.save(existingUser);
    }

    @Transactional
    public UserResponse deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

        List<Order> orders = orderRepository.findByUserId(id);
        List<Notification> notifications = notificationRepository.findByUserId(id);

        orderRepository.deleteByUserId(id);
        notificationRepository.deleteByUserId(id);

        userRepository.deleteById(id);

        return new UserResponse(user, orders, notifications);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User authenticateUser(String name, String passwordHash) {
        User user = userRepository.findByName(name);
        if (user != null && user.getPasswordHash().equals(passwordHash)) {
            return user;
        }
        return null;
    }
}
