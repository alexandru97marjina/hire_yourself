package com.marjina.hire_yourself.services.education;

import com.marjina.hire_yourself.services.education.dto.EducationReqDTO;
import com.marjina.hire_yourself.services.education.dto.EducationResDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationServiceImpl implements EducationService {

    @Override
    public void createEducation(EducationReqDTO reqDTO) {

    }

    @Override
    public void createEducation(Integer educationId, EducationReqDTO reqDTO) {

    }

    @Override
    public void deleteEducation(Integer educationId) {

    }

    @Override
    public EducationResDTO getEducationById(Integer educationId) {
        return null;
    }

    @Override
    public List<EducationResDTO> getListOfEducations() {
        return null;
    }

}
