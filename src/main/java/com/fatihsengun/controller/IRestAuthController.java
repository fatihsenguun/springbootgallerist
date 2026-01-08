package com.fatihsengun.controller;

import com.fatihsengun.dto.AuthRequest;
import com.fatihsengun.dto.AuthResponse;
import com.fatihsengun.dto.DtoUser;
import com.fatihsengun.dto.RefreshTokenRequest;
import com.fatihsengun.model.RootEntity;

public interface IRestAuthController {

	public RootEntity<DtoUser> register(AuthRequest authRequest);
	public RootEntity<AuthResponse> authenticate (AuthRequest authRequest);
	public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest request);
	
	
}
