package com.marjina.hire_yourself.services.security.manager;

import com.marjina.hire_yourself.common.helper.exception.AppException;
import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.persistence.models.User;
import com.marjina.hire_yourself.common.persistence.repository.UserRepository;
import com.marjina.hire_yourself.common.util.ExceptionUtil;
import com.marjina.hire_yourself.services.security.dto.SecurityReqDTO;
import com.marjina.hire_yourself.services.user.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.marjina.hire_yourself.common.util.consts.GlobalConst.DUPLICATE_EMAIL_EXISTS;
import static com.marjina.hire_yourself.common.util.consts.GlobalConst.EMAIL_FIELD;

@Component
public class SecurityManagerImpl implements SecurityManager {

    @Autowired
    private UserRepository userDAO;

    @Autowired
    private UserManager userManager;

    /**
     * Check if user with this email already exists in db, if exists throw exception, else create new
     *
     * @param securityReqDTO SecurityReqDTO
     * @throws AppException Exception in case of existing email
     */
    @Override
    public void registerUser(SecurityReqDTO securityReqDTO) throws AppException {
        Optional<User> user = userDAO.findUserByEmail(securityReqDTO.getEmail());

        if(user.isPresent()){
            throw ExceptionUtil.newAppException(200,EMAIL_FIELD,DUPLICATE_EMAIL_EXISTS);
        }else {
            User newUser = new User();
             newUser.setEmail(securityReqDTO.getEmail());
             newUser.setPassword(securityReqDTO.getPassword());

             userDAO.save(newUser);
        }
    }

    /**
     * Change user password
     *
     * @param securityReqDTO SecurityReqDTO
     * @throws NotFoundException in case of not found user
     */
    @Override
    public void changeUserPassword(SecurityReqDTO securityReqDTO) throws NotFoundException {
        User user = userManager.getUserByEmail(securityReqDTO.getEmail());
        user.setPassword(securityReqDTO.getPassword());

        userDAO.save(user);
    }

}
