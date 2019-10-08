package com.marjina.hire_yourself.services.education;

import com.marjina.hire_yourself.services.education.dto.EducationReqDTO;
import com.marjina.hire_yourself.services.education.dto.EducationResDTO;

import java.util.List;

public interface EducationService {

    void createEducation(EducationReqDTO reqDTO);

    void createEducation(Integer educationId, EducationReqDTO reqDTO);

    void deleteEducation(Integer educationId);

    EducationResDTO getEducationById(Integer educationId);

    List<EducationResDTO> getListOfEducations();

}
