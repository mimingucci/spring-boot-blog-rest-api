package com.springboot.blog.controller;

import com.springboot.blog.dto.CommentDTO;
import com.springboot.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping("/posts/{id}")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO, @PathVariable long id){
        return new ResponseEntity<>(commentService.createComment(commentDTO, id), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> getCommnent(@PathVariable long id) {
        return new ResponseEntity<>(commentService.getCommentById(id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable long id){
        commentService.deleteComment(id);
        return new ResponseEntity<>("Comment is deleted", HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CommentDTO> updateComment(@RequestBody CommentDTO commentDTO, @PathVariable long id){
        CommentDTO responseComment=commentService.updateComment(commentDTO, id);
        return new ResponseEntity<>(responseComment, HttpStatus.OK);
    }
}
