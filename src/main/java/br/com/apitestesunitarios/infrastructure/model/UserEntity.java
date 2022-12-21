package br.com.apitestesunitarios.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "É obrigatório informar um nome")
    private String name;
    @NotBlank(message = "É obrigatório informar um email")
    @Column(unique = true)
    private String email;
    @NotBlank(message = "É obrigatório informar um password")
    private String password;
}
