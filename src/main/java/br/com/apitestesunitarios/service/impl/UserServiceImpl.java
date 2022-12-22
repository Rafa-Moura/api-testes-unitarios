package br.com.apitestesunitarios.service.impl;

import br.com.apitestesunitarios.infrastructure.model.UserEntity;
import br.com.apitestesunitarios.infrastructure.repository.UserRepository;
import br.com.apitestesunitarios.service.UserService;
import br.com.apitestesunitarios.service.exception.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity findById(Long id) {
        log.info(this.getClass() + ": Início do método findById - Id: {}", id);
        Optional<UserEntity> userEntity = userRepository.findById(id);
        return userEntity.orElseThrow(
                () -> new ObjectNotFoundException(this.getClass().toString(), "Usuário não encontrado"));
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }
}