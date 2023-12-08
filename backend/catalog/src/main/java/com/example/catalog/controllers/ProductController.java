package com.example.catalog.controllers;

import com.example.catalog.dtos.ProductDto;
import com.example.catalog.services.DefaultProductService;
import com.example.catalog.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(DefaultProductService defaultProductService) {
        this.productService = defaultProductService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Page<ProductDto> findAll(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction,
            @RequestParam(name = "orderBy", defaultValue = "price") String orderBy

    ) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return productService.findAll(pageRequest);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ProductDto findById(@PathVariable String id) {
        return productService.findById(id);
    }


    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ProductDto insert(@RequestBody ProductDto dto) {
        return productService.insert(dto);
    }


    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto update(@PathVariable String id, @RequestBody ProductDto dto) {
        return productService.update(id, dto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable  String id) {
        productService.delete(id);
    }
}
