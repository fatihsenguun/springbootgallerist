package com.fatihsengun.controller;

import com.fatihsengun.dto.*;
import com.fatihsengun.model.RootEntity;

public interface IRestAuthController {

	public RootEntity<DtoUser> register(RegisterRequest authRequest);
	public RootEntity<AuthResponse> authenticate (AuthRequest authRequest);
	public RootEntity<RefreshResponse> refreshToken(RefreshTokenRequest request);
	
	
}
