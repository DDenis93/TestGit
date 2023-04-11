package com.example.product.services;

import com.example.product.models.Product;
import com.example.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true) // читает базу данных
public class ProductService {
    private final ProductRepository productRepository;
    private ProductService productService;

        @Autowired
        public ProductService(ProductRepository productRepository) {
            this.productRepository = productRepository;
        }

        // метод возвращает всех пользователей
        public List<Product> getAllProduct() {
            return productRepository.findAll();
        }

        // метод возвращает пользователя по id
        public Product getProductId(int id) {
            // select * from user_site where id = {id};
            Optional<Product> optionalProduct = productRepository.findById(id);
            return optionalProduct.orElse(null);
        }

        // метод сохраняет пользователя
        @Transactional // вносит изменения в базу данных
        public void addProduct(Product product) {
            productRepository.save(product); // сохранение
        }

        // метод обновляет данные
        @Transactional // вносит изменения в базу данных
        public void editProduct(int id, Product product) {
            product.setId(id);
            productRepository.save(product); // изменение
        }

        // метод удаляет данные
        @Transactional
        public void deleteProduct(int id) {
            productRepository.deleteById(id);
        }
}