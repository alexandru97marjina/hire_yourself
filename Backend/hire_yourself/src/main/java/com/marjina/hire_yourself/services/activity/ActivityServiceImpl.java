package com.marjina.hire_yourself.services.activity;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.services.activity.dto.ActivityNameResDTO;
import com.marjina.hire_yourself.services.activity.dto.ActivityReqDTO;
import com.marjina.hire_yourself.services.activity.dto.ActivityResDTO;
import com.marjina.hire_yourself.services.activity.manager.ActivityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityManager manager;

    @Override
    public void createActivity(ActivityReqDTO reqDTO) {
        manager.createActivity(reqDTO);
    }

    @Override
    public void updateActivity(Integer activityId, ActivityReqDTO reqDTO) throws NotFoundException {
        manager.updateActivity(activityId, reqDTO);
    }

    @Override
    public ActivityResDTO getActivityById(Integer activityId) throws NotFoundException {
        return manager.getActivity(activityId);
    }

    @Override
    public void deleteActivity(Integer activityId) {
        manager.deleteActivity(activityId);
    }

    @Override
    public List<ActivityResDTO> getActivityList() {
        return manager.getActivityList();
    }

    @Override
    public List<ActivityNameResDTO> getActivityNameList() {
        return manager.getActivityNameList();
    }

}
