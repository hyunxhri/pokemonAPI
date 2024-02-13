package com.pokemon.api.controller;

import com.pokemon.api.controller.mapper.UserMapper;
import com.pokemon.api.dto.CreateUserDTO;
import com.pokemon.api.dto.UserDTO;
import com.pokemon.api.exception.UserNotFoundException;
import com.pokemon.api.model.User;
import com.pokemon.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found."));
        return userMapper.toDTO(user);
    }

    @PostMapping
    public void createUser(@RequestBody CreateUserDTO userDTO){
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setImg(userDTO.getUsername());
        userRepository.save(user);
    }

    @PostMapping("/login")
    public UserDTO loginUser(){
        UserDTO userDTO = new UserDTO();
        //TO DO.
        return userDTO;
    }

    //PUT & DELETE
}
