package com.pokemon.api.service;

import com.pokemon.api.dto.UserTokenDTO;

public interface UserService {

    void createUser(String username, String password, String gender);

    UserTokenDTO loginUser(String username, String password);
}
