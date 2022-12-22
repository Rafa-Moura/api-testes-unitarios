package br.com.apitestesunitarios.infrastructure.repository;

import br.com.apitestesunitarios.infrastructure.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
