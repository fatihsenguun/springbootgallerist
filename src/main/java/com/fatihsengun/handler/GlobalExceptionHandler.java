package com.fatihsengun.handler;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.fatihsengun.controller.RestBaseController;
import com.fatihsengun.exception.BaseException;
import com.fatihsengun.model.RootEntity;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.UnexpectedTypeException;

@ControllerAdvice
public class GlobalExceptionHandler extends RestBaseController {

	@ExceptionHandler(value = BaseException.class)
	public ResponseEntity<RootEntity<?>> handleBaseException(BaseException exception, WebRequest request) {
		return ResponseEntity.badRequest().body(error(createApiError(exception.getMessage(), request, null)));

	}

	@ExceptionHandler(value = AccessDeniedException.class)
	public ResponseEntity<RootEntity<?>> accessDeniedHandler(AccessDeniedException exception, WebRequest request) {
		return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED)
				.body(error(createApiError(exception.getMessage(), request, HttpServletResponse.SC_UNAUTHORIZED)));
	}
	@ExceptionHandler(value = UnexpectedTypeException.class)
	public ResponseEntity<RootEntity<?>> unexpectedTypeException(UnexpectedTypeException exception, WebRequest request) {
		return ResponseEntity.badRequest().body(error(createApiError(exception.getMessage(), request, null)));
	}

	@ExceptionHandler(value = AuthenticationException.class)
	public ResponseEntity<RootEntity<?>> ioException(AuthenticationException exception, WebRequest request) {
		return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED)
				.body(error(createApiError(exception.getMessage(), request, HttpServletResponse.SC_UNAUTHORIZED)));
	}

	@ExceptionHandler(value = BadCredentialsException.class)
	public ResponseEntity<RootEntity<?>> badCredentialException(BadCredentialsException exception, WebRequest request) {
		return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED)
				.body(error(createApiError(exception.getMessage(), request, HttpServletResponse.SC_UNAUTHORIZED)));
	}

	@ExceptionHandler(value = ExpiredJwtException.class)
	public ResponseEntity<RootEntity<?>> expiredJwtException(ExpiredJwtException exception, WebRequest request) {

		return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED)
				.body(error(createApiError(exception.getMessage(), request, HttpServletResponse.SC_UNAUTHORIZED)));
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<RootEntity<?>> handleValidationException(MethodArgumentNotValidException exception,
			WebRequest request) {
		Map<String, List<String>> errorsMap = new HashMap<>();

		for (ObjectError objectError : exception.getBindingResult().getAllErrors()) {
			String fieldName = ((FieldError) objectError).getField();
			if (errorsMap.containsKey(fieldName)) {
				errorsMap.put(fieldName, addList(errorsMap.get(fieldName), objectError.getDefaultMessage()));
			} else {
				errorsMap.put(fieldName, addList(new ArrayList<>(), objectError.getDefaultMessage()));
			}

		}
		return ResponseEntity.badRequest().body(error(createApiError(errorsMap, request, null)));

	}

	public List<String> addList(List<String> list, String newValue) {
		list.add(newValue);
		return list;

	}

	public String getHostName() {

		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			System.out.println("Hostname not found. " + e);
		}
		return null;
	}

	public <E> ApiError<E> createApiError(E message, WebRequest request, Integer statusCode) {
		ApiError<E> apiError = new ApiError<>();

		apiError.setStatus(statusCode);
		if (statusCode == null) {
			apiError.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}

		Exception<E> exceptionResponse = new Exception<>();

		exceptionResponse.setHostName(getHostName());
		exceptionResponse.setPath(request.getDescription(false));
		exceptionResponse.setCreateTime(new Date());
		exceptionResponse.setMessage(message);

		apiError.setException(exceptionResponse);
		return apiError;

	}

}
