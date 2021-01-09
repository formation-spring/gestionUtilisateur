package com.formation.accountUsers.entity;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@DynamicUpdate
@Table(name = "TABLE_USER")
public class User implements Serializable{
	@Id
	@org.hibernate.annotations.Type(type = "uuid-char")
	@Column(name = "USER_ID", columnDefinition = "VARCHAR (80)")
	private UUID id;
	@Column(name = "USER_LASTNAME", columnDefinition = "VARCHAR (80)")
	private String lastName;
	@Column(name = "USER_FIRSTNAME", columnDefinition = "VARCHAR (80)")
	private String firstName;
	@Column(name = "USER_EMAIL", columnDefinition = "VARCHAR (80)")
	private String email;
	@Column(name = "USER_PASSWORD", columnDefinition = "VARCHAR (80)")
	private String password;
	@Column(name = "USER_PSEUDO", columnDefinition = "VARCHAR (80)")
	private String pseudo;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade =CascadeType.ALL)
	@JoinTable(
	  name = "USER_ROLE_TABLE", 
			  inverseJoinColumns   = @JoinColumn(name = "ROLE_ID", foreignKey = @ForeignKey(name = "FK_USER_ROLE_ROLE"), referencedColumnName="ROLE_ID",nullable = false, updatable = false), 
			  joinColumns = @JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_USER_ROLE_USER"), referencedColumnName="USER_ID",nullable = false, updatable = false))
	
	List<Roles> roles;

}
