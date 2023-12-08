package com.example.catalog.services;

import com.example.catalog.dtos.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


public interface ProductService {

    Page<ProductDto> findAll(PageRequest pageRequest);

    ProductDto findById(String id);

    ProductDto insert (ProductDto dto);

    ProductDto update (String id, ProductDto dto);

    void delete (String id);
}
