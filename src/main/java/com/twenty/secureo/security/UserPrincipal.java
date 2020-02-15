package com.twenty.secureo.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.twenty.secureo.domain.User;

@SuppressWarnings("serial")
public class UserPrincipal implements UserDetails {

	private Long id;

    private String lastName;
    
    private String firstName;

    private String username;

	@JsonIgnore
    private String email;

    @JsonIgnore
    private String password;

    private Collection<GrantedAuthority> authorities;

    public UserPrincipal(Long id, String lastName, String firstName, String username, String email, String password, Collection<GrantedAuthority> authorities) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = user.getAuthorities().stream().map(role ->
                new SimpleGrantedAuthority(role.getName())
        ).collect(Collectors.toList());

        return new UserPrincipal(
        		user.getId(),
        		user.getLastName(),
        		user.getUsername(),
        		user.getEmail(),
        		user.getPassword(),
                user.getEmail(),
                authorities
        );
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return isEnabled();
	}

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserPrincipal other = (UserPrincipal) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserPrincipal [lastName=" + lastName + ", firstName=" + firstName + ", username=" + username
				+ ", email=" + email + "]";
	}


}
