package com.pokemon.api.service;

import com.pokemon.api.dto.UserTokenDTO;
import com.pokemon.api.model.User;

public interface UserService {

    void createUser(String username, String password, String gender);

    User getUser(Long userId);

    UserTokenDTO loginUser(String username, String password);
}
