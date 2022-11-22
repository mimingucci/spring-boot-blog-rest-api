package com.springboot.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostContent {
    private List<PostDTO> listPostDTO;
    private boolean isLast;
    private int pageNo;
    private int pageSize;
    private long pageTotalElements;
    private int pageTotalPages;
}
