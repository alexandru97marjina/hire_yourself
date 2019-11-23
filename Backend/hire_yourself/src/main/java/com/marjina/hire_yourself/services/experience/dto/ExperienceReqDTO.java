package com.marjina.hire_yourself.services.experience.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceReqDTO {

    private String companyName;
    private Long dateStarted;
    private Long dateEnded;

}
