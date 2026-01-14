package com.fatihsengun.controller.impl;

import com.fatihsengun.dto.DtoCar;
import com.fatihsengun.model.RootEntity;
import com.fatihsengun.service.impl.VectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api")
public class RestVectorController {

    @Autowired
    private VectorService vectorService;

    @GetMapping("/vector")
    public RootEntity<Integer> toVector(){

        return RootEntity.ok(vectorService.toVector());
    }

    @PostMapping("/vector/search")
    public  RootEntity<List<DtoCar>> vectorSearch(@RequestBody String query){
        return  RootEntity.ok(vectorService.vectorSearch(query));
    }


}

