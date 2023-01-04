package br.com.apitestesunitarios.controller.Api;

import br.com.apitestesunitarios.controller.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api")
@Api(value = "API REST Cadastro de Usuário - Estudo de Testes Unitários e Documentação")
public interface UserControllerApi {
    final String API_VERSION = "/v1";

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
    public ResponseEntity<UserDto> findUserById(Long id);

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
    public ResponseEntity<List<UserDto>> findAll();

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
    public void create(UserDto userDto);

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
    public ResponseEntity<UserDto> update(Long id, UserDto userDto);

    @DeleteMapping(value = API_VERSION + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(
            tags = {"Exclui um usuário pelo Id"},
            value = "Exclui um usuário pelo Id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Usuário excluído com sucesso"),
            @ApiResponse(code = 404, message = "Usuário não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno do sistema. Contate o administrador")
    })
    public void delete(Long id);


}
