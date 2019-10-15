package com.marjina.hire_yourself.services.activity.manager;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.persistence.models.ActivityField;
import com.marjina.hire_yourself.common.persistence.repository.ActivityRepository;
import com.marjina.hire_yourself.services.activity.dto.ActivityReqDTO;
import com.marjina.hire_yourself.services.activity.dto.ActivityResDTO;
import com.marjina.hire_yourself.services.activity.helper.ActivityMapHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.marjina.hire_yourself.common.util.consts.GlobalConst.ACTIVITY_FIELD_NOT_FOUND;

@Component
public class ActivityManagerImpl implements ActivityManager {

    @Autowired
    private ActivityRepository activityDAO;

    @Autowired
    private ActivityMapHelper helper;

    @Override
    public ActivityField getActivityById(Integer id) throws NotFoundException {
        return activityDAO.findById(id).orElseThrow(() -> new NotFoundException(ACTIVITY_FIELD_NOT_FOUND));
    }

    @Override
    public void createActivity(ActivityReqDTO reqDTO) {
        ActivityField activityField = new ActivityField();
        activityField.setActivityName(reqDTO.getActivityName());
        activityDAO.save(activityField);
    }

    @Override
    public void updateActivity(Integer activityId, ActivityReqDTO reqDTO) throws NotFoundException {
        ActivityField activityField = getActivityById(activityId);
        activityField.setActivityName(reqDTO.getActivityName());
        activityDAO.save(activityField);
    }

    @Override
    public ActivityResDTO getActivity(Integer activityId) throws NotFoundException {
        return helper.mapActivityToActivityResDTO(getActivityById(activityId));
    }

    @Override
    public void deleteActivity(Integer activityId) {
        activityDAO.deleteById(activityId);
    }

    @Override
    public List<ActivityResDTO> getActivityList() {
        List<ActivityField> activityFields = activityDAO.findAll();

        if (activityFields == null) {
            return new ArrayList<>();
        }

        return activityFields.stream()
                .map(activityField -> helper.mapActivityToActivityResDTO(activityField))
                .collect(Collectors.toList());
    }

}
