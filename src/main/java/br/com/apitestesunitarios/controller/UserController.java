package br.com.apitestesunitarios.controller;

import br.com.apitestesunitarios.controller.dto.UserDto;
import br.com.apitestesunitarios.service.impl.UserServiceImpl;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
@Slf4j
@Api(value = "API REST Cadastro de Usuário - Estudo de Testes Unitários e Documentação")
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
    @ApiOperation(
            tags = {"Busca por id"},
            value = "Busca um usuário no sistema através de seu Id",
            response = UserDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuário localizado"),
            @ApiResponse(code = 404, message = "Usuário não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno do sistema. Contate o administrador")
    })
    public ResponseEntity<UserDto> findUserById(
            @ApiParam(value = "Id do Usuário para busca", example = "1") @PathVariable Long id) {
        log.info("Início da camada de controller. Método findUserById - Id: {}", id);

        return ResponseEntity.ok().body(modelMapper.map(userServiceImpl.findById(id), UserDto.class));
    }

    @GetMapping(value = API_VERSION)
    @ApiOperation(
            tags = {"Busca todos os usuários"},
            value = "Retorna uma lista com todos os usuários do sistema",
            response = UserDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de usuários"),
            @ApiResponse(code = 500, message = "Erro interno do sistema. Contate o administrador")
    })
    public ResponseEntity<List<UserDto>> findAll() {
        log.info("Início da camada de controller. Método findALl");

        return ResponseEntity
                .ok()
                .body(userServiceImpl.findAll()
                        .stream()
                        .map(userDto -> modelMapper.map(userDto, UserDto.class))
                        .collect(Collectors.toList()));
    }

    @PostMapping(value = API_VERSION, produces = "application/json")
    @ApiOperation(
            tags = {"Adiciona um novo usuário"},
            value = "Adiciona um novo usuário no sistema")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuário criado"),
            @ApiResponse(code = 400, message = "Todos os dados precisam ser preenchidos"),
            @ApiResponse(code = 409, message = "Email já cadastrado no sistema"),
            @ApiResponse(code = 500, message = "Erro interno do sistema. Contate o administrador")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid UserDto userDto) {

        log.info("Início da camada de controller. Método create");
        userServiceImpl.create(userDto);
    }

    @PutMapping(value = API_VERSION + "/{id}", produces = "application/json")
    @ApiOperation(
            tags = {"Atualiza um usuário pelo Id"},
            value = "Atualiza um usuário pelo Id",
            response = UserDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuário atualizado"),
            @ApiResponse(code = 404, message = "Usuário não encontrado"),
            @ApiResponse(code = 409, message = "Email já cadastrado"),
            @ApiResponse(code = 500, message = "Erro interno do sistema. Contate o administrador")
    })
    public ResponseEntity<UserDto> update(
            @ApiParam(value = "Id do usuário para atualização", example = "3")
            @PathVariable Long id, @RequestBody UserDto userDto) {

        userDto.setId(id);
        log.info("Início da camada de controller. Método Update {}", userDto.getId());
        return ResponseEntity.ok().body(modelMapper.map(userServiceImpl.update(userDto), UserDto.class));
    }

    @DeleteMapping(value = API_VERSION + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(
            tags = {"Exclui um usuário pelo Id"},
            value = "Exclui um usuário pelo Id",
            response = UserDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Usuário excluído com sucesso"),
            @ApiResponse(code = 404, message = "Usuário não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno do sistema. Contate o administrador")
    })
    public void delete(@ApiParam(value = "Id do usuário para exclusão", example = "3") @PathVariable Long id) {
        log.info("Início da camada de controller. Método delete {}", id);
        userServiceImpl.deleteById(id);
    }
}