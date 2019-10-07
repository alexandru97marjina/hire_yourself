package com.marjina.hire_yourself.services.user.manager;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.persistence.models.ActivityField;
import com.marjina.hire_yourself.common.persistence.models.Education;
import com.marjina.hire_yourself.common.persistence.models.User;
import com.marjina.hire_yourself.common.persistence.repository.ActivityRepository;
import com.marjina.hire_yourself.common.persistence.repository.EducationRepository;
import com.marjina.hire_yourself.common.persistence.repository.UserRepository;
import com.marjina.hire_yourself.common.persistence.repository.UserRoleRepository;
import com.marjina.hire_yourself.services.activity.manager.ActivityManager;
import com.marjina.hire_yourself.services.education.manager.EducationManager;
import com.marjina.hire_yourself.services.experience.manager.ExperienceManager;
import com.marjina.hire_yourself.services.user.dto.UserReqDTO;
import com.marjina.hire_yourself.services.user.dto.UserResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

import static com.marjina.hire_yourself.common.util.consts.GlobalConst.USER_NOT_FOUND;

@Component
@Transactional
public class UserManagerImpl implements UserManager {

    @Autowired
    private UserRepository userDAO;

    @Autowired
    private EducationManager educationManager;

    @Autowired
    private ExperienceManager experienceManager;

    @Autowired
    private ActivityManager activityManager;

    @Autowired
    private EducationRepository educationDAO;

    @Autowired
    private ActivityRepository activityDAO;

    @Autowired
    private UserRoleRepository userRoleDAO;

    @Override
    public void createUser(UserReqDTO reqDTO) throws NotFoundException {
        User user = new User();
        mapUserReqDTOToUser(user,reqDTO);
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
        mapUserReqDTOToUser(user,reqDTO);
    }

    @Override
    public UserResDTO getUserResDTO(Integer userId) throws NotFoundException {
        User user = getUserById(userId);

        return null;
    }

    @Override
    public void mapUserReqDTOToUser(User user , UserReqDTO reqDTO) throws NotFoundException {
        user.setEmail(reqDTO.getEmail());
        user.setPassword(reqDTO.getPassword());
        user.setFirstName(reqDTO.getFirstName());
        user.setLastName(reqDTO.getLastName());
        user.setAge(reqDTO.getAge());
        user.setPhoneNumber(reqDTO.getPhone());
        user.setActive(reqDTO.getActive());
        user.setAddress(reqDTO.getAddress());
        user.setGraduationYear(reqDTO.getGraduationYear());
        user.setCvPath(reqDTO.getCvPath());
        user.setPostLimit(reqDTO.getPostLimit());

        Education education = educationManager.getEducationById(reqDTO.getEducationId());
        user.setEducation(education);
        education.getUsers().add(user);
        educationDAO.save(education);

        ActivityField activityField = activityManager.getActivityById(reqDTO.getActivityId());
        user.setActivityField(activityField);
        activityField.getUsers().add(user);
        activityDAO.save(activityField);
        userDAO.save(user);

        User addedUser = userDAO.save(user);

        addedUser.setExperiences(experienceManager.saveExperiencesByUser(reqDTO.getExperience(), addedUser.getId()));
        userDAO.save(addedUser);
    }

    public void mapUserToUserResDTO(User user){
        UserResDTO userResDTO = new UserResDTO();
        userResDTO.setId(user.getId());
        userResDTO.setEmail(user.getEmail());
        userResDTO.setFirstName(user.getFirstName());
        userResDTO.setLastName(user.getLastName());
        userResDTO.setAge(user.getAge());
        userResDTO.setAddress(user.getAddress());
        userResDTO.setPhone(user.getPhoneNumber());
        userResDTO.setCvPath(user.getCvPath());
        userResDTO.setActive(user.getActive());
        userResDTO.setGraduationYear(user.getGraduationYear());
//        userResDTO.setEducation();
        userResDTO.setExperience(experienceManager.getExperienceResDTOs(user.getId()));

    }

}
