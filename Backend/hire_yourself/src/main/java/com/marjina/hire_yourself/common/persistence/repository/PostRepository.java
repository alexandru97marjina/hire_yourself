package com.marjina.hire_yourself.common.persistence.repository;

import com.marjina.hire_yourself.common.persistence.models.Post;
import com.marjina.hire_yourself.common.persistence.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Integer> {

    List<Post> findAllByUser_Id(Integer userId);

    Optional<List<Post>> findAllByActivityField_Id(Integer activityId);

    Optional<List<Post>> findAllByFavoriteUser(User user);

}
