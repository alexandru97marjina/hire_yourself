package com.marjina.hire_yourself.services.security;

import com.marjina.hire_yourself.common.helper.exception.AppException;
import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.services.security.dto.SecurityReqDTO;

public interface SecurityService {

    void register(SecurityReqDTO securityReqDTO) throws AppException;

    Boolean login(SecurityReqDTO securityReqDTO) throws NotFoundException;

}
