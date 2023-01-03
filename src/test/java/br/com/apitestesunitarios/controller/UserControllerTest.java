package br.com.apitestesunitarios.controller;

import br.com.apitestesunitarios.controller.dto.UserDto;
import br.com.apitestesunitarios.infrastructure.model.UserEntity;
import br.com.apitestesunitarios.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class UserControllerTest {

    public static final long ID = 1L;
    public static final String NAME = "rafael moura";
    public static final String EMAIL = "rafael@gmail.com";
    public static final String PASSWORD = "12345";
    @InjectMocks
    private UserController userController;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserServiceImpl userServiceImpl;

    @Autowired
    MockMvc mockMvc;

    private UserDto userDto;
    private UserEntity userEntity;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController(userServiceImpl, modelMapper);
        startUser();
    }

    @Test
    @DisplayName(value = "findByUserId Method. Should be return an userDto and Status ok")
    void mustReturnAnUserWhenFindByUserId() throws Exception {
        when(userServiceImpl.findById(anyLong())).thenReturn(userEntity);
        when(modelMapper.map(any(), any())).thenReturn(userDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/{id}", 1L)
                        .contentType("application/json")
                        .characterEncoding(StandardCharsets.UTF_8)
                ).andExpect(MockMvcResultMatchers.jsonPath("$.name").isNotEmpty())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)).andReturn();
    }

    @Test
    @DisplayName(value = "findAll Method. Should be return a list of userDto and Status ok")
    void mustReturnlistOfUserDtoWhenFindAll() throws Exception {
        when(userServiceImpl.findAll()).thenReturn(List.of(userEntity));
        when(modelMapper.map(any(), any())).thenReturn(userDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1")
                        .contentType("application/json")
                        .characterEncoding(StandardCharsets.UTF_8)
                ).andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0]").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").isNotEmpty())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Should be create a new User and return status code 201, email and name")
    void mustBeCreateANewUser() throws Exception {
        when(userServiceImpl.create(userDto)).thenReturn(userEntity);

        String userDtoJson = "{\"name\":\"Joana Moura\",\"email\": \"teste@gmail.com\",\"password\":\"233023\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userDtoJson))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName(value = "Should be update a User and return status code 200, email and name")
    void mustBeUpdateANewUser() throws Exception {
        when(userServiceImpl.create(userDto)).thenReturn(userEntity);

        String userDtoJson = "{\"name\":\"Joana Moura\",\"email\": \"joana@gmail.com\",\"password\":\"233023\"}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userDtoJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Should be Delete an User by your id")
    void mustBeDeleteAnUserById() throws Exception {
        doNothing().when(userServiceImpl).deleteById(anyLong());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    private void startUser() {
        userEntity = new UserEntity(ID, NAME, EMAIL, PASSWORD);
        userDto = new UserDto(ID, NAME, EMAIL, PASSWORD);
    }

}