package com.marjina.hire_yourself.services.user.helper;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.persistence.models.ActivityField;
import com.marjina.hire_yourself.common.persistence.models.Education;
import com.marjina.hire_yourself.common.persistence.models.User;
import com.marjina.hire_yourself.common.persistence.repository.ActivityRepository;
import com.marjina.hire_yourself.common.persistence.repository.EducationRepository;
import com.marjina.hire_yourself.common.persistence.repository.UserRepository;
import com.marjina.hire_yourself.services.activity.manager.ActivityManager;
import com.marjina.hire_yourself.services.education.dto.EducationResDTO;
import com.marjina.hire_yourself.services.education.manager.EducationManager;
import com.marjina.hire_yourself.services.experience.manager.ExperienceManager;
import com.marjina.hire_yourself.services.favorites.manager.FavoritesManager;
import com.marjina.hire_yourself.services.post.manager.PostManager;
import com.marjina.hire_yourself.services.user.dto.UserReqDTO;
import com.marjina.hire_yourself.services.user.dto.UserResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.text.ParseException;

@Component
public class UserMapHelperImpl implements UserMapHelper {

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
    private PostManager postManager;

    @Autowired
    private FavoritesManager favoritesManager;

    @Override
    public void mapUserReqDTOToUser(User user, UserReqDTO reqDTO) throws NotFoundException, ParseException {
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

    @Override
    @Transactional
    public UserResDTO mapUserToUserResDTO(User user) {
        UserResDTO userResDTO = new UserResDTO();
        userResDTO.setId(user.getId());
        userResDTO.setEmail(user.getEmail());
        userResDTO.setFirstName(user.getFirstName());
        userResDTO.setLastName(user.getLastName());
        userResDTO.setAge(user.getAge());
        userResDTO.setAddress(user.getAddress());
        userResDTO.setPhone(user.getPhoneNumber());
        userResDTO.setCvPath(user.getCvPath());
        userResDTO.setActivityField(user.getActivityField() != null? user.getActivityField().getActivityName() : null);
        userResDTO.setActive(user.getActive());
        userResDTO.setPostLimit(user.getPostLimit());
        userResDTO.setGraduationYear(user.getGraduationYear());
        userResDTO.setEducation(user.getEducation() != null ? new EducationResDTO(user.getEducation()) : null);
        userResDTO.setExperience(experienceManager.getExperienceResDTOs(user.getId()));
        userResDTO.setPosts(postManager.getListOfPostResDTObyUser(user.getId()));
        userResDTO.setFavoritePosts(postManager.getListOfFavoritePosts(user.getId()));

        return userResDTO;
    }

}
