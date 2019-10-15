package com.marjina.hire_yourself.services.user;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.services.user.dto.UserReqDTO;
import com.marjina.hire_yourself.services.user.dto.UserResDTO;

import java.text.ParseException;
import java.util.List;

public interface UserService {

    void createUser(UserReqDTO reqDTO) throws NotFoundException, ParseException;

    void updateUser(Integer userId, UserReqDTO reqDTO) throws NotFoundException, ParseException;

    UserResDTO getUser(Integer userId) throws NotFoundException;

    List<UserResDTO> getAllUsers();

    void deleteUser(Integer userId);
}
