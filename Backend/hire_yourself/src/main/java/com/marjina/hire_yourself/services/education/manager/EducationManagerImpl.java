package com.marjina.hire_yourself.services.education.manager;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.persistence.models.Education;
import com.marjina.hire_yourself.common.persistence.repository.EducationRepository;
import com.marjina.hire_yourself.services.education.dto.EducationResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.marjina.hire_yourself.common.util.consts.GlobalConst.EDUCATION_NOT_FOUND;

@Component
public class EducationManagerImpl implements EducationManager {

    @Autowired
    private EducationRepository educationDAO;

    /**
     * Get Education by educationId
     *
     * @param id Integer educationId
     * @return Education
     * @throws NotFoundException In case of not found object
     */
    @Override
    public Education getEducationById(Integer id) throws NotFoundException {
        return educationDAO.findById(id).orElseThrow(() -> new NotFoundException(EDUCATION_NOT_FOUND));
    }


//    public EducationResDTO getEducationResDTOByUserId(){
//
//    }

}
