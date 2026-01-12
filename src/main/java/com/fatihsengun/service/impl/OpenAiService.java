package com.fatihsengun.service.impl;

import com.fatihsengun.exception.BaseException;
import com.fatihsengun.exception.ErrorMessage;
import com.fatihsengun.exception.MessageType;
import com.fatihsengun.model.Car;
import com.fatihsengun.repository.CarRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OpenAiService {

	private final ChatClient chatClient;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private VectorService vectorService;
    @Autowired
    private ChatModel chatModel;

	public OpenAiService(ChatClient.Builder builder) {
		this.chatClient = builder.build();

	}

	public String askToAi(String prompt) {
		return chatClient.prompt().user(prompt).call().content();
	}

	public String generateCarDescriptionWithId(Long carId) {
		Car car = carRepository.findById(carId)
				.orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.OBJECT_NOT_FOUND, carId.toString())));
		
		String brand =car.getBrand();
		String model = car.getModel();
		String year = car.getProductionYear().toString();
		String price = car.getPrice().toString();
		

		String prompt = String.format("""
				Act as a professional car dealership copywriter.
            Write a short, exciting, and professional sales listing for the following vehicle:
            
            Vehicle Details:
            - Brand: %s
            - Model: %s
            - Year: %s
            - Price: %s TL
            
            Constraints:
            1. Language: English.
            2. Style: Use emojis, be persuasive but professional.
            3. CRITICAL: Output ONLY the advertisement text. Do not write intro sentences like "Here is the description". Start directly with a catchy headline.
				""",brand, model, year, price );

		return chatClient.prompt().user(prompt).call().content();
	}

	public String aiSalesConsultant(String query){
List<String> response = vectorService.vectorSearch(query);
if (response==null){
	return "I cant find any car";
}
String carContext = String.join("\n",response);

String prompt =
		"""
				Sen çok yetenekli, kibar ve ikna edici bir araba satış danışmanısın.
				Müşterinin sorusu: {question}
				
				Elimizdeki stok durumu (sadece bunları öner) : {context}
				
				Lütfen bu araçları kullanarak müşteriye cevap ver. araçların özelliklerini
				öv ve müşteriyi showrooma davet et. Cevabın ingilizce olsun.
				
				 CRITICAL: Output ONLY the advertisement text. Do not write intro sentences like "Here is the description". Start directly with a catchy headline.
	
				""";
		PromptTemplate template = new PromptTemplate(prompt);

		var promptVar = template.create(Map.of(
				"question", query,
				"context", carContext
		));
		return chatModel.call(promptVar).getResult().getOutput().getContent();




	}

}
