package com.aritizate.controller;

import com.aritizate.model.User;
import com.aritizate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("El nombre de usuario ya existe");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("El correo electrónico ya está registrado");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("Usuario registrado con éxito");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body("Usuario no encontrado");
        }
        User dbUser = optionalUser.get();

        if (passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            return ResponseEntity.ok("Inicio de sesión exitoso");
        } else {
            return ResponseEntity.badRequest().body("Contraseña incorrecta");
        }
    }

    @PostMapping("/check-email")
    public ResponseEntity<String> checkEmail(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        if (userRepository.findByEmail(email).isPresent()) {
            return ResponseEntity.ok("El correo existe. Puedes crear una nueva contraseña.");
        } else {
            return ResponseEntity.badRequest().body("Correo no encontrado");
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String,String> request) {
        String email = request.get("email");
        String newPassword = request.get("newPassword");
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body("Correo no encontrado");
        }

        User user = optionalUser.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return ResponseEntity.ok("Contraseña actualizada con éxito");
    }
}

