package br.com.apitestesunitarios.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @JsonIgnore
    private Long id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
}
