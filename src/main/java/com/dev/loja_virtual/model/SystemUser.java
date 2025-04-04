package com.dev.loja_virtual.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "system_user")
@SequenceGenerator(name = "seq_users", sequenceName = "seq_users", initialValue = 1, allocationSize = 1)
public class SystemUser implements UserDetails {

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

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "system_user_acess", 
	uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "acess_id"}, name = "unique_acess_users"),
	
	joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", table = "system_user", unique = false,
			 	  foreignKey = @ForeignKey(name = "system_user_fk", value = ConstraintMode.CONSTRAINT)),
	
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
	
	@ManyToOne(targetEntity = Person.class)
	@JoinColumn(name = "company_id", nullable = false, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "company_fk"))
	private Person company;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCurrentPasswordDate() {
		return currentPasswordDate;
	}

	public void setCurrentPasswordDate(Date currentPasswordDate) {
		this.currentPasswordDate = currentPasswordDate;
	}

	public List<Acess> getAccesses() {
		return accesses;
	}

	public void setAccesses(List<Acess> accesses) {
		this.accesses = accesses;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	public Person getCompany() {
		return company;
	}

	public void setCompany(Person company) {
		this.company = company;
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

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

}
