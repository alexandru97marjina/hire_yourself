package com.marjina.hire_yourself.services.activity.manager;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.persistence.models.ActivityField;
import com.marjina.hire_yourself.common.persistence.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.marjina.hire_yourself.common.util.consts.GlobalConst.ACTIVITY_FIELD_NOT_FOUND;

@Component
public class ActivityManagerImpl implements ActivityManager {

    @Autowired
    private ActivityRepository activityDAO;

    @Override
    public ActivityField getActivityById(Integer id) throws NotFoundException {
        return activityDAO.findById(id).orElseThrow(() -> new NotFoundException(ACTIVITY_FIELD_NOT_FOUND));
    }

}
