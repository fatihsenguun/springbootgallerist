package com.fatihsengun.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatihsengun.model.RefreshToken;


@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	
	public Optional<RefreshToken> findByRefreshToken(String refreshToken);
	public Optional<RefreshToken> findByUserId(Long userId);

}
