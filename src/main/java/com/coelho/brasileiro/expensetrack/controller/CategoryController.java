package com.coelho.brasileiro.expensetrack.controller;


import com.coelho.brasileiro.expensetrack.constants.Params;
import com.coelho.brasileiro.expensetrack.input.CategoryInput;
import com.coelho.brasileiro.expensetrack.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryInput categoryInput) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.categoryService.create(categoryInput));
    }

    @PutMapping(path = "/{id:^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-4[0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$}")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryInput categoryInput, @PathVariable("id") String id) {
        CategoryInput input = new CategoryInput(
                categoryInput.getName(),
                categoryInput.getDescription(),
                categoryInput.getColor(),
                categoryInput.getType(),
                id);
        return ResponseEntity.status(HttpStatus.OK).body(this.categoryService.update(input));
    }

    @GetMapping
    public ResponseEntity<?> findAllCategories(@RequestParam(value = Params.NAME, required = false) String name,
                                               @RequestParam(value = Params.DESCRIPTION, required = false) String description,
                                               @RequestParam(value = Params.TYPE, required = false) String type,
                                               @RequestParam(value = "pageNo", defaultValue = "0", required = false) String pageNo,
                                               @RequestParam(value = "pageSize", defaultValue = "10", required = false) String pageSize,
                                               @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
                                               @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        Map<String, String> params = new HashMap<>();
        params.put(Params.NAME, name);
        params.put(Params.DESCRIPTION, description);
        params.put(Params.TYPE, type);
        params.put(Params.NO_PAGE, pageNo);
        params.put(Params.PAGE_SIZE, pageSize);
        params.put(Params.SORT_BY, sortBy);
        params.put(Params.SORT_DIR, sortDir);
        return ResponseEntity.ok(this.categoryService.findAll(params));
    }

    @DeleteMapping(path = "/{id:^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-4[0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        CategoryInput input = new CategoryInput(null, null, null, null, id);
        this.categoryService.delete(input);
        return ResponseEntity.ok().build();
    }


}
