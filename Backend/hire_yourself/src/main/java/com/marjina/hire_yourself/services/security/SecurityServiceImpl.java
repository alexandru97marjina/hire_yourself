package com.marjina.hire_yourself.services.security;

import com.marjina.hire_yourself.common.helper.exception.AppException;
import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.helper.mail.EMailSender;
import com.marjina.hire_yourself.common.persistence.models.User;
import com.marjina.hire_yourself.services.security.dto.SecurityReqDTO;
import com.marjina.hire_yourself.services.security.manager.SecurityManager;
import com.marjina.hire_yourself.services.user.dto.UserResDTO;
import com.marjina.hire_yourself.services.user.helper.UserMapHelper;
import com.marjina.hire_yourself.services.user.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private UserManager userManager;

    @Autowired
    private UserMapHelper userMapHelper;

    @Autowired
    private SecurityManager manager;

    @Autowired
    private EMailSender mailSender;

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

    /**
     * Login in app
     *
     * @param securityReqDTO Security reqDTO
     * @return Boolean state referred to user password
     * @throws NotFoundException in case of not found user
     */
    @Override
    public UserResDTO login(SecurityReqDTO securityReqDTO) throws NotFoundException {
        User user = userManager.getUserByEmail(securityReqDTO.getEmail());

        return user.getPassword().equals(securityReqDTO.getPassword()) ? userMapHelper.mapUserToUserResDTO(user) : null;
    }

    /**
     * Send user password form database on user email
     *
     * @param email User email
     * @throws NotFoundException in case of not found user
     */
    @Override
    public void restorePassword(String email) throws NotFoundException {
        User user = userManager.getUserByEmail(email);
        mailSender.sendEmail(user.getEmail(),user.getPassword());
        System.out.println("mail sent to " + user.getEmail());
    }

    /**
     * Change user password
     *
     * @param securityReqDTO SecurityReqDTO
     * @throws NotFoundException in case of not found user
     */
    @Override
    public void changePassword(SecurityReqDTO securityReqDTO) throws NotFoundException {
        manager.changeUserPassword(securityReqDTO);
    }

}
