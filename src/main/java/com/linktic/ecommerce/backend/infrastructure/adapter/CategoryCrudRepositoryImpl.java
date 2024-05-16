package com.linktic.ecommerce.backend.infrastructure.adapter;

import com.linktic.ecommerce.backend.domain.model.Category;
import com.linktic.ecommerce.backend.domain.port.ICategoryRepository;
import com.linktic.ecommerce.backend.infrastructure.mapper.CategoryMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CategoryCrudRepositoryImpl implements ICategoryRepository {
    private final ICategoryCrudRepository iCategoryCrudRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Category save(Category category) {
        return categoryMapper.toCategory( iCategoryCrudRepository.save(categoryMapper.toCategoryEntity(category)));
    }

    @Override
    public Iterable<Category> findAll() {
        return categoryMapper.toCategoryList(iCategoryCrudRepository.findAll());
    }

    @Override
    public Category findById(Integer id) {
        return categoryMapper.toCategory(iCategoryCrudRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Categoría con id: "+id+ " no existe")
        ));
    }

    @Override
    public void deleteById(Integer id) {
        iCategoryCrudRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Categoría con id: "+id+ " no existe")
        );
        iCategoryCrudRepository.deleteById(id);
    }
}
