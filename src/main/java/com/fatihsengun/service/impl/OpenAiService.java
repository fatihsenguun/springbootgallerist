package com.fatihsengun.service.impl;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatihsengun.exception.BaseException;
import com.fatihsengun.exception.ErrorMessage;
import com.fatihsengun.exception.MessageType;
import com.fatihsengun.model.Car;
import com.fatihsengun.repository.CarRepository;

@Service
public class OpenAiService {

	private final ChatClient chatClient;

	@Autowired
	private CarRepository carRepository;

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

}
