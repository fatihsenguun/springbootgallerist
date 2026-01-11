package com.fatihsengun.controller.impl;

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

    @GetMapping("/vector/{id}")
    public RootEntity<Integer> toVector(@PathVariable(name = "id") Long id){

        return RootEntity.ok(vectorService.toVector(id));
    }

    @PostMapping("/vector/search")
    public  RootEntity<List<String>> vectorSearch(@RequestBody String query){
        return  RootEntity.ok(vectorService.vectorSearch(query));
    }


}

