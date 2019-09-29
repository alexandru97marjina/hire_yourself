package com.marjina.hire_yourself.services.user;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.services.user.dto.UserReqDTO;
import com.marjina.hire_yourself.services.user.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserManager userManager;

    /**
     * Creates new user
     *
     * @param reqDTO UserReqDTO
     */
    @Override
    public void createUser(UserReqDTO reqDTO) throws NotFoundException {
        userManager.createUser(reqDTO);
    }

}
