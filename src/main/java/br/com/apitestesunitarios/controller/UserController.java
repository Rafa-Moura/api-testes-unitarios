package br.com.apitestesunitarios.controller;

import br.com.apitestesunitarios.infrastructure.model.UserEntity;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping(value = API_VERSION + "/{id}")
    public ResponseEntity<UserEntity> findUserById(@PathVariable Long id) {
        log.info("Início da camada de controller. Método findUserById - Id: {}", id);
        UserEntity userEntity = new UserEntity(1L, "rafael", "rafael@gmail.com", "12345");
        log.info("Fim do método findByUserId - User: {}", userEntity.getEmail());
        return ResponseEntity.ok().body(userEntity);
    }
}