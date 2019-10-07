package com.marjina.hire_yourself.services.user.helper;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.persistence.models.User;
import com.marjina.hire_yourself.services.user.dto.UserReqDTO;
import com.marjina.hire_yourself.services.user.dto.UserResDTO;

public interface UserMapHelper {
    void mapUserReqDTOToUser(User user, UserReqDTO reqDTO) throws NotFoundException;

    UserResDTO mapUserToUserResDTO(User user);
}
