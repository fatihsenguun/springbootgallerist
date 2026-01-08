package com.fatihsengun.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fatihsengun.controller.IRestAuthController;
import com.fatihsengun.controller.RestBaseController;
import com.fatihsengun.dto.AuthRequest;
import com.fatihsengun.dto.AuthResponse;
import com.fatihsengun.dto.DtoUser;
import com.fatihsengun.dto.RefreshTokenRequest;
import com.fatihsengun.model.RootEntity;
import com.fatihsengun.service.IAuthService;
import com.fatihsengun.service.IRefreshTokenService;

import jakarta.validation.Valid;

@RestController
public class RestAuthControllerImpl extends RestBaseController implements IRestAuthController {

	@Autowired
	private IAuthService authService;

	@Autowired
	private IRefreshTokenService refreshTokenService;

	@Override
	@PostMapping("/register")
	public RootEntity<DtoUser> register(@Valid @RequestBody AuthRequest authRequest) {
		return ok(authService.register(authRequest));
	}

	@Override
	@PostMapping("/authenticate")
	public RootEntity<AuthResponse> authenticate (@Valid @RequestBody AuthRequest authRequest) {

		return ok(authService.authenticate(authRequest));
	}

	@Override
	@PostMapping("/refreshToken")
	public RootEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {

		return ok(refreshTokenService.refreshToken(request));
	}

}
