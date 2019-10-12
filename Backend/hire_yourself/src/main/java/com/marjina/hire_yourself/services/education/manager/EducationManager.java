package com.marjina.hire_yourself.services.education.manager;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.persistence.models.Education;
import com.marjina.hire_yourself.services.education.dto.EducationReqDTO;
import com.marjina.hire_yourself.services.education.dto.EducationResDTO;

import java.util.List;

public interface EducationManager {

    Education getEducationById(Integer id) throws NotFoundException;

    void createEducation(EducationReqDTO reqDTO);

    void updateEducation(Integer educationId, EducationReqDTO reqDTO) throws NotFoundException;

    void deleteEducation(Integer educationId);

    List<EducationResDTO> getEducationList();
}
