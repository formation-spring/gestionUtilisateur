package com.formation.formationspring.dto;

 import com.fasterxml.jackson.annotation.JsonInclude;
 import com.formation.formationspring.entities.Roles;
 import lombok.AllArgsConstructor;
 import lombok.Data;
 import lombok.NoArgsConstructor;

 import java.util.List;
 import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data// pour DTO
 @JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private UUID id;
     private String email;
     private String password;
     private String firstName;
     private String lastName;
     private String pseudo;
 List<RoleDto> roles;

}
