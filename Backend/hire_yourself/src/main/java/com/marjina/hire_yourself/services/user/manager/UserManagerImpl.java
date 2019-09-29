package com.marjina.hire_yourself.services.user.manager;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.persistence.models.ActivityField;
import com.marjina.hire_yourself.common.persistence.models.Education;
import com.marjina.hire_yourself.common.persistence.models.User;
import com.marjina.hire_yourself.common.persistence.repository.UserRepository;
import com.marjina.hire_yourself.services.activity.manager.ActivityManager;
import com.marjina.hire_yourself.services.education.manager.EducationManager;
import com.marjina.hire_yourself.services.experience.manager.ExperienceManager;
import com.marjina.hire_yourself.services.user.dto.UserReqDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.marjina.hire_yourself.common.util.consts.GlobalConst.USER_NOT_FOUND;

@Component
public class UserManagerImpl implements UserManager {

    @Autowired
    private UserRepository userDAO;

    @Autowired
    private EducationManager educationManager;

    @Autowired
    private ExperienceManager experienceManager;

    @Autowired
    private ActivityManager activityManager;

    @Override
    public void createUser(UserReqDTO reqDTO) throws NotFoundException {
        User user = new User();
        user.setEmail(reqDTO.getEmail());
        user.setPassword(reqDTO.getPassword());
        user.setFirstName(reqDTO.getFirstName());
        user.setLastName(reqDTO.getLastName());
        user.setAddress(reqDTO.getAddress());
        userDAO.save(user);
        user = getUserByEmail(reqDTO.getEmail());
        Education education = educationManager.getEducationById(reqDTO.getEducationId());
        education.getUsers().add(user);
        user.setEducation(education);
        user.setGraduationYear(reqDTO.getGraduationYear());
        ActivityField activityField = activityManager.getActivityById(reqDTO.getActivityId());
        activityField.getUsers().add(user);
        user.setActivityField(activityField);
        user.setExperiences(experienceManager.saveExperiencesByUser(reqDTO.getExperience(), user.getId()));

        userDAO.save(user);
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


}
