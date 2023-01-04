package br.com.apitestesunitarios.controller;

import br.com.apitestesunitarios.controller.Api.UserControllerApi;
import br.com.apitestesunitarios.controller.dto.UserDto;
import br.com.apitestesunitarios.service.impl.UserServiceImpl;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestController
public class UserController implements UserControllerApi {

    private final UserServiceImpl userServiceImpl;

    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl, ModelMapper modelMapper) {
        this.userServiceImpl = userServiceImpl;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<UserDto> findUserById(
            @ApiParam(value = "Id do Usuário para busca", example = "1") @PathVariable Long id) {
        log.info("Início da camada de controller. Método findUserById - Id: {}", id);

        return ResponseEntity.ok().body(modelMapper.map(userServiceImpl.findById(id), UserDto.class));
    }

    @Override
    public ResponseEntity<List<UserDto>> findAll() {
        log.info("Início da camada de controller. Método findALl");

        return ResponseEntity
                .ok()
                .body(userServiceImpl.findAll()
                        .stream()
                        .map(userDto -> modelMapper.map(userDto, UserDto.class))
                        .collect(Collectors.toList()));
    }

    @Override
    public void create(@RequestBody @Valid UserDto userDto) {

        log.info("Início da camada de controller. Método create");
        userServiceImpl.create(userDto);
    }

    @Override
    public ResponseEntity<UserDto> update(
            @ApiParam(value = "Id do usuário para atualização", example = "3")
            @PathVariable Long id, @RequestBody UserDto userDto) {

        userDto.setId(id);
        log.info("Início da camada de controller. Método Update {}", userDto.getId());
        return ResponseEntity.ok().body(modelMapper.map(userServiceImpl.update(userDto), UserDto.class));
    }

    @Override
    public void delete(@ApiParam(value = "Id do usuário para exclusão", example = "3") @PathVariable Long id) {
        log.info("Início da camada de controller. Método delete {}", id);
        userServiceImpl.deleteById(id);
    }
}