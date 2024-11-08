package com.example.crud.controllers;

import com.example.crud.domain.product.Product;
import com.example.crud.domain.product.ProductRepository;
import com.example.crud.domain.product.RequestProduct;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository repository;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity getAllProducts() {

        var allProducts = repository.findAllByActiveTrue();
        return ResponseEntity.ok(allProducts);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/name/{name}")
    public ResponseEntity getProductsByName(@PathVariable String name) {

        var allProductsByName = repository.findAllByName(name);
        return ResponseEntity.ok(allProductsByName);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/price/{price_in_cents}")
    public ResponseEntity getProductsByPrice(@PathVariable int price_in_cents) {

        var allProductsByPrice = repository.findAllByPrice(price_in_cents);
        return ResponseEntity.ok(allProductsByPrice);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public ResponseEntity registerProduct(@RequestBody @Valid RequestProduct data){
        Product newProduct = new Product(data);
        repository.save(newProduct);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping
    @Transactional
    public ResponseEntity updateProduct(@RequestBody @Valid RequestProduct data) {

        Optional<Product> optionalProduct = repository.findById(data.id());
        if (optionalProduct.isPresent()) {
            Product p = optionalProduct.get();
            p.setName(data.name());
            p.setPrice_in_cents(data.price_in_cents());
            return ResponseEntity.ok(p);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteProduct(@PathVariable String id){

        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()) {
            Product p = optionalProduct.get();
            p.setActive(false);
            return ResponseEntity.noContent().build();
        } else {
            throw new EntityNotFoundException();
        }
    }


}
