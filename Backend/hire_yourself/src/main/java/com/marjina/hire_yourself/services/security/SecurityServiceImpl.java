package com.marjina.hire_yourself.services.security;

import com.marjina.hire_yourself.common.helper.exception.AppException;
import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.persistence.models.User;
import com.marjina.hire_yourself.services.security.dto.SecurityReqDTO;
import com.marjina.hire_yourself.services.security.manager.SecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private SecurityManager manager;

    /**
     * Register a user in database
     *
     * @param securityReqDTO Security req dto
     * @throws AppException in case of duplicate email registration
     */
    @Override
    public void register(SecurityReqDTO securityReqDTO) throws AppException {
        manager.registerUser(securityReqDTO);
    }

    @Override
    public Boolean login(SecurityReqDTO securityReqDTO) throws NotFoundException {
        User user = manager.getUserByEmail(securityReqDTO.getEmail());

        return user.getPassword().equals(securityReqDTO.getPassword());
    }

}
