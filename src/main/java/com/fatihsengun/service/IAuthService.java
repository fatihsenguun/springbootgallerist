package com.fatihsengun.service;

import com.fatihsengun.dto.DtoUser;
import com.fatihsengun.jwt.AuthRequest;
import com.fatihsengun.jwt.AuthResponse;
import com.fatihsengun.jwt.RefreshTokenRequest;

public interface IAuthService {
	public DtoUser register(AuthRequest authRequest);
	public AuthResponse authenticate(AuthRequest authRequest);
	

}
