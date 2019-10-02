package com.marjina.hire_yourself.services.experience.manager;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.persistence.models.Experience;
import com.marjina.hire_yourself.common.persistence.repository.ExperienceRepository;
import com.marjina.hire_yourself.common.util.DateUtil;
import com.marjina.hire_yourself.services.experience.dto.ExperienceReqDTO;
import com.marjina.hire_yourself.services.user.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExperienceManagerImpl implements ExperienceManager {

    @Autowired
    private ExperienceRepository experienceDAO;

    @Autowired
    private UserManager userManager;

    @Override
    public List<Experience> saveExperiencesByUser(List<ExperienceReqDTO> experienceReqDTOS, Integer userId) throws NotFoundException {
        List<Experience> experiences = new ArrayList<>();

        for (ExperienceReqDTO reqDTO : experienceReqDTOS) {
            Experience experience = new Experience();
            experience.setCompanyName(reqDTO.getCompanyName());
            experience.setDateStarted(reqDTO.getDateStarted());
            experience.setDateEnded(reqDTO.getDateEnded());
            experience.setMonthsOfExperience(DateUtil.getMonthDifference(reqDTO.getDateStarted(), reqDTO.getDateStarted()));
            experience.setUser(userManager.getUserById(userId));

            experiences.add(experience);
        }

        return experienceDAO.saveAll(experiences);
    }
}
