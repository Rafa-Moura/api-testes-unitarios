package br.com.apitestesunitarios.service.impl;

import br.com.apitestesunitarios.controller.dto.UserDto;
import br.com.apitestesunitarios.infrastructure.model.UserEntity;
import br.com.apitestesunitarios.infrastructure.repository.UserRepository;
import br.com.apitestesunitarios.service.UserService;
import br.com.apitestesunitarios.service.exception.DataIntegrityViolationException;
import br.com.apitestesunitarios.service.exception.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserEntity findById(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        return userEntity.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity create(UserDto userDto) {
        findByEmail(userDto);
        return userRepository.save(modelMapper.map(userDto, UserEntity.class));
    }

    @Override
    public UserEntity update(UserDto userDto) {
        findById(userDto.getId());
        findByEmail(userDto);
        return userRepository.save(modelMapper.map(userDto, UserEntity.class));
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        userRepository.deleteById(id);
    }

    private void findByEmail(UserDto userDto) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(userDto.getEmail());
        if (userEntity.isPresent() && !userEntity.get().getId().equals(userDto.getId())) {
            throw new DataIntegrityViolationException("Email já cadastrado");
        }
    }
}