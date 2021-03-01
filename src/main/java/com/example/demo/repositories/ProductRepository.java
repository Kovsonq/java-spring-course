package com.example.demo.repositories;

import com.example.demo.models.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productList;

    public List<Product> getAllProducts(){
        return productList;
    }

    public void saveProduct(Product product){
        if (product.getId() == null){
            Product product1 = productList.stream().filter(o->o.getId().equals(product.getId())).findFirst().get();
            product1.setName(product.getName());
            product1.setPrice(product.getPrice());
        } else {
            productList.add(product);
        }
    }

    public Product getOneProductById(long id){
        if (productList.stream().anyMatch(o->o.getId() == id)){
            return productList.stream().filter(o->o.getId() == id).findFirst().get();
        }
        return null;
    }

    public void deleteProductById(long id){
        if (productList.stream().anyMatch(o->o.getId() == id)){
            productList.remove(productList.stream().filter(o->o.getId() == id).findFirst().get());
        } else System.out.println("no one product with this id: " + id);

    }

    @PostConstruct
    public void init(){
        productList = new ArrayList<>();
        productList.add(new Product(1L,"Milk1",15.3));
        productList.add(new Product(2L,"Milk2",11.6));
        productList.add(new Product(3L,"Milk3",14.2));
        productList.add(new Product(4L,"Milk4",75.3));
        productList.add(new Product(5L,"Milk5",17.5));
        productList.add(new Product(6L,"Milk6",12.4));
    }
}
