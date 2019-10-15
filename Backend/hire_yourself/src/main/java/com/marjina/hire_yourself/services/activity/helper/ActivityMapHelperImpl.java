package com.marjina.hire_yourself.services.activity.helper;

import com.marjina.hire_yourself.common.persistence.models.ActivityField;
import com.marjina.hire_yourself.common.persistence.models.Post;
import com.marjina.hire_yourself.common.persistence.models.User;
import com.marjina.hire_yourself.common.persistence.repository.PostRepository;
import com.marjina.hire_yourself.common.persistence.repository.UserRepository;
import com.marjina.hire_yourself.services.activity.dto.ActivityResDTO;
import com.marjina.hire_yourself.services.post.dto.PostResDTO;
import com.marjina.hire_yourself.services.user.helper.UserMapHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ActivityMapHelperImpl implements ActivityMapHelper {

    @Autowired
    private UserMapHelper userMapHelper;

    @Autowired
    private UserRepository userDAO;

    @Autowired
    private PostRepository postDAO;

    @Override
    public ActivityResDTO mapActivityToActivityResDTO(ActivityField activityField) {
        ActivityResDTO activityResDTO = new ActivityResDTO();
        activityResDTO.setId(activityField.getId());
        activityResDTO.setActivityName(activityField.getActivityName());
        List<Post> postList = postDAO.findAllByActivityField_Id(activityField.getId()).orElse(new ArrayList<>());
        List<User> userList = userDAO.findAllByActivityField_Id(activityField.getId()).orElse(new ArrayList<>());
        activityResDTO.setUsers(userList.stream()
                .map(user -> userMapHelper.mapUserToUserResDTO(user)).collect(Collectors.toList()));
        activityResDTO.setPosts(postList.stream()
                .map(PostResDTO::new).collect(Collectors.toList()));

        return activityResDTO;
    }
}
