package com.linktic.ecommerce.backend.infrastructure.adapter;

import com.linktic.ecommerce.backend.domain.model.Product;
import com.linktic.ecommerce.backend.domain.port.IProductRepository;
import com.linktic.ecommerce.backend.infrastructure.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductCrudRepositoryImpl implements IProductRepository {
    private final IProductCrudRepository iProductCrudRepository;
    private final ProductMapper productMapper;
    @Override
    public Product save(Product product) {
        return productMapper.toProduct(iProductCrudRepository.save(productMapper.toProductEntity(product)));
    }

    @Override
    public Iterable<Product> findAll() {
        return productMapper.toProductList(iProductCrudRepository.findAll());
    }

    @Override
    public Product findById(Integer id) {
        return productMapper.toProduct(iProductCrudRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Producto con id: "+id+ "no existe")
        ));
    }

    @Override
    public void deleteById(Integer id) {
        iProductCrudRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Producto con id: "+id+ "no existe")
        );
        iProductCrudRepository.deleteById(id);
    }
}
