package com.marjina.hire_yourself.services.user.manager;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.persistence.models.User;
import com.marjina.hire_yourself.services.user.dto.UserReqDTO;
import com.marjina.hire_yourself.services.user.dto.UserResDTO;

public interface UserManager {

    void createUser(UserReqDTO reqDTO) throws NotFoundException;

    User getUserById(Integer userId) throws NotFoundException;

    User getUserByEmail(String email) throws NotFoundException;

    void updateUser(Integer userId, UserReqDTO reqDTO) throws NotFoundException;

    UserResDTO getUserResDTO(Integer userId) throws NotFoundException;

    void mapUserReqDTOToUser(User user , UserReqDTO reqDTO) throws NotFoundException;
}
