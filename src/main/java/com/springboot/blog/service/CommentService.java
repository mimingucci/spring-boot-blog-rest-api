package com.springboot.blog.service;

import com.springboot.blog.dto.CommentDTO;

public interface CommentService {
    public CommentDTO createComment(CommentDTO commentDTO, long postId);
    public void deleteComment(long commentId);
    public CommentDTO getCommentById(long commentId);
    public CommentDTO updateComment(CommentDTO commentDTO, long commentId);
}
