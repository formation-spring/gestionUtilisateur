package com.formation.formationspring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data// pour DTO
@Accessors(chain = true)
@Entity
@Table(name= "TABLE_UTILISATEUR")
 public class User implements Serializable {
     @Id
    @org.hibernate.annotations.Type(type= "uuid-char")
    @Column(name="USER_ID",columnDefinition = "VARCHAR(80)")
    private UUID id;
    @Column(name="USER_EMAIL",columnDefinition = "VARCHAR(80)")

    private String email;
    @Column(name="USER_PASSWORD",columnDefinition = "VARCHAR(80)")

    private String password;
    @Column(name="USER_FIRSTNAME",columnDefinition = "VARCHAR(80)")

    private String firstName;
    @Column(name="USER_LASTNAME",columnDefinition = "VARCHAR(80)")

    private String lastName;
    @Column(name="USER_PSEUDO",columnDefinition = "VARCHAR(80)")

    private String pseudo;
    @ManyToMany(fetch = FetchType.EAGER,cascade =CascadeType.ALL)
    @JoinTable(
            name = "USER_ROLE_TABLE",
            inverseJoinColumns   = @JoinColumn(name = "ROLE_ID", foreignKey = @ForeignKey(name = "FK_USER_ROLE_ROLE"), referencedColumnName="ROLE_ID",nullable = false, updatable = false),
            joinColumns = @JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_USER_ROLE_USER"), referencedColumnName="USER_ID",nullable = false, updatable = false))

    List<Roles> roles;

}
