package com.marjina.hire_yourself.services.user;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.services.user.dto.UserReqDTO;
import com.marjina.hire_yourself.services.user.dto.UserResDTO;

public interface UserService {

    void createUser(UserReqDTO reqDTO) throws NotFoundException;

    void updateUser(Integer userId, UserReqDTO reqDTO) throws NotFoundException;

    UserResDTO getUser(Integer userId) throws NotFoundException;
}
