package com.marjina.hire_yourself.services.post;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.services.post.dto.PostReqDTO;
import com.marjina.hire_yourself.services.post.dto.PostResDTO;
import com.marjina.hire_yourself.services.post.helper.PostMapHelper;
import com.marjina.hire_yourself.services.post.manager.PostManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostManager manager;

    @Autowired
    private PostMapHelper helper;

    @Override
    public void createPost(PostReqDTO reqDTO) throws NotFoundException, ParseException {
        manager.createPost(reqDTO);
    }

    @Override
    public void updatePost(Integer postId, PostReqDTO reqDTO) throws NotFoundException, ParseException {
        manager.updatePost(postId, reqDTO);
    }

    @Override
    public PostResDTO getPostById(Integer postId) throws NotFoundException {
        return new PostResDTO(manager.getPostById(postId));
    }

    @Override
    public void deletePostById(Integer postId) {
        manager.deletePost(postId);
    }

    @Override
    public List<PostResDTO> getAllPosts() {
        return manager.getListOfPosts();
    }

}
