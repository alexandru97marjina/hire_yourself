package com.marjina.hire_yourself.services.post.helper;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.persistence.models.Post;
import com.marjina.hire_yourself.services.post.dto.PostReqDTO;

import java.text.ParseException;

public interface PostMapHelper {

    Post mapPostReqDTOToPost(Post post, PostReqDTO reqDTO) throws NotFoundException, ParseException;

}
