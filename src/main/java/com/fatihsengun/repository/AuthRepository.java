package com.fatihsengun.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatihsengun.model.RefreshToken;
import com.fatihsengun.model.User;


@Repository
public interface AuthRepository extends JpaRepository<User, Long> {
	
	
	Optional<User> findByUsername(String username);
	boolean existsByUsername(String username);


}
