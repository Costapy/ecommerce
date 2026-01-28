package com.costa.ecommerce.controllers;

import com.costa.ecommerce.domain.users.Users;
import com.costa.ecommerce.dto.LoginRequestDTO;
import com.costa.ecommerce.dto.RegisterRequestDTO;
import com.costa.ecommerce.dto.ResponseDTO;
import com.costa.ecommerce.infra.security.TokenService;
import com.costa.ecommerce.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.costa.ecommerce.domain.users.UsersRole.ADMIN;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body) {
        Users user = this.usersRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));

        if(passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(token, user.getName()));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body) {
        Optional<Users> user = this.usersRepository.findByEmail(body.email());
        if (user.isEmpty()) {
            Users newUser = new Users();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setName(body.name());
            newUser.setEmail(body.email());
            newUser.setRole(ADMIN);
            this.usersRepository.save(newUser);

            String token = tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(token, newUser.getName()));
        }
        return ResponseEntity.badRequest().build();
    }
}
