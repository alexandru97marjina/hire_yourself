package com.marjina.hire_yourself.services.activity.helper;

import com.marjina.hire_yourself.common.persistence.models.ActivityField;
import com.marjina.hire_yourself.services.activity.dto.ActivityResDTO;

public interface ActivityMapHelper {

    ActivityResDTO mapActivityToActivityResDTO(ActivityField activityField);

}
