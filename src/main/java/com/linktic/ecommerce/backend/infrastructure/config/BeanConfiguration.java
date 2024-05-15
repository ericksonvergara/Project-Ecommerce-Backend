package com.linktic.ecommerce.backend.infrastructure.config;

import com.linktic.ecommerce.backend.application.CategoryService;
import com.linktic.ecommerce.backend.application.UserService;
import com.linktic.ecommerce.backend.domain.port.ICategoryRepository;
import com.linktic.ecommerce.backend.domain.port.IUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public UserService userService(IUserRepository iUserRepository){
        return new UserService(iUserRepository);
    }
    @Bean
    public CategoryService categoryService(ICategoryRepository iCategoryRepository){
        return new CategoryService(iCategoryRepository);
    }
}
