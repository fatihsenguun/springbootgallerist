package com.fatihsengun.controller;

import com.fatihsengun.model.RootEntity;

public interface IRestOpenAIController {
	
	RootEntity<String> getAnswer(String question);
	RootEntity<String> generateCarDescriptionWithId(Long id);


}
