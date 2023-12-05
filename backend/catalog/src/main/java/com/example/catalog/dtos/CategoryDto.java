package com.example.catalog.dtos;

import com.example.catalog.entities.Category;

import java.util.UUID;

public record CategoryDto(UUID id, String name) {
    public static CategoryDto from(Category category) {
        return new CategoryDto(category.getId(), category.getName());

    }


}
