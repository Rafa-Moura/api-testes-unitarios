package br.com.apitestesunitarios.config;

import br.com.apitestesunitarios.infrastructure.model.UserEntity;
import br.com.apitestesunitarios.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    private final UserRepository userRepository;

    @Autowired
    public LocalConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public void startDb() {
        UserEntity userEntity = new UserEntity(null, "Rafael Moura", "rafael@gmail.com", "75647");
        UserEntity userEntity2 = new UserEntity(null, "Rayanne Moura", "rayanne@gmail.com", "75647");
        userRepository.saveAll(List.of(userEntity, userEntity2));
    }

}
