package com.fatihsengun.controller.impl;

import com.fatihsengun.controller.IRestAuthController;
import com.fatihsengun.controller.RestBaseController;
import com.fatihsengun.dto.*;
import com.fatihsengun.model.RootEntity;
import com.fatihsengun.service.IAuthService;
import com.fatihsengun.service.IRefreshTokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class RestAuthControllerImpl extends RestBaseController implements IRestAuthController {

	@Autowired
	private IAuthService authService;

	@Autowired
	private IRefreshTokenService refreshTokenService;

	@Override
	@PostMapping("/register")
	public RootEntity<DtoUser> register(@Valid @RequestBody RegisterRequest authRequest) {
		return ok(authService.register(authRequest));
	}

	@Override
	@PostMapping("/authenticate")
	public RootEntity<AuthResponse> authenticate (@Valid @RequestBody AuthRequest authRequest) {

		return ok(authService.authenticate(authRequest));
	}

	@Override
	@PostMapping("/refreshToken")
	public RootEntity<RefreshResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {

		return ok(refreshTokenService.refreshToken(request));
	}

}
