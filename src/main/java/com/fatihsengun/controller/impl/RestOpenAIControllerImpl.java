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
	@GetMapping("/description/car/{plate}")
	public RootEntity<String> generateCarDescriptionWithPlate(@PathVariable(name = "plate") String plate) {

		return ok(openAiService.generateCarDescriptionWithPlate(plate));
	}

	@PostMapping("/chat")
	public RootEntity<String> aiSalesConsultant(@RequestBody String query){

		return ok(openAiService.aiSalesConsultant(query));
	}

}
