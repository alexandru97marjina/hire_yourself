package com.marjina.hire_yourself.services.favorites.manager;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.persistence.models.Post;
import com.marjina.hire_yourself.common.persistence.models.User;
import com.marjina.hire_yourself.common.persistence.repository.PostRepository;
import com.marjina.hire_yourself.common.persistence.repository.UserRepository;
import com.marjina.hire_yourself.services.post.dto.PostResDTO;
import com.marjina.hire_yourself.services.post.manager.PostManager;
import com.marjina.hire_yourself.services.user.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class FavoritesManagerImpl implements FavoritesManager {

    @Autowired
    private UserRepository userDAO;

    @Autowired
    private PostRepository postDAO;

    @Autowired
    private UserManager userManager;

    @Autowired
    private PostManager postManager;

    /**
     * Add favorite posts to user
     *
     * @param userId user Id
     * @param postId post Id
     * @throws NotFoundException in case of not found post or user
     */
    @Override
    public void addToFavorites(Integer userId, Integer postId) throws NotFoundException {
        User user = userManager.getUserById(userId);
        Post post = postManager.getPostById(postId);

        if (!user.getFavoritePosts().contains(post)) {
            user.getFavoritePosts().add(post);
        }
        if (!post.getFavoriteUser().contains(user)) {
            post.getFavoriteUser().add(user);
        }

        userDAO.save(user);
        postDAO.save(post);
    }

    /**
     * Remove user favorites
     *
     * @param userId user Id
     * @param postId post Id
     * @throws NotFoundException in case of not found post or user
     */
    @Override
    public void removeFromFavorites(Integer userId, Integer postId) throws NotFoundException {
        User user = userManager.getUserById(userId);
        Post post = postManager.getPostById(postId);

        user.getFavoritePosts().remove(post);
        post.getFavoriteUser().remove(user);

        userDAO.save(user);
        postDAO.save(post);
    }

    /**
     * Get user favorites
     *
     * @param userId User Id
     * @return PostcardResDTO list
     * @throws NotFoundException in case of not fond user
     */
    @Override
    public List<PostResDTO> getUserFavorites(Integer userId) throws NotFoundException {
        User user = userManager.getUserById(userId);

        return postManager.getListOfFavoritePosts(user);
    }
}
