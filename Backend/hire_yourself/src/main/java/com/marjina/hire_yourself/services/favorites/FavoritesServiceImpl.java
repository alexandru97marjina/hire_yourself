package com.marjina.hire_yourself.services.favorites;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.services.favorites.manager.FavoritesManager;
import com.marjina.hire_yourself.services.post.dto.PostResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritesServiceImpl implements FavoritesService {

    @Autowired
    private FavoritesManager manager;

    /**
     * Add favorite posts to user
     *
     * @param userId user Id
     * @param postId post Id
     * @throws NotFoundException in case of not found post or user
     */
    @Override
    public void addToFavorites(Integer userId, Integer postId) throws NotFoundException {
        manager.addToFavorites(userId, postId);
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
        manager.removeFromFavorites(userId, postId);
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
        return manager.getUserFavorites(userId);
    }

}
