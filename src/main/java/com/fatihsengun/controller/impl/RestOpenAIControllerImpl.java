package com.fatihsengun.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fatihsengun.controller.IRestOpenAIController;
import com.fatihsengun.controller.RestBaseController;
import com.fatihsengun.model.RootEntity;
import com.fatihsengun.service.impl.OpenAiService;

@RestController
@RequestMapping("/rest/api/openai")
public class RestOpenAIControllerImpl extends RestBaseController implements IRestOpenAIController {

	@Autowired
	private OpenAiService openAiService;

	@Override
	@GetMapping("/message")
	public RootEntity<String> getAnswer(@RequestBody String question) {

		return ok(openAiService.askToAi(question));
	}

	@Override
	@GetMapping("/description/car/{id}")
	public RootEntity<String> generateCarDescriptionWithId(@PathVariable(name = "id") Long id) {

		return ok(openAiService.generateCarDescriptionWithId(id));
	}

	@PostMapping("/chat")
	public RootEntity<String> aiSalesConsultant(@RequestBody String query){

		return ok(openAiService.aiSalesConsultant(query));
	}

}
