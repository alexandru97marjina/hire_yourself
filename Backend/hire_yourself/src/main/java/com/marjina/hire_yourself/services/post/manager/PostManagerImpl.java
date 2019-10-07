package com.marjina.hire_yourself.services.post.manager;

import com.marjina.hire_yourself.common.persistence.models.Post;
import com.marjina.hire_yourself.common.persistence.repository.PostRepository;
import com.marjina.hire_yourself.services.post.dto.PostResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostManagerImpl implements PostManager {

    @Autowired
    private PostRepository postDAO;

    @Override
    public List<PostResDTO> getListOfPostResDTObyUser(Integer userId) {
        List<Post> posts = postDAO.findAllByUser_Id(userId);

        if (posts == null) {
            return null;
        }

        return posts.stream().map(PostResDTO::new).collect(Collectors.toList());
    }
}
