package com.example.crud.domain.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentsRepository extends JpaRepository<Comments, String> {

    @Query("SELECT c FROM comments c WHERE c.product_name = :product_name")
    List<Comments> findAllByProductName(@Param("product_name") String product_name);

    @Query("SELECT c.user_name, c.comment_text FROM comments c WHERE c.product_name = :product_name")
    List<Object[]> findAllByProductNameVersion2(@Param("product_name") String product_name);
}
