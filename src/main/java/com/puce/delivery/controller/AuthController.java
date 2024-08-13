package com.puce.delivery.controller;

import com.puce.delivery.model.LoginRequest;
import com.puce.delivery.model.User;
import com.puce.delivery.model.UserResponse;
import com.puce.delivery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.authenticateUser(loginRequest.getName(), loginRequest.getPasswordHash());
        if (user != null) {
            // Obtiene la respuesta del usuario con sus órdenes y notificaciones
            UserResponse userResponse = userService.getUserById(user.getId());
            return ResponseEntity.ok(userResponse);
        } else {
            // Devuelve una respuesta de error si las credenciales no son válidas
            return ResponseEntity.status(401).body(null); // Unauthorized
        }
    }
}
