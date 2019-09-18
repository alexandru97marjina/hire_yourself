package com.marjina.hire_yourself.common.persistence.repository;

import com.marjina.hire_yourself.common.persistence.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
