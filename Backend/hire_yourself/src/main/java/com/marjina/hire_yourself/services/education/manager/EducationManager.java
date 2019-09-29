package com.marjina.hire_yourself.services.education.manager;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.persistence.models.Education;

public interface EducationManager {

    Education getEducationById(Integer id) throws NotFoundException;

}
