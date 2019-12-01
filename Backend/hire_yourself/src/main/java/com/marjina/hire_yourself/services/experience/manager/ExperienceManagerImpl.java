package com.marjina.hire_yourself.services.experience.manager;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.persistence.models.Experience;
import com.marjina.hire_yourself.common.persistence.repository.ExperienceRepository;
import com.marjina.hire_yourself.common.util.DateUtil;
import com.marjina.hire_yourself.services.experience.dto.ExperienceReqDTO;
import com.marjina.hire_yourself.services.experience.dto.ExperienceResDTO;
import com.marjina.hire_yourself.services.user.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExperienceManagerImpl implements ExperienceManager {

    @Autowired
    private ExperienceRepository experienceDAO;

    @Autowired
    private UserManager userManager;

    @Override
    public List<Experience> saveExperiencesByUser(List<ExperienceReqDTO> experienceReqDTOS, Integer userId) throws NotFoundException, ParseException {
        experienceDAO.deleteAllByUser_Id(userId);
        List<Experience> experiences = new ArrayList<>();

        for (ExperienceReqDTO reqDTO : experienceReqDTOS) {
            Experience experience = new Experience();
            experience.setCompanyName(reqDTO.getCompanyName());
            experience.setDateStarted(new Date(reqDTO.getDateStarted()));
            experience.setDateEnded(new Date(reqDTO.getDateEnded()));
            experience.setMonthsOfExperience(DateUtil.getMonthDifference(experience.getDateStarted(), experience.getDateEnded()));
            experience.setUser(userManager.getUserById(userId));

            experiences.add(experience);
        }

        experienceDAO.saveAll(experiences);

        return experiences;
    }

    @Override
    public List<ExperienceResDTO> getExperienceResDTOs(Integer userId) {
        List<Experience> experienceList = experienceDAO.findAllByUser_Id(userId);

        return experienceList
                .stream()
                .map(ExperienceResDTO::new)
                .collect(Collectors.toList());
    }
}
