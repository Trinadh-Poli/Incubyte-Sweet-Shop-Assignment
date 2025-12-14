package com.incubyte.sweetshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incubyte.sweetshop.model.Sweet;

public interface SweetRepository extends JpaRepository<Sweet, Long> {
    List<Sweet> findByNameContainingIgnoreCase(String name);
}
