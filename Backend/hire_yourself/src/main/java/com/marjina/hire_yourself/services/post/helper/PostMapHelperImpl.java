package com.marjina.hire_yourself.services.post.helper;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.persistence.models.ActivityField;
import com.marjina.hire_yourself.common.persistence.models.Education;
import com.marjina.hire_yourself.common.persistence.models.Post;
import com.marjina.hire_yourself.common.persistence.models.User;
import com.marjina.hire_yourself.common.persistence.repository.ActivityRepository;
import com.marjina.hire_yourself.common.persistence.repository.EducationRepository;
import com.marjina.hire_yourself.common.persistence.repository.PostRepository;
import com.marjina.hire_yourself.common.persistence.repository.UserRepository;
import com.marjina.hire_yourself.services.activity.manager.ActivityManager;
import com.marjina.hire_yourself.services.education.manager.EducationManager;
import com.marjina.hire_yourself.services.post.dto.PostReqDTO;
import com.marjina.hire_yourself.services.user.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.Date;

@Component
public class PostMapHelperImpl implements PostMapHelper {

    @Autowired
    private PostRepository postDAO;

    @Autowired
    private UserRepository userDAO;

    @Autowired
    private ActivityRepository activityDAO;

    @Autowired
    private EducationRepository educationDAO;

    @Autowired
    private UserManager userManager;

    @Autowired
    private ActivityManager activityManager;

    @Autowired
    private EducationManager educationManager;

    @Override
    @Transactional
    public Post mapPostReqDTOToPost(Post post, PostReqDTO reqDTO) throws NotFoundException, ParseException {
        post.setTitle(reqDTO.getTitle());
        post.setDescription(reqDTO.getDescription());
        post.setImagePath(reqDTO.getImagePath());
        post.setSalaryMin(reqDTO.getSalaryMin());
        post.setSalaryMax(reqDTO.getSalaryMax());
        post.setJobPosition(reqDTO.getJobPosition());
        post.setMinExperience(reqDTO.getMinExperience());
        post.setMaxExperience(reqDTO.getMaxExperience());
        post.setJobLocation(reqDTO.getJobLocation());
        post.setEmail(reqDTO.getEmail());
        post.setActive(reqDTO.getActive());
        post.setDateCreated(new Date(reqDTO.getDateCreated()));
        post.setDateUpdated(new Date(reqDTO.getDateUpdated()));
        post.setDateExpired(new Date(reqDTO.getDateExpired()));

        User user = userManager.getUserById(reqDTO.getUserId());
        post.setUser(user);
        user.getPosts().add(post);
        userDAO.save(user);

        ActivityField activityField = activityManager.getActivityById(reqDTO.getActivityId());
        post.setActivityField(activityField);
        activityField.getPosts().add(post);
        activityDAO.save(activityField);

        Education education = educationManager.getEducationById(reqDTO.getEducationId());
        post.setEducation(education);
        education.getPosts().add(post);
        educationDAO.save(education);

        return postDAO.save(post);
    }

}
