package com.twenty.secureo.repository;

import org.springframework.stereotype.Repository;

import com.twenty.secureo.domain.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the {@link User} entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findOneByUsername(String username);

}
