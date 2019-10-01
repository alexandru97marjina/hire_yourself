package com.marjina.hire_yourself.services.experience.dto;

import com.marjina.hire_yourself.common.persistence.models.Experience;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceResDTO implements Serializable {

    private Integer id;
    private String companyName;
    private Integer monthsOfExperience;
    private Date dateStarted;
    private Date dateEnded;

    public ExperienceResDTO(Experience experience) {
        this.id = experience.getId();
        this.companyName = experience.getCompanyName();
        this.monthsOfExperience = experience.getMonthsOfExperience();
        this.dateStarted = experience.getDateStarted();
        this.dateEnded = experience.getDateEnded();
    }

}
