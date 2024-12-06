package com.aritizate.controller;

import com.aritizate.model.Pqrs;
import com.aritizate.service.PqrsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pqrs")
public class PqrsController {

    @Autowired
    private PqrsService pqrsService;

    @PostMapping
    public ResponseEntity<Pqrs> createPqrs(@RequestBody Pqrs pqrs) {
        return ResponseEntity.ok(pqrsService.savePqrs(pqrs));
    }
}
