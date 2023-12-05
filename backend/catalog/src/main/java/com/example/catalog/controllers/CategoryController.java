package com.example.catalog.controllers;


import com.example.catalog.dtos.CategoryDto;
import com.example.catalog.services.CategoryService;
import com.example.catalog.services.DefaultCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(DefaultCategoryService defaultCategoryService) {
        this.categoryService = defaultCategoryService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<CategoryDto> findAll() {
        final var listCategoryDto = categoryService.findAll();
        return listCategoryDto;
    }


    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public CategoryDto findById(@PathVariable String id) {
        final var categoryDto = categoryService.findById(id);
        return categoryDto;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto insert (@RequestBody CategoryDto dto){
        final var categoryDto = categoryService.insert(dto);
        return categoryDto;
    }


    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto update (@PathVariable String id, @RequestBody CategoryDto dto){
        final var categoryDto = categoryService.update(id, dto);
        return categoryDto;
    }


    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        categoryService.delete(id);
    }

}
