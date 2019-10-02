package com.marjina.hire_yourself.services.activity.manager;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.persistence.models.ActivityField;

public interface ActivityManager {

    ActivityField getActivityById(Integer id) throws NotFoundException;

}
