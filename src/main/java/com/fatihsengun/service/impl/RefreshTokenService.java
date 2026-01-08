package com.fatihsengun.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatihsengun.dto.AuthResponse;
import com.fatihsengun.dto.RefreshTokenRequest;
import com.fatihsengun.exception.BaseException;
import com.fatihsengun.exception.ErrorMessage;
import com.fatihsengun.exception.MessageType;
import com.fatihsengun.jwt.JwtService;
import com.fatihsengun.model.RefreshToken;
import com.fatihsengun.model.User;
import com.fatihsengun.repository.RefreshTokenRepository;
import com.fatihsengun.service.IRefreshTokenService;

@Service
public class RefreshTokenService implements IRefreshTokenService {

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private JwtService jwtService;

	@Override
	public RefreshToken saveRefreshToken(User user) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		refreshToken.setUser(user);
		refreshToken.setCreateTime(new Date());
		refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4));
		
		return refreshTokenRepository.save(refreshToken);

	}

	public boolean isRefreshTokenExpired(Date expireDate) {
		return new Date().after(expireDate);
	}

	@Override
	public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {

		Optional<RefreshToken> optional = refreshTokenRepository
				.findByRefreshToken(refreshTokenRequest.getRefreshToken());
		if (optional.isEmpty()) {
			throw new BaseException(
					new ErrorMessage(MessageType.NO_RECORD_EXIST, refreshTokenRequest.getRefreshToken()));
		}
		RefreshToken refreshToken = optional.get();

		if (isRefreshTokenExpired(refreshToken.getExpiredDate())) {
			throw new BaseException(new ErrorMessage(MessageType.TOKEN_EXPIRED, refreshToken.getRefreshToken()));

		}
		String accessToken = jwtService.generateToken(refreshToken.getUser());

		RefreshToken savedRefreshToken = saveRefreshToken(refreshToken.getUser());
		refreshTokenRepository.delete(refreshToken);

		return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
	}

}
