package com.flagteam.dispatchmanagementapp.service;

import com.flagteam.dispatchmanagementapp.exception.UserPasswordMismatchException;
import com.flagteam.dispatchmanagementapp.model.Token;
import com.flagteam.dispatchmanagementapp.exception.UserAlreadyExistException;
import com.flagteam.dispatchmanagementapp.exception.UserNotFoundException;
import com.flagteam.dispatchmanagementapp.model.User;
import com.flagteam.dispatchmanagementapp.repository.UserRepository;
import com.flagteam.dispatchmanagementapp.util.JwtUtil;
import org.springframework.format.datetime.joda.LocalDateParser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
                       UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void add(User user) {

        user.setId(UUID.randomUUID());
        if (userRepository.existsById(user.getId()) || !userRepository.findByUsername(user.getUsername()).isEmpty()) {
            throw new UserAlreadyExistException("User already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedDate(LocalDate.now());
        userRepository.save(user);
    }

    public Token authenticate(User user) throws UserNotFoundException {

        List<User> candidate = userRepository.findByUsername(user.getUsername());
        if (candidate == null || candidate.isEmpty()) {
            throw new UserNotFoundException("User does not exist.");
        }

        boolean match = passwordEncoder.matches(user.getPassword(), candidate.get(0).getPassword());
        if (!match) {
            throw new UserPasswordMismatchException("User password mismatch.");
        }
        return new Token(jwtUtil.generateToken(user.getUsername()));
    }
}
