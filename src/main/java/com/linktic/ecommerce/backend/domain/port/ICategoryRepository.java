package com.linktic.ecommerce.backend.domain.port;

import com.linktic.ecommerce.backend.domain.model.Category;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


public interface ICategoryRepository{
    Category save(Category category);
    Iterable<Category> findAll();
    Category findById(Integer id);
    void deleteById(Integer id);
}
