package com.pokemon.api.controller.mapper;

import com.pokemon.api.dto.UserDTO;
import com.pokemon.api.model.User;

public interface UserMapper {

    UserDTO toDTO(User user);

}
