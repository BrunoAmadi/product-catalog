package com.example.catalog.dtos;

import com.example.catalog.entities.Category;
import com.example.catalog.entities.Product;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public record ProductDto(
        UUID id,
        String name,
        Double price,
        String description,
        Instant date,
        String imgUrl,

        Set<CategoryDto> categories

) {
    public static ProductDto from(Product product){
        return  new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getDate(),
                product.getImgUrl(),
                new HashSet<CategoryDto>()
        );
    }
    public static ProductDto from(Product product, Set<Category> categories){
        Set<CategoryDto> setCategoryDto = new HashSet<>();
        categories.forEach(cat -> setCategoryDto.add(new CategoryDto(cat.getId(),cat.getName())));

        return  new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getDate(),
                product.getImgUrl(),
                setCategoryDto

        );
    }

}
