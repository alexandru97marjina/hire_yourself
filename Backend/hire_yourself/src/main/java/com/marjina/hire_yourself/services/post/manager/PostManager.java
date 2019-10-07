package com.marjina.hire_yourself.services.post.manager;

import com.marjina.hire_yourself.services.post.dto.PostResDTO;

import java.util.List;

public interface PostManager {

    List<PostResDTO> getListOfPostResDTObyUser(Integer userId);

}
