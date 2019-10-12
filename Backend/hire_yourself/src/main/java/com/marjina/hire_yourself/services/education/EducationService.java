package com.marjina.hire_yourself.services.education;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.services.education.dto.EducationReqDTO;
import com.marjina.hire_yourself.services.education.dto.EducationResDTO;

import java.util.List;

public interface EducationService {

    void createEducation(EducationReqDTO reqDTO);

    void updateEducation(Integer educationId, EducationReqDTO reqDTO) throws NotFoundException;

    void deleteEducation(Integer educationId);

    EducationResDTO getEducationById(Integer educationId) throws NotFoundException;

    List<EducationResDTO> getListOfEducations();

}
