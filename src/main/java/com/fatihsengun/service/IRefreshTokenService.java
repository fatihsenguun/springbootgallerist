package com.fatihsengun.service;

import com.fatihsengun.dto.RefreshResponse;
import com.fatihsengun.dto.RefreshTokenRequest;
import com.fatihsengun.model.RefreshToken;
import com.fatihsengun.model.User;


public interface IRefreshTokenService {
	public RefreshToken saveRefreshToken(User user);
	public RefreshResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
