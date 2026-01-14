package com.fatihsengun.service.impl;

import com.fatihsengun.dto.DtoCar;
import com.fatihsengun.exception.BaseException;
import com.fatihsengun.exception.ErrorMessage;
import com.fatihsengun.exception.MessageType;
import com.fatihsengun.service.IGalleristCarService;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

@Service
public class VectorService {

    @Autowired
    private IGalleristCarService galleristCarService;
    @Autowired
    private PgVectorStore vectorStore;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private ChatModel chatModel;


    public Integer toVector() {
        List<DtoCar> carList = galleristCarService.findDtoGalleristCars();

        Long galleristId = identityService.getCurrentGallerist().getId();
        DecimalFormat priceFormatter = new DecimalFormat("#,###");
        if (carList != null) {
            List<Document> documents = new ArrayList<>();
            List<String> idsToDelete = new ArrayList<>();
            for (DtoCar car : carList) {
                String uniqeId = UUID.nameUUIDFromBytes(String.valueOf(car.getId()).getBytes()).toString();
                if (!car.isCarSaled()) {
                    String formattedPrice = priceFormatter.format((car.getPrice()));

                    String content = "Brand: " + car.getBrand() +
                            "Model: " + car.getModel() +
                            "Production Year: " + car.getProductionYear() +
                            "Price: " + formattedPrice +
                            "Damage Price: " + car.getDamagePrice();
                    Map<String, Object> metadata = new HashMap<>();
                    metadata.put("carId", car.getId());
                    metadata.put("price", formattedPrice);
                    metadata.put("galleristId", galleristId);

                    documents.add(new Document(uniqeId, content, metadata));
                }else{
                    idsToDelete.add(uniqeId);
                }

            }
            vectorStore.add(documents);
            if (!idsToDelete.isEmpty()){
                vectorStore.delete(idsToDelete);
            }


            return documents.size();
        } else {
            throw new BaseException(new ErrorMessage(MessageType.OBJECT_NOT_FOUND, "carList" + galleristId.toString()));
        }


    }

    public List<DtoCar> vectorSearch(String userQuery) {

        String enhancedQuery = enhanceQueryWithAI(userQuery);
        System.out.println(userQuery);
        System.out.println(enhancedQuery);

        List<Document> results = vectorStore.similaritySearch(SearchRequest.builder().query(enhancedQuery).topK(3).build());
        List<DtoCar> response = new ArrayList<>();

        for (Document doc : results) {

            Object carIdObj = doc.getMetadata().get("carId");
            Long carId = Long.parseLong(carIdObj.toString());

            DtoCar cleanCar = galleristCarService.findGalleristCarById(carId);

            if (cleanCar != null) {
                response.add(cleanCar);
            }
        }

        return response;
    }

    public String enhanceQueryWithAI(String query) {
        String prompt = """
                You are a smart translator that converts User Input into a Search Query for a Vehicle Vector Database.
                
                            DATABASE RECORD FORMAT: 'Brand: [Brand], Model: [Model], Year: [Year], Price: [Price in TL], Damage: [Damage]'
                            IMPORTANT: Prices are stored with comma separators (e.g., '15,000,000').
                
                            YOUR GOAL:
                            Analyze the user's intent (Luxury, Economic, Sport, etc.) and generate a search string that contains RELEVANT BRANDS and RELEVANT PRICE POINTS found in the database.
                
                            LOGIC GUIDELINES (Use your own reasoning):
                            1. **PRICE MATCHING (Crucial):**
                               - If User wants 'Ultra Luxury' / 'High End': You MUST generate very high price strings like 'Price: 10,000,000', 'Price: 15,000,000', 'Price: 20,000,000'.
                               - If User wants 'Cheap' / 'Budget': Generate low price strings like 'Price: 500,000', 'Price: 800,000'.
                               - If User mentions specific currency (USD/EUR), convert to TL (approx 1 USD=36 TL) and format with commas.
                
                            2. **BRAND & SEGMENT:**
                               - Do not spam keywords. Just add the most relevant 3-4 brands for that segment based on your world knowledge.
                               - Example: For 'Ultra Luxury', add 'Porsche, Ferrari, Maserati, Bentley'.
                               - Example: For 'Reliable', add 'Toyota, Honda, Volvo'.
                
                            3. **OUTPUT:**
                               - Output ONLY the generated search string. No explanations.
                
                            User Input: %s
                """.formatted(query);
        return chatModel.call(prompt);

    }

}