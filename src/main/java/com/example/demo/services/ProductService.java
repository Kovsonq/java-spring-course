package com.example.demo.services;

import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void save(Product product){
        productRepository.saveProduct(product);
    }

    public Product getOneProductById(long id){
        return productRepository.getOneProductById(id);
    }

    public List<Product> findAllProduct(){
        return productRepository.getAllProducts();
    }

    public List<Product> findAllProductByFilter(String word, Double minPrice, Double maxPrice){
        List<Product> fullList  = productRepository.getAllProducts();
        if (word == null && minPrice == null && maxPrice == null ) {
            return fullList;
        }

        return fullList.stream()
                .filter(o->o.getName().toLowerCase().contains(word))
                .filter(o->o.getPrice()<((maxPrice != null) ? maxPrice : Double.MAX_VALUE))
                .filter(o->o.getPrice()>((minPrice != null) ? minPrice : Double.MIN_NORMAL))
                .collect(Collectors.toList());
    }


    public void deleteProductById(long id){
        productRepository.deleteProductById(id);
    }

}
