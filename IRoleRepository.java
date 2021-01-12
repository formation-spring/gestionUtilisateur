package com.formation.formationspring.dao;

import com.formation.formationspring.entities.Roles;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface IRoleRepository extends CrudRepository<Roles, UUID> {

    List<Roles> findAll();
 }
