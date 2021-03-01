package com.example.demo.controllers;

import com.example.demo.models.Product;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller("/")
public class HelloController {
    private final ProductService productService;

    @Autowired
    public HelloController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/hello")
    public String helloPage(Model model, @RequestParam(value = "name", required = false) String name) {
        if (name == null) {
            name = "Anonymous";
        }
        model.addAttribute("name", name);
        return "Hello-page";
    }

    @GetMapping("/products")
    public String getAllProducts(Model model,
                                 @RequestParam(value = "word", required = false) String word,
                                 @RequestParam(value = "minPrice", required = false) Double minPrice,
                                 @RequestParam(value = "maxPrice", required = false) Double maxPrice

    ) {
        model.addAttribute("products", productService.findAllProductByFilter(word, minPrice, maxPrice));
        model.addAttribute("product", new Product());
        model.addAttribute("word", word);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        return "Products";
    }

    @GetMapping("/products/product-form/{id}")
    public String getProduct(@PathVariable Long id, Model model) {
        Product product = productService.getOneProductById(id);
        model.addAttribute("product", product);
        return "Product";
    }

    @PostMapping("/products/product/save")
    public String saveProduct(Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @PostMapping("/products/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }

}
