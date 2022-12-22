package br.com.apitestesunitarios.controller;

import br.com.apitestesunitarios.controller.dto.UserDto;
import br.com.apitestesunitarios.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
@Slf4j
public class UserController {
    final String API_VERSION = "/v1";

    private final UserServiceImpl userServiceImpl;

    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl, ModelMapper modelMapper) {
        this.userServiceImpl = userServiceImpl;
        this.modelMapper = modelMapper;
    }


    @GetMapping(value = API_VERSION + "/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable Long id) {
        log.info("Início da camada de controller. Método findUserById - Id: {}", id);
        return ResponseEntity.ok().body(modelMapper.map(userServiceImpl.findById(id), UserDto.class));
    }

    @GetMapping(value = API_VERSION)
    public ResponseEntity<List<UserDto>> findAll() {
        log.info("Início da camada de controller. Método findALl");
        return ResponseEntity
                .ok()
                .body(userServiceImpl.findAll()
                .stream()
                .map(userDto -> modelMapper.map(userDto, UserDto.class))
                .collect(Collectors.toList()));
    }
}