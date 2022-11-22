package com.springboot.blog.controller;

import com.springboot.blog.dto.PostContent;
import com.springboot.blog.dto.PostDTO;
import com.springboot.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO){
        PostDTO postDTO1= postService.createPostDto(postDTO);
       return new ResponseEntity<>(postDTO1, HttpStatus.CREATED);
    }
    @GetMapping("/listPosts")
    public ResponseEntity<List<PostDTO>> listPosts(){
        return new ResponseEntity<>(postService.listPostDtos(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable(name="id") Long id){
        return new ResponseEntity<>(postService.getPosttById(id), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePostById(@RequestBody PostDTO postDTO, @PathVariable Long id){
        return new ResponseEntity<>(postService.updatePostById(postDTO, id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable Long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Delete post with id:"+id, HttpStatus.OK);
    }
    @GetMapping("/listPostsPageable")
    public ResponseEntity<PostContent> listPostsPageable(@RequestParam(name = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                         @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize
                                                           )
    {
        return new ResponseEntity<>(postService.getPosts(pageNo, pageSize), HttpStatus.OK);
    }
}
