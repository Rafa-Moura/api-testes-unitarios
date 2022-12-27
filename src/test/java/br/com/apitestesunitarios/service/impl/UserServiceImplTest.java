package br.com.apitestesunitarios.service.impl;

import br.com.apitestesunitarios.controller.dto.UserDto;
import br.com.apitestesunitarios.infrastructure.model.UserEntity;
import br.com.apitestesunitarios.infrastructure.repository.UserRepository;
import br.com.apitestesunitarios.service.exception.DataIntegrityViolationException;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    private static final Long ID = 1L;
    private static final String NAME = "Rafael";
    private static final String EMAIL = "rafael@gmail.com";
    private static final String PASSWORD = "49839";
    public static final int INDEX = 0;
    public static final String USUARIO_NAO_ENCONTRADO = "Usuário não encontrado";
    public static final String EMAIL_JA_CADASTRADO = "Email já cadastrado";

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
    void mustReturnAnUserWhenFindById() {
        when(userRepository.findById(anyLong())).thenReturn(optionalUserEntity);

        UserEntity response = userServiceImpl.findById(ID);

        assertNotNull(response);
        assertEquals(UserEntity.class, response.getClass());
        assertEquals(ID, response.getId());
    }

    @Test
    void mustReturnAnObjectNotFoundExceptionWhenFindById() {
        when(userRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException(USUARIO_NAO_ENCONTRADO));

        try {
            userServiceImpl.findById(ID);
        } catch (Exception exception) {
            assertEquals(ObjectNotFoundException.class, exception.getClass());
            assertEquals(USUARIO_NAO_ENCONTRADO, exception.getMessage());
        }
    }

    @Test
    void mustReturnAllUsersWhenFindAll() {
        when(userRepository.findAll()).thenReturn(List.of(userEntity));

        List<UserEntity> userList = userServiceImpl.findAll();

        assertNotNull(userList);
        assertEquals(1, userList.size());
        assertEquals(UserEntity.class, userList.get(INDEX).getClass());

        assertEquals(ID, userList.get(INDEX).getId());
        assertEquals(NAME, userList.get(INDEX).getName());
        assertEquals(EMAIL, userList.get(INDEX).getEmail());
        assertEquals(PASSWORD, userList.get(INDEX).getPassword());
    }

    @Test
    void mustSaveAnUserWhenCreate() {
        when(userRepository.save(any())).thenReturn(userEntity);

        UserEntity response = userServiceImpl.create(userDto);

        assertNotNull(response);
        assertEquals(UserEntity.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void mustReturnDataIntegrityViolationExceptionWhenCreateUser() {
        when(userRepository.findByEmail(anyString())).thenReturn(optionalUserEntity);

        try {
            optionalUserEntity.get().setId(2L);
            userServiceImpl.create(userDto);
        } catch (Exception exception) {
            assertEquals(DataIntegrityViolationException.class, exception.getClass());
            assertEquals(EMAIL_JA_CADASTRADO, exception.getMessage());
        }
    }

    @Test
    void mustUpdateAnUserWhenUpdate() {

        when(userRepository.findById(anyLong())).thenReturn(optionalUserEntity);
        when(userRepository.save(any())).thenReturn(userEntity);

        userEntity.setName("Thiago");

        UserEntity response = userServiceImpl.update(userDto);
        assertEquals(ID, response.getId());
        assertEquals("Thiago", response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());

    }

    @Test
    void mustReturnObjectNotFoundExceptionWhenUpdate() {
        when(userRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException(USUARIO_NAO_ENCONTRADO));
        try {
            userServiceImpl.update(userDto);
        } catch (Exception exception) {
            assertEquals(ObjectNotFoundException.class, exception.getClass());
            assertEquals(USUARIO_NAO_ENCONTRADO, exception.getMessage());
        }
    }

    @Test
    void mustReturnDataIntegrityViolationExceptionWhenUpdate() {
        when(userRepository.findById(anyLong())).thenReturn(optionalUserEntity);
        when(userRepository.findByEmail(anyString())).thenReturn(optionalUserEntity);
        try {
            optionalUserEntity.get().setId(2L);
            userServiceImpl.update(userDto);
        } catch (Exception exception) {
            assertEquals(DataIntegrityViolationException.class, exception.getClass());
            assertEquals(EMAIL_JA_CADASTRADO, exception.getMessage());
        }
    }

    @Test
    void mustDeleteAnUserWhenDeleteById() {
        when(userRepository.findById(anyLong())).thenReturn(optionalUserEntity);
        doNothing().when(userRepository).deleteById(anyLong());
        userServiceImpl.deleteById(ID);
        verify(userRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void mustReturnAnObjectNotFoundExceptionWhenDeleteById() {
        when(userRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException(USUARIO_NAO_ENCONTRADO));

        try {
            userServiceImpl.deleteById(ID);
        } catch (Exception exception) {
            assertEquals(ObjectNotFoundException.class, exception.getClass());
            assertEquals(USUARIO_NAO_ENCONTRADO, exception.getMessage());
        }
    }


    private void startUsers() {
        userEntity = new UserEntity(ID, NAME, EMAIL, PASSWORD);
        userDto = new UserDto(ID, NAME, EMAIL, PASSWORD);
        optionalUserEntity = Optional.of(new UserEntity(ID, NAME, EMAIL, PASSWORD));
    }
}