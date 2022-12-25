package br.com.apitestesunitarios.service;

import br.com.apitestesunitarios.controller.dto.UserDto;
import br.com.apitestesunitarios.infrastructure.model.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity findById(Long id);

    List<UserEntity> findAll();

    UserEntity create(UserDto userDto);

    UserEntity update(UserDto userDto);

    void deleteById(Long id);
}
