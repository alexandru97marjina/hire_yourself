package com.marjina.hire_yourself.services.education.manager;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.persistence.models.Education;
import com.marjina.hire_yourself.common.persistence.repository.EducationRepository;
import com.marjina.hire_yourself.services.education.dto.EducationReqDTO;
import com.marjina.hire_yourself.services.education.dto.EducationResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * Create education entity
     *
     * @param reqDTO Education reqDTO
     */
    @Override
    public void createEducation(EducationReqDTO reqDTO) {
        Education education = new Education();
        education.setSpecialityName(reqDTO.getSpecialityName());
        education.setStudyGrade(reqDTO.getStudyGrade());

        educationDAO.save(education);
    }

    /**
     * Update education object
     *
     * @param educationId Education identifier
     * @param reqDTO      Education reqDTO
     * @throws NotFoundException in case of not found education
     */
    @Override
    public void updateEducation(Integer educationId, EducationReqDTO reqDTO) throws NotFoundException {
        Education education = getEducationById(educationId);
        education.setSpecialityName(reqDTO.getSpecialityName());
        education.setStudyGrade(reqDTO.getStudyGrade());

        educationDAO.save(education);
    }

    /**
     * Delete education object by id
     *
     * @param educationId Education identifier
     */
    @Override
    public void deleteEducation(Integer educationId) {
        educationDAO.deleteById(educationId);
    }

    /**
     * Get list of education resDTO
     *
     * @return List of educationResDTO
     */
    @Override
    public List<EducationResDTO> getEducationList() {
        return educationDAO.findAll()
                .stream()
                .map(EducationResDTO::new)
                .collect(Collectors.toList());
    }

}
