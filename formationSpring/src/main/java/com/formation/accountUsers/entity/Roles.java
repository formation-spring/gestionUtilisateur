package com.formation.accountUsers.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
@Table(name = "TABLE_ROLE")
public class Roles implements Serializable {
	@Id
	@org.hibernate.annotations.Type(type = "uuid-char")
	@Column(name = "ROLE_ID", columnDefinition = "VARCHAR (80)")
	private UUID id;
	@Column(name = "ROLE_ID", columnDefinition = "VARCHAR (80)")
	private String label;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles",cascade = CascadeType.ALL)
	private Collection<User> users;

	
	
}
