package com.example.crud.controllers;


import com.example.crud.domain.comments.Comments;
import com.example.crud.domain.comments.CommentsRepository;
import com.example.crud.domain.comments.RequestComments;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("comments")
public class CommentsController {

    @Autowired
    private CommentsRepository repository;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity getAllComments() {

        var allComments = repository.findAll();
        return ResponseEntity.ok(allComments);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{product_name}")
    public ResponseEntity getAllCommentsByProductName(@PathVariable String product_name) {

        var allComments = repository.findAllByProductNameVersion2(product_name);

        List<Map<String, String>> response = allComments.stream()
                .map(obj -> Map.of("user_name", (String) obj[0], "comment_text", (String) obj[1]))
                .toList();

        return ResponseEntity.ok(response);

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/CreateComment")
    public ResponseEntity registerComment(@RequestBody @Valid RequestComments data) {

        Comments newComment = new Comments(data);
        repository.save(newComment);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteComment(@PathVariable String id) {

        Optional<Comments> optionalComments = repository.findById(id);
        if (optionalComments.isPresent()) {

            Comments c = optionalComments.get();
            repository.delete(c);
        }

        return ResponseEntity.noContent().build();
    }
}
