package com.marjina.hire_yourself.services.post.manager;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.persistence.models.Post;
import com.marjina.hire_yourself.common.persistence.models.User;
import com.marjina.hire_yourself.services.post.dto.PostReqDTO;
import com.marjina.hire_yourself.services.post.dto.PostResDTO;

import java.text.ParseException;
import java.util.List;

public interface PostManager {

    List<PostResDTO> getListOfPostResDTObyUser(Integer userId);

    void createPost(PostReqDTO reqDTO) throws NotFoundException, ParseException;

    void updatePost(Integer postId, PostReqDTO reqDTO) throws NotFoundException, ParseException;

    Post getPostById(Integer postId) throws NotFoundException;

    void deletePost(Integer postId);

    List<PostResDTO> getListOfPosts();

    List<PostResDTO> getListOfFavoritePosts(User user);
}
