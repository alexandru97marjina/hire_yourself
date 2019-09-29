package com.marjina.hire_yourself.services.experience.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceReqDTO {

    private String companyName;
    private Integer experienceId;
    private Date dateStarted;
    private Date dateEnded;

}
