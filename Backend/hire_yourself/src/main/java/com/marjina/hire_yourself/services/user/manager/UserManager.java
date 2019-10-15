package com.marjina.hire_yourself.services.user.manager;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.persistence.models.User;
import com.marjina.hire_yourself.services.user.dto.UserReqDTO;
import com.marjina.hire_yourself.services.user.dto.UserResDTO;

import java.text.ParseException;
import java.util.List;

public interface UserManager {

    void createUser(UserReqDTO reqDTO) throws NotFoundException, ParseException;

    User getUserById(Integer userId) throws NotFoundException;

    User getUserByEmail(String email) throws NotFoundException;

    void updateUser(Integer userId, UserReqDTO reqDTO) throws NotFoundException, ParseException;

    UserResDTO getUserResDTO(Integer userId) throws NotFoundException;

    List<UserResDTO> getListOfUsers();

    void deleteUserById(Integer userId);
}
