package com.marjina.hire_yourself.services.user;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.services.user.dto.UserReqDTO;
import com.marjina.hire_yourself.services.user.dto.UserResDTO;
import com.marjina.hire_yourself.services.user.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void updateUser(Integer userId, UserReqDTO reqDTO) throws NotFoundException {
        userManager.updateUser(userId,reqDTO);
    }

    @Override
    public UserResDTO getUser(Integer userId) throws NotFoundException {
        return userManager.getUserResDTO(userId);
    }

    @Override
    public List<UserResDTO> getAllUsers() {
        return userManager.getListOfUsers();
    }

    @Override
    public void deleteUser(Integer userId) {
        userManager.deleteUserById(userId);
    }

}
