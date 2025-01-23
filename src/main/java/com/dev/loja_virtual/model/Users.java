package com.dev.loja_virtual.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "users")
@SequenceGenerator(name = "seq_users", sequenceName = "seq_users", initialValue = 1, allocationSize = 1)
public class Users implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_users")
	private Long id;

	private String login;
	private String password;

	@Temporal(TemporalType.DATE)
	private Date currentPasswordDate;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_acess", 
	uniqueConstraints = @UniqueConstraint(columnNames = {"users_id", "acess_id"}, name = "unique_acess_users"),
	
	joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id", table = "users", unique = false,
			 	  foreignKey = @ForeignKey(name = "users_fk", value = ConstraintMode.CONSTRAINT)),
	
	inverseJoinColumns = @JoinColumn(name = "acess_id", unique = false, referencedColumnName = "id", table = "acess", 
						 foreignKey = @ForeignKey(name = "acess_fk", value = ConstraintMode.CONSTRAINT)))
	private List<Acess> accesses;

	/* Autoridades > São acessos, ou seja ROLE_ADMIN, ROLE_CLIENT ... */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.accesses;
	}
	
	@ManyToOne(targetEntity = Person.class)
	@JoinColumn(name = "person_id", nullable = false,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "person_fk"))
	private Person person;

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
