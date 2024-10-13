package org.example.javaspring.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.javaspring.dto.auth.SignUpRequestDto;
import org.example.javaspring.dto.auth.SignUpResponseDto;
import org.example.javaspring.entity.User;
import org.example.javaspring.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("User '%s' not found".formatted(username)));
    }

    public SignUpResponseDto createUser(@Valid SignUpRequestDto signUpRequestDto) {
        String password = passwordEncoder.encode(signUpRequestDto.getPassword());
        User user = new User();
        user.setUsername(signUpRequestDto.getUsername());
        user.setPassword(password);
        user.setRole(signUpRequestDto.getRole());
        user.setRegisteredAt(LocalDateTime.now());
        userRepository.save(user);

        return SignUpResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .registeredAt(user.getRegisteredAt())
                .build();
    }
}
