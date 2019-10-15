package com.marjina.hire_yourself.services.activity;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.services.activity.dto.ActivityReqDTO;
import com.marjina.hire_yourself.services.activity.dto.ActivityResDTO;

import java.util.List;

public interface ActivityService {

    void createActivity(ActivityReqDTO reqDTO);

    void updateActivity(Integer activityId, ActivityReqDTO reqDTO) throws NotFoundException;

    ActivityResDTO getActivityById(Integer activityId) throws NotFoundException;

    void deleteActivity(Integer activityId);

    List<ActivityResDTO> getActivityList();
}
