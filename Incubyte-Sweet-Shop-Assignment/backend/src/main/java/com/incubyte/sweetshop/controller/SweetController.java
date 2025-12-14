package com.incubyte.sweetshop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incubyte.sweetshop.model.Sweet;
import com.incubyte.sweetshop.service.SweetService;

@RestController
@RequestMapping("/api/sweets")
public class SweetController {

    private final SweetService sweetService;

    public SweetController(SweetService sweetService) {
        this.sweetService = sweetService;
    }

    @PostMapping
    public ResponseEntity<Sweet> addSweet(@RequestBody Sweet sweet) {
        return new ResponseEntity<>(sweetService.addSweet(sweet), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Sweet>> getAllSweets() {
        return ResponseEntity.ok(sweetService.getAllSweets());
    }

    @PostMapping("/{id}/purchase")
    public ResponseEntity<Sweet> purchaseSweet(@PathVariable Long id) {
        return ResponseEntity.ok(sweetService.purchaseSweet(id));
    }
}
