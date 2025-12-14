package com.incubyte.sweetshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sweets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String category;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer quantity;
}
