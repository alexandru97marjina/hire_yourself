package com.marjina.hire_yourself.services.activity.helper;

import com.marjina.hire_yourself.common.persistence.models.ActivityField;
import com.marjina.hire_yourself.services.activity.dto.ActivityResDTO;
import com.marjina.hire_yourself.services.post.dto.PostResDTO;
import com.marjina.hire_yourself.services.user.helper.UserMapHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ActivityMapHelperImpl implements ActivityMapHelper {

    @Autowired
    private UserMapHelper userMapHelper;

    @Override
    public ActivityResDTO mapActivityToActivityResDTO(ActivityField activityField) {
        ActivityResDTO activityResDTO = new ActivityResDTO();
        activityResDTO.setId(activityField.getId());
        activityResDTO.setActivityName(activityField.getActivityName());
        activityResDTO.setPosts(activityField.getPosts().stream()
                .map(PostResDTO::new).collect(Collectors.toList()));
        activityResDTO.setUsers(activityField.getUsers().stream()
                .map(user -> userMapHelper.mapUserToUserResDTO(user)).collect(Collectors.toList()));

        return activityResDTO;
    }
}
