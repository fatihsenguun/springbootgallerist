package com.fatihsengun.service;

import com.fatihsengun.jwt.AuthResponse;
import com.fatihsengun.jwt.RefreshTokenRequest;
import com.fatihsengun.model.RefreshToken;
import com.fatihsengun.model.User;


public interface IRefreshTokenService {
	public RefreshToken saveRefreshToken(User user);
	public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
