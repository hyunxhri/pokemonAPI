package com.pokemon.api.controller.mapper.impl;

import com.pokemon.api.controller.mapper.UserMapper;
import com.pokemon.api.dto.UserDTO;
import com.pokemon.api.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setImg(user.getImg());
        userDTO.setGender(user.getGender());
        return userDTO;
    }

}
