package com.springboot.blog.service;

import com.springboot.blog.dto.PostContent;
import com.springboot.blog.dto.PostDTO;
import com.springboot.blog.entity.Post;

import java.util.List;

public interface PostService {
    public PostDTO createPostDto(PostDTO postDTO);
    public List<PostDTO> listPostDtos();
    public PostDTO getPosttById(Long id);
    public PostDTO updatePostById(PostDTO postDTO, Long id);
    public void deletePostById(Long id);
    public PostContent getPosts(int pageNo, int pageSize);
}
