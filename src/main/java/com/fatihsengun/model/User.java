package com.fatihsengun.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fatihsengun.enums.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity implements UserDetails {
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;

	@Column(name="role")
	@Enumerated(EnumType.STRING)
	private RoleType role;

	@JoinColumn(name = "gallerist")
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Gallerist gallerist;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	

}
