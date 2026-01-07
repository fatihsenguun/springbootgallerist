package com.fatihsengun.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fatihsengun.controller.IRestAuthController;
import com.fatihsengun.dto.DtoUser;
import com.fatihsengun.jwt.AuthRequest;
import com.fatihsengun.jwt.AuthResponse;
import com.fatihsengun.jwt.RefreshTokenRequest;
import com.fatihsengun.model.RefreshToken;
import com.fatihsengun.model.RootEntity;
import com.fatihsengun.service.IAuthService;
import com.fatihsengun.service.IRefreshTokenService;

@RestController
public class RestAuthControllerImpl implements IRestAuthController {

	@Autowired
	private IAuthService authService;

	@Autowired
	private IRefreshTokenService refreshTokenService;

	@Override
	@PostMapping("/register")
	public RootEntity<DtoUser> register(@RequestBody AuthRequest authRequest) {
		return RootEntity.ok(authService.register(authRequest));
	}

	@Override
	@PostMapping("/authenticate")
	public RootEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest) {

		return RootEntity.ok(authService.authenticate(authRequest));
	}

	@Override
	@PostMapping("/refreshToken")
	public RootEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest request) {

		return RootEntity.ok(refreshTokenService.refreshToken(request));
	}

}
