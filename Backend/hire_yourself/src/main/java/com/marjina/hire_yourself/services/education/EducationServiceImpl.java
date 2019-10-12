package com.marjina.hire_yourself.services.education;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.services.education.dto.EducationReqDTO;
import com.marjina.hire_yourself.services.education.dto.EducationResDTO;
import com.marjina.hire_yourself.services.education.manager.EducationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationServiceImpl implements EducationService {

    @Autowired
    private EducationManager manager;

    @Override
    public void createEducation(EducationReqDTO reqDTO) {
        manager.createEducation(reqDTO);
    }

    @Override
    public void updateEducation(Integer educationId, EducationReqDTO reqDTO) throws NotFoundException {
        manager.updateEducation(educationId, reqDTO);
    }

    @Override
    public void deleteEducation(Integer educationId) {
        manager.deleteEducation(educationId);
    }

    @Override
    public EducationResDTO getEducationById(Integer educationId) throws NotFoundException {
        return new EducationResDTO(manager.getEducationById(educationId));
    }

    @Override
    public List<EducationResDTO> getListOfEducations() {
        return manager.getEducationList();
    }

}
