package com.marjina.hire_yourself.services.activity.manager;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.persistence.models.ActivityField;
import com.marjina.hire_yourself.services.activity.dto.ActivityNameResDTO;
import com.marjina.hire_yourself.services.activity.dto.ActivityReqDTO;
import com.marjina.hire_yourself.services.activity.dto.ActivityResDTO;

import java.util.List;

public interface ActivityManager {

    ActivityField getActivityById(Integer id) throws NotFoundException;

    void createActivity(ActivityReqDTO reqDTO);

    void updateActivity(Integer activityId, ActivityReqDTO reqDTO) throws NotFoundException;

    ActivityResDTO getActivity(Integer activityId) throws NotFoundException;

    void deleteActivity(Integer activityId);

    List<ActivityResDTO> getActivityList();

    List<ActivityNameResDTO> getActivityNameList();

}
