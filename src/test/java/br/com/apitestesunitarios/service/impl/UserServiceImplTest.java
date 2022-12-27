package br.com.apitestesunitarios.service.impl;

import br.com.apitestesunitarios.controller.dto.UserDto;
import br.com.apitestesunitarios.infrastructure.model.UserEntity;
import br.com.apitestesunitarios.infrastructure.repository.UserRepository;
import br.com.apitestesunitarios.service.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    private static final Long id = 1L;
    private static final String name = "Rafael";
    private static final String email = "rafael@gmail.com";
    private static final String password = "49839";

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    private UserEntity userEntity;

    private UserDto userDto;

    private Optional<UserEntity> optionalUserEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userServiceImpl = new UserServiceImpl(userRepository, modelMapper);
        startUsers();
    }

    @Test
    void mustReturnAnUserById() {
        when(userRepository.findById(anyLong())).thenReturn(optionalUserEntity);

        UserEntity response = userServiceImpl.findById(id);

        assertNotNull(response);
        assertEquals(UserEntity.class, response.getClass());
        assertEquals(id, response.getId());
    }

    @Test
    void mustReturnAnObjectNotFoundException() {
        when(userRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Usuário não encontrado"));

        try {
            userServiceImpl.findById(id);
        } catch (Exception exception) {
            assertEquals(ObjectNotFoundException.class, exception.getClass());
            assertEquals("Usuário não encontrado", exception.getMessage());
        }
    }

    @Test
    void mustReturnAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(userEntity));

        List<UserEntity> userList = userServiceImpl.findAll();

        assertNotNull(userList);
        assertEquals(1, userList.size());
        assertEquals(UserEntity.class, userList.get(0).getClass());
        assertEquals(id, userList.get(0).getId());
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }

    private void startUsers() {
        userEntity = new UserEntity(id, name, email, password);
        userDto = new UserDto(id, name, email, password);
        optionalUserEntity = Optional.of(new UserEntity(id, name, email, password));
    }
}