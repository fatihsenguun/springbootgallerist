package com.fatihsengun.controller;

import com.fatihsengun.dto.DtoUser;
import com.fatihsengun.jwt.AuthRequest;
import com.fatihsengun.jwt.AuthResponse;
import com.fatihsengun.jwt.RefreshTokenRequest;
import com.fatihsengun.model.RefreshToken;
import com.fatihsengun.model.RootEntity;

public interface IRestAuthController {

	public RootEntity<DtoUser> register(AuthRequest authRequest);
	public RootEntity<AuthResponse> authenticate (AuthRequest authRequest);
	public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest request);
	
	
}
