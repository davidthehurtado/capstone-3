package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import javax.annotation.security.PermitAll;
import java.util.List;

// added the annotations to make this a REST controller
@RestController
@RequestMapping("categories")
@CrossOrigin
public class CategoriesController
{
    private CategoryDao categoryDao;
    private ProductDao productDao;


    // created an Autowired controller to inject the categoryDao and ProductDao

    @Autowired
    public CategoriesController(CategoryDao categoryDao, ProductDao productDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;

    }

    // GET /categories
    @GetMapping
    public List<Category> getAll()
    {
        return categoryDao.getAllCategories();
    }

    // GET /categories/{id}
    @GetMapping("{id}")
    public Category getById(@PathVariable int id)
    {
        return categoryDao.getById(id);
    }

    // GET /categories/{id}/products
    @GetMapping("{id}/products")
    public List<Product> getProductsByCategory(@PathVariable int id)
    {
        return productDao.listByCategoryId(id);
    }


}
