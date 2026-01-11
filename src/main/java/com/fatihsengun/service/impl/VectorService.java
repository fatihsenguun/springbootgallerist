package com.fatihsengun.service.impl;

import com.fatihsengun.dto.DtoCar;
import com.fatihsengun.exception.BaseException;
import com.fatihsengun.exception.ErrorMessage;
import com.fatihsengun.exception.MessageType;
import com.fatihsengun.service.IGalleristCarService;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VectorService {

    @Autowired
    private IGalleristCarService galleristCarService;
    @Autowired
    private PgVectorStore vectorStore;


    public Integer toVector(Long id) {
        List<DtoCar> carList = galleristCarService.findDtoGalleristCars(id);

        if (carList != null) {
            List<Document> documents = new ArrayList<>();
            for (DtoCar car : carList){
                String content = "Brand: "+car.getBrand()+
                                 "Model: "+car.getModel()+
                        "Production Year: "+car.getProductionYear()+
                        "Price: "+car.getPrice()+
                        "Damage Price: "+car.getDamagePrice();
                Map<String, Object> metadata = new HashMap<>();
                metadata.put("carId",car.getId());
                metadata.put("price",car.getPrice());

                documents.add(new Document(content,metadata));
            }
            vectorStore.add(documents);
            System.out.println(documents.size());

            return documents.size();
        }else{
            throw new BaseException(new ErrorMessage(MessageType.OBJECT_NOT_FOUND,"carList"+id));
        }



    }
}