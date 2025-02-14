package com.example.Shop.service;

import com.example.Shop.model.Product;
import com.example.Shop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductRepository {
    private final List<Product> products = new ArrayList<>();

    public ProductService() {
        this.products.add(new Product(1, "Iphone 16S", "Apple", 75000.00, 4));
        this.products.add(new Product(2, "Iphone 15S", "Apple", 55000.00, 5));
        this.products.add(new Product(3, "Iphone 14S", "Apple", 25000.00, 10));
    }

    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public Optional<Product> findById(long id) {
        return products.stream().filter(product -> product.getId() == id).findFirst();
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public void delete(Product product) {
        products.remove(product);
    }

    @Override
    public void update(long id, Product product) {
        products.replaceAll(p -> {
            if (p.getId() == id) {
                p.setName(product.getName());
                p.setDescription(product.getDescription());
                p.setPrice(product.getPrice());
                p.setStock(product.getStock());
            }
            return p;
        });
    }
}
