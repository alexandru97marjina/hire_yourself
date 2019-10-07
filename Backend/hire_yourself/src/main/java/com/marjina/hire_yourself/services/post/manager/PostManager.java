package com.marjina.hire_yourself.services.post.manager;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.persistence.models.Post;
import com.marjina.hire_yourself.services.post.dto.PostReqDTO;
import com.marjina.hire_yourself.services.post.dto.PostResDTO;

import java.util.List;

public interface PostManager {

    List<PostResDTO> getListOfPostResDTObyUser(Integer userId);

    void createPost(PostReqDTO reqDTO) throws NotFoundException;

    void updatePost(Integer postId, PostReqDTO reqDTO) throws NotFoundException;

    Post getPostById(Integer postId) throws NotFoundException;

    void deletePost(Integer postId);

    List<PostResDTO> getListOfPosts();

}
