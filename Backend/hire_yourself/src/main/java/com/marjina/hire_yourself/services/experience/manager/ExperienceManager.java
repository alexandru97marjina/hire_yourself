package com.marjina.hire_yourself.services.experience.manager;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.persistence.models.Experience;
import com.marjina.hire_yourself.services.experience.dto.ExperienceReqDTO;
import com.marjina.hire_yourself.services.experience.dto.ExperienceResDTO;

import java.util.List;

public interface ExperienceManager {

    List<Experience> saveExperiencesByUser(List<ExperienceReqDTO> experienceReqDTOS, Integer userId) throws NotFoundException;

    List<ExperienceResDTO> getExperienceResDTOs(Integer userId);

}
