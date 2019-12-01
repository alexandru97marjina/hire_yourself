package com.marjina.hire_yourself.services.post.manager;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.persistence.models.Post;
import com.marjina.hire_yourself.common.persistence.repository.PostRepository;
import com.marjina.hire_yourself.services.post.dto.PostReqDTO;
import com.marjina.hire_yourself.services.post.dto.PostResDTO;
import com.marjina.hire_yourself.services.post.helper.PostMapHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.marjina.hire_yourself.common.util.consts.GlobalConst.POST_NOT_FOUND;

@Component
public class PostManagerImpl implements PostManager {

    @Autowired
    private PostRepository postDAO;

    @Autowired
    private PostMapHelper helper;

    @Override
    public List<PostResDTO> getListOfPostResDTObyUser(Integer userId) {
        List<Post> posts = postDAO.findAllByUser_Id(userId);

        if (posts == null) {
            return null;
        }

        return posts.stream().map(PostResDTO::new).collect(Collectors.toList());
    }

    @Override
    public void createPost(PostReqDTO reqDTO) throws NotFoundException, ParseException {
        Post post = new Post();
        helper.mapPostReqDTOToPost(post, reqDTO);
    }

    @Override
    public void updatePost(Integer postId, PostReqDTO reqDTO) throws NotFoundException, ParseException {
        Post post = getPostById(postId);
        helper.mapPostReqDTOToPost(post, reqDTO);
    }

    /**
     * Get post by id
     *
     * @param postId Post id
     * @return Post
     * @throws NotFoundException in case of not found post
     */
    @Override
    public Post getPostById(Integer postId) throws NotFoundException {
        return postDAO.findById(postId)
                .orElseThrow(() -> new NotFoundException(POST_NOT_FOUND));
    }

    @Override
    public void deletePost(Integer postId) {
        postDAO.deleteById(postId);
    }

    @Override
    public List<PostResDTO> getListOfPosts() {
        List<Post> posts = postDAO.findAll();

        return posts
                .stream()
                .map(PostResDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostResDTO> getListOfFavoritePosts(Integer userId) {
        List<Post> posts = postDAO.findAllByFavoriteUser_IdOrderById(userId).orElse(new ArrayList<>());

        return posts.stream().map(PostResDTO::new).collect(Collectors.toList());
    }

}
