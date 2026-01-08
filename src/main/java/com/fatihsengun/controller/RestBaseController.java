package com.fatihsengun.controller;

import com.fatihsengun.handler.ApiError;
import com.fatihsengun.model.RootEntity;

public class RestBaseController {

	public <T> RootEntity<T> ok(T payload) {
		return RootEntity.ok(payload);
	}
	
	public <T> RootEntity<T> error(ApiError<?> error) {
		return RootEntity.error(error);
	}
	
	
}

