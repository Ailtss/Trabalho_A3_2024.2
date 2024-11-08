package com.example.crud.domain.product;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findAllByActiveTrue();

    @Query("SELECT p FROM product p WHERE p.name = :name")
    List<Product> findAllByName(@Param("name") String name);

    @Query("SELECT p FROM product p WHERE p.price_in_cents = :price_in_cents")
    List<Product> findAllByPrice(@Param("price_in_cents") int price_in_cents);
}
