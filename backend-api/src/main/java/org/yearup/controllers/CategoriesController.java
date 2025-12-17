package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.util.List;

@RestController
@RequestMapping("categories")
@CrossOrigin
public class CategoriesController {

    private CategoryDao categoryDao;
    private ProductDao productDao;

    @Autowired
    public CategoriesController(CategoryDao categoryDao, ProductDao productDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    // GET /categories
    @GetMapping
    @PreAuthorize("permitAll()")
    public List<Category> getAll() {
        return categoryDao.getAllCategories();
    }

    // GET /categories/{id}
    @GetMapping("{id}")
    @PreAuthorize("permitAll()")
    public Category getById(@PathVariable int id) {
        Category category = categoryDao.getById(id);

        if (category == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return category;
    }

    // GET /categories/{id}/products
    @GetMapping("{id}/products")
    @PreAuthorize("permitAll()")
    public List<Product> getProductsByCategory(@PathVariable int id) {
        return productDao.listByCategoryId(id);
    }

    // POST /categories (ADMIN)
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Category addCategory(@RequestBody Category category) {
        return categoryDao.create(category);
    }

    // PUT /categories/{id} (ADMIN)
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCategory(@PathVariable int id, @RequestBody Category category) {
        categoryDao.update(id, category);
    }

    // DELETE /categories/{id} (ADMIN)
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable int id) {
        categoryDao.delete(id);
    }
}
