package com.twenty.secureo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twenty.secureo.domain.User;
import com.twenty.secureo.repository.UserRepository;

/**
 * Authenticate a user from the database.
 */
@Service
public class DomainUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findOneByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Utilisateur inconnu avec username : " + username));
		if(user.isPasswordExpired())
			throw new BadCredentialsException("Mot de passe expiré.");
		if(!user.isEnabled() && user.isDeleted())
			throw new BadCredentialsException("Utilisateur [" + username + "] désactivé ");
		return UserPrincipal.create(user);
	}

	public UserDetails loadUserById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("Utilisateur inconnu avec ID : " + id));
		if(user.isPasswordExpired())
			throw new BadCredentialsException("Mot de passe expiré pour utilisateur avec ID : " + id);
		if(!user.isEnabled() && user.isDeleted())
			throw new BadCredentialsException("Utilisateur avec id [" + id + "] désactivé ");
		return UserPrincipal.create(user);
	}

}
