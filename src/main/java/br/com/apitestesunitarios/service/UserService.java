package br.com.apitestesunitarios.service;

import br.com.apitestesunitarios.infrastructure.model.UserEntity;

public interface UserService {
    UserEntity findById(Long id);
}
