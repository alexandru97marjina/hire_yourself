package com.marjina.hire_yourself.services.user;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.services.user.dto.UserReqDTO;

public interface UserService {

    void createUser(UserReqDTO reqDTO) throws NotFoundException;

}
