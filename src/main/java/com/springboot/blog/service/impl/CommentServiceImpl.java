package com.springboot.blog.service.impl;

import com.springboot.blog.dto.CommentDTO;
import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    private PostRepository postRepository;
    private CommentRepository commentRepository;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository=postRepository;
        this.commentRepository=commentRepository;
    }

    @Override
    public CommentDTO createComment(CommentDTO commentDTO, long postId) {
        Comment comment=new Comment();
        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setMessage(commentDTO.getMessage());
        Post post=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Id", postId));
        comment.setPost(post);
        Comment responseComment= commentRepository.save(comment);
        return convertToCommentDTO(responseComment);
    }

    @Override
    public void deleteComment(long commentId) {
        Comment comment=commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "Id", commentId));
        commentRepository.delete(comment);
    }

    @Override
    public CommentDTO getCommentById(long commentId) {
        Comment comment=commentRepository.getReferenceById(commentId);
        if(comment!=null){
            return convertToCommentDTO(comment);
        }
        return null;
    }

    @Override
    public CommentDTO updateComment(CommentDTO commentDTO, long commentId) {
        Comment comment=commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "Id", commentId));
        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setMessage(commentDTO.getMessage());
        Comment responseComment= commentRepository.save(comment);
        return convertToCommentDTO(responseComment);
    }

    public CommentDTO convertToCommentDTO(Comment comment){
        CommentDTO commentDTO=new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setName(comment.getName());
        commentDTO.setEmail(comment.getEmail());
        commentDTO.setMessage(comment.getMessage());
        return commentDTO;
    }

}
