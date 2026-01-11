package com.fatihsengun.controller.impl;

import com.fatihsengun.model.RootEntity;
import com.fatihsengun.service.impl.VectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api")
public class RestVectorController {

    @Autowired
    private VectorService vectorService;

    @GetMapping("/vector/{id}")
    public RootEntity<Integer> toVector(@PathVariable(name = "id") Long id){

        return RootEntity.ok(vectorService.toVector(id));
    }
}
