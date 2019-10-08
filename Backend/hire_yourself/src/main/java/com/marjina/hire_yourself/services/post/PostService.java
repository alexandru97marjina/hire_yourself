package com.marjina.hire_yourself.services.post;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.services.post.dto.PostReqDTO;
import com.marjina.hire_yourself.services.post.dto.PostResDTO;

import java.text.ParseException;
import java.util.List;

public interface PostService {

    void createPost(PostReqDTO reqDTO) throws NotFoundException, ParseException;

    void updatePost(Integer postId, PostReqDTO reqDTO) throws NotFoundException, ParseException;

    PostResDTO getPostById(Integer postId) throws NotFoundException;

    void deletePostById(Integer postId);

    List<PostResDTO> getAllPosts();

}
