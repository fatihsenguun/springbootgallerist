package com.fatihsengun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatihsengun.model.RefreshToken;
import java.util.List;
import java.util.Optional;


@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	
	public Optional<RefreshToken> findByRefreshToken(String refreshToken);
	public Optional<RefreshToken> findByUserId(Long userId);

}
