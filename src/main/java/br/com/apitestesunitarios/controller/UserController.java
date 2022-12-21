package br.com.apitestesunitarios.controller;

import br.com.apitestesunitarios.infrastructure.model.UserEntity;
import br.com.apitestesunitarios.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@Slf4j
public class UserController {
    final String API_VERSION = "/v1";

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }


    @GetMapping(value = API_VERSION + "/{id}")
    public ResponseEntity<UserEntity> findUserById(@PathVariable Long id) {
        log.info("Início da camada de controller. Método findUserById - Id: {}", id);
        UserEntity userEntity = userServiceImpl.findById(id);
        log.info("Fim do método findByUserId - id: {}", 1);
        return ResponseEntity.ok().body(userEntity);
    }
}