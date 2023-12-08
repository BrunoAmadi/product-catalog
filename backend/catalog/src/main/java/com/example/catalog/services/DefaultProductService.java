package com.example.catalog.services;

import com.example.catalog.dtos.ProductDto;
import com.example.catalog.entities.Product;
import com.example.catalog.repositories.CategoryRepository;
import com.example.catalog.repositories.ProductRepository;
import com.example.catalog.services.exceptions.InvalidUuidException;
import com.example.catalog.services.exceptions.NotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultProductService implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public DefaultProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    public Page<ProductDto> findAll(PageRequest pageRequest) {
        final var listPaged = productRepository.findAll(pageRequest);
        return listPaged.map(prod -> ProductDto.from(prod, prod.getCategories()));
    }


    public ProductDto findById(String id) {
        if (!isValidUIID(id)) {
            throw new InvalidUuidException("Invalid id");
        }
        final var product = productRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("Resource not found"));

        return ProductDto.from(product, product.getCategories());
    }


    public ProductDto insert(ProductDto dto) {
        var product = new Product();
        copyDTOtoEntity(dto, product);
        product = productRepository.save(product);
        return ProductDto.from(product,product.getCategories());
    }


    public ProductDto update(String id, ProductDto dto) {
        try {
            if (!isValidUIID(id)) {
                throw new InvalidUuidException("id is not valid");
            }

            final var product = productRepository.getReferenceById(UUID.fromString(id));
            copyDTOtoEntity(dto, product);
            final var productSaved = productRepository.save(product);
            return ProductDto.from(productSaved,productSaved.getCategories());

        } catch (EntityNotFoundException e) {
            throw new NotFoundException("Entity not found");
        }
    }

    public void delete(String id) {
        if (!isValidUIID(id)) {
            throw new InvalidUuidException("Id is not valid");
        }
        productRepository.deleteById(UUID.fromString(id));
    }


    private void copyDTOtoEntity(ProductDto dto, Product product) {
        product.setName(dto.name());
        product.setDate(dto.date());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setImgUrl(dto.imgUrl());

        product.getCategories().clear();
        dto.categories().forEach(catDto -> {
            var category = categoryRepository.getReferenceById(catDto.id());
            product.getCategories().add(category);
        });

    }


    private boolean isValidUIID(String id) {
        try {
            final var uuid = UUID.fromString(id);
            return true;

        } catch (IllegalArgumentException e) {
            return false;
        }

    }


}


