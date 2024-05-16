package com.linktic.ecommerce.backend.application;

import com.linktic.ecommerce.backend.domain.model.Product;
import com.linktic.ecommerce.backend.domain.port.IProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@AllArgsConstructor
public class ProductService {
    private final IProductRepository iProductRepository;

    public Product save(Product product, MultipartFile multipartFile) throws IOException {
        if(product.getId()!=0){//cuando es un producto modificado
            if(multipartFile==null){
                product.setUrlImage(product.getUrlImage());
            }else{
                String nameFile = product.getUrlImage().substring(29);
//                log.info("este es el nombre de la imagen: {}", nameFile);
//                if (!nameFile.equals("default.jpg")){
//                    uploadFile.delete(nameFile);
//                }
//                product.setUrlImage(uploadFile.upload(multipartFile));
            }
        }
//        else{
//            product.setUrlImage(uploadFile.upload(multipartFile));
//        }

        return this.iProductRepository.save(product);
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
