package com.marjina.hire_yourself.services.security.manager;

import com.marjina.hire_yourself.common.helper.exception.AppException;
import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.services.security.dto.SecurityReqDTO;

public interface SecurityManager {

    void registerUser(SecurityReqDTO securityReqDTO) throws AppException;

    void changeUserPassword(SecurityReqDTO securityReqDTO) throws NotFoundException;

}
