package com.formation.formationspring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.formation.formationspring.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data// pour DTO
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDto {

    private UUID id;

    private String label;
 }
