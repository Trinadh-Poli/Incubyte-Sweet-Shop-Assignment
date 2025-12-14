package com.incubyte.sweetshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.incubyte.sweetshop.model.Sweet;
import com.incubyte.sweetshop.repository.SweetRepository;

@Service
public class SweetService {
    private final SweetRepository sweetRepository;

    public SweetService(SweetRepository sweetRepository) {
        this.sweetRepository = sweetRepository;
    }

    public Sweet addSweet(Sweet sweet) {
        return sweetRepository.save(sweet);
    }

    public List<Sweet> getAllSweets() {
        return sweetRepository.findAll();
    }

    public Sweet purchaseSweet(Long id) {
        Sweet sweet = sweetRepository.findById(id).orElseThrow(() -> new RuntimeException("Sweet not found"));
        if (sweet.getQuantity() > 0) {
            sweet.setQuantity(sweet.getQuantity() - 1);
            return sweetRepository.save(sweet);
        } else {
            throw new RuntimeException("Out of stock");
        }
    }
}
