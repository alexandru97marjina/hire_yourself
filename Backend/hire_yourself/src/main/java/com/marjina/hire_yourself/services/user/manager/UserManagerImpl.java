package com.marjina.hire_yourself.services.user.manager;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.persistence.models.User;
import com.marjina.hire_yourself.common.persistence.repository.UserRepository;
import com.marjina.hire_yourself.services.user.dto.UserReqDTO;
import com.marjina.hire_yourself.services.user.dto.UserResDTO;
import com.marjina.hire_yourself.services.user.helper.UserMapHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.marjina.hire_yourself.common.util.consts.GlobalConst.USER_NOT_FOUND;

@Component
@Transactional
public class UserManagerImpl implements UserManager {

    @Autowired
    private UserRepository userDAO;

    @Autowired
    private UserMapHelper helper;

    @Override
    public void createUser(UserReqDTO reqDTO) throws NotFoundException {
        User user = new User();
        helper.mapUserReqDTOToUser(user, reqDTO);
    }

    /**
     * Get user by email
     *
     * @param userId User id
     * @return User
     * @throws NotFoundException in case of not found user
     */
    @Override
    public User getUserById(Integer userId) throws NotFoundException {
        return userDAO.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
    }

    /**
     * Get user by email
     *
     * @param email User email
     * @return User
     * @throws NotFoundException in case of not found user
     */
    @Override
    public User getUserByEmail(String email) throws NotFoundException {
        return userDAO.findUserByEmail(email)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
    }

    @Override
    public void updateUser(Integer userId, UserReqDTO reqDTO) throws NotFoundException {
        User user = getUserById(userId);
        helper.mapUserReqDTOToUser(user, reqDTO);
    }

    @Override
    public UserResDTO getUserResDTO(Integer userId) throws NotFoundException {
        User user = getUserById(userId);

        return helper.mapUserToUserResDTO(user);
    }

    @Override
    public List<UserResDTO> getListOfUsers() {
        List<User> users = userDAO.findAll();

        if (users == null) {
            return null;
        }

        return users.stream()
                .map(user -> helper.mapUserToUserResDTO(user))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(Integer userId) {
        userDAO.deleteById(userId);
    }

}
