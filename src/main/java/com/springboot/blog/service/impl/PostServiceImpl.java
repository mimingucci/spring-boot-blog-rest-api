package com.springboot.blog.service.impl;

import com.springboot.blog.dto.PostContent;
import com.springboot.blog.dto.PostDTO;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDTO createPostDto(PostDTO postDTO) {
        Post returnedPost=postRepository.save(convertToPost(postDTO));
        PostDTO postDTOResponse = convertToPostDTO(returnedPost);
        return postDTOResponse;
    }

    @Override
    public List<PostDTO> listPostDtos() {
        return postRepository.findAll().stream().map(e->convertToPostDTO(e)).collect(Collectors.toList());
    }

    @Override
    public PostDTO getPosttById(Long id) {
        Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "Id", id));
        return convertToPostDTO(post);
    }

    @Override
    public PostDTO updatePostById(PostDTO postDTO, Long id) {
        Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "Id", id));
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());
        Post responsePost=postRepository.save(post);
        return convertToPostDTO(responsePost);
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public PostContent getPosts(int pageNo, int pageSize) {
        Pageable pageable= PageRequest.of(pageNo, pageSize);
        Page<Post> pagePosts=postRepository.findAll(pageable);
        List<Post> listPosts=pagePosts.getContent();
        PostContent postContent=new PostContent();
        postContent.setListPostDTO(listPosts.stream().map((e)->convertToPostDTO(e)).collect(Collectors.toList()));
        postContent.setLast(pagePosts.isLast());
        postContent.setPageNo(pagePosts.getNumber());
        postContent.setPageSize(pagePosts.getSize());
        postContent.setPageTotalElements(pagePosts.getTotalElements());
        postContent.setPageTotalPages(pagePosts.getTotalPages());
        return postContent;
    }

    public Post convertToPost(PostDTO postDTO){
        Post post=new Post();
        post.setId(postDTO.getId());
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());
        return post;
    }
    public PostDTO convertToPostDTO(Post post){
        PostDTO postDTO=new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setDescription(post.getDescription());
        postDTO.setContent(post.getContent());
        return postDTO;
    }
}
