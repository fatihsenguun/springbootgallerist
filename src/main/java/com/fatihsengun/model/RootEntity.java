package com.fatihsengun.model;

import com.fatihsengun.handler.ApiError;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RootEntity<T>{
	
	private boolean result;
	
	private ApiError<?> errorMessage;
	
	private T data;
	
	public static <T> RootEntity<T> ok(T data){
		
		RootEntity<T> rootEntity = new RootEntity<>();
		rootEntity.setResult(true);
		rootEntity.setErrorMessage(null);
		rootEntity.setData(data);
		return rootEntity;
	}
	public static <T> RootEntity<T> error(ApiError<?> error){
		RootEntity<T> rootEntity = new RootEntity<>();
		
		rootEntity.setResult(false);
		rootEntity.setErrorMessage(error);
		rootEntity.setData(null);
		return rootEntity;
	}

}
