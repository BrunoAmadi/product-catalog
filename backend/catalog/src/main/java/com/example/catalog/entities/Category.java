package com.example.catalog.entities;

import com.example.catalog.dtos.CategoryDto;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "tb_category")
public class Category {

    @Id
    @Column(name = "id_category")
    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @Column(name = "nm_category")
    private String name;

    public Category() {
    }

    public Category(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
