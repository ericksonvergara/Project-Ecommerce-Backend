package com.linktic.ecommerce.backend.application;

import com.linktic.ecommerce.backend.domain.model.Product;
import com.linktic.ecommerce.backend.domain.port.IProductRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductService {
    private final IProductRepository iProductRepository;

    public Product save(Product product){
        return iProductRepository.save(product);
    }

    public Iterable<Product> findAll(){
        return iProductRepository.findAll();
    }

    public Product findById(Integer id){
        return iProductRepository.findById(id);
    }

    public void deleteById(Integer id){
        iProductRepository.deleteById(id);
    }
}
