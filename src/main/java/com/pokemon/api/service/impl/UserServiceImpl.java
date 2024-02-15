package com.pokemon.api.service.impl;

import com.pokemon.api.controller.mapper.UserMapper;
import com.pokemon.api.dto.UserTokenDTO;
import com.pokemon.api.exception.InvalidCredentialsException;
import com.pokemon.api.model.User;
import com.pokemon.api.repository.UserRepository;
import com.pokemon.api.service.TokenGenerator;
import com.pokemon.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenGenerator tokenGenerator;

    @Override
    public void createUser(String username, String password, String gender) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setGender(gender);
        userRepository.save(user);
    }

    @Override
    public UserTokenDTO loginUser(String username, String password) {
        User user = userRepository.findByUsername(username)
                .filter(u -> passwordEncoder.matches(password, u.getPassword()))
                .orElseThrow(() -> new InvalidCredentialsException("Invalid Credentials."));
        UserTokenDTO userToken = new UserTokenDTO();
        userToken.setToken(tokenGenerator.generate(user.getId()));
        return userToken;
    }
}
