package com.example.catalog.controllers;


import com.example.catalog.dtos.CategoryDto;
import com.example.catalog.services.CategoryService;
import com.example.catalog.services.DefaultCategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public Page<CategoryDto> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy

    ) {
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        final var listCategoryDto = categoryService.findAll(pageRequest);
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
