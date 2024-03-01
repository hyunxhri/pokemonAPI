package com.pokemon.api.controller;

import com.pokemon.api.controller.mapper.UserMapper;
import com.pokemon.api.dto.CreateUserDTO;
import com.pokemon.api.dto.LoginUserDTO;
import com.pokemon.api.dto.UserDTO;
import com.pokemon.api.dto.UserTokenDTO;
import com.pokemon.api.exception.UserNotFoundException;
import com.pokemon.api.model.User;
import com.pokemon.api.repository.UserRepository;
import com.pokemon.api.service.TokenExtractor;
import com.pokemon.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;
    private final TokenExtractor tokenExtractor;
    @GetMapping("/me")
    public UserDTO getUserById(@RequestHeader(name = "Authorization") String token){
        Long userId = tokenExtractor.getUser(token);
        return userMapper.toDTO(userService.getUser(userId));
    }

    @PostMapping
    public void createUser(@RequestBody CreateUserDTO userDTO){
        userService.createUser(userDTO.getUsername(), userDTO.getPassword(), userDTO.getGender());
    }

    @PostMapping("/login")
    public UserTokenDTO loginUser(@RequestBody LoginUserDTO loginUserDTO){
        return userService.loginUser(loginUserDTO.getUsername(), loginUserDTO.getPassword());
    }
}
