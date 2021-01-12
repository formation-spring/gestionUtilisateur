package com.formation.formationspring.dao;

import com.formation.formationspring.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepository extends CrudRepository<User, UUID> {
List<User>findAll();
Optional<User> findByEmail(String email);

 }
