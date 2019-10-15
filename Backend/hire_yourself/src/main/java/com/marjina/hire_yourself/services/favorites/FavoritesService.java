package com.marjina.hire_yourself.services.favorites;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.services.post.dto.PostResDTO;

import java.util.List;

public interface FavoritesService {

    void addToFavorites(Integer userId, Integer postId) throws NotFoundException;

    void removeFromFavorites(Integer userId, Integer postId) throws NotFoundException;

    List<PostResDTO> getUserFavorites(Integer userId) throws NotFoundException;
}
