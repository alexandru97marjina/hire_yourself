package com.marjina.hire_yourself.services.security;

import com.marjina.hire_yourself.common.helper.exception.AppException;
import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.persistence.models.User;
import com.marjina.hire_yourself.services.security.dto.SecurityReqDTO;
import com.marjina.hire_yourself.services.user.dto.UserResDTO;

public interface SecurityService {

    void register(SecurityReqDTO securityReqDTO) throws AppException;

    UserResDTO login(SecurityReqDTO securityReqDTO) throws NotFoundException;

    void restorePassword(String email) throws NotFoundException;

    void changePassword(SecurityReqDTO securityReqDTO) throws NotFoundException;

}
