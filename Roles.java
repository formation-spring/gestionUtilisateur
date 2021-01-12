package com.formation.formationspring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data// pour DTO
@Accessors(chain = true)
@Entity
@Table(name= "TABLE_ROLE")
public class Roles implements Serializable {
    @Id
    @org.hibernate.annotations.Type(type= "uuid-char")
    @Column(name="ROLE_ID",columnDefinition = "VARCHAR(80)")
    private UUID id;
    @Column(name="ROLE_LABEL",columnDefinition = "VARCHAR(80)")

    private String label;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles",cascade = CascadeType.ALL)
    private Collection<User> users;
public Roles(UUID id,String label){
    this.id=id;
    this.label=label;
}
}
