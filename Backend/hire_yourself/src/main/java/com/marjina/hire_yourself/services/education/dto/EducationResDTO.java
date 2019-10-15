package com.marjina.hire_yourself.services.education.dto;

import com.marjina.hire_yourself.common.persistence.models.Education;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationResDTO {

    private Integer id;
    private String specialityName;
    private String studyGrade;

    public EducationResDTO(Education education) {
        this.id = education.getId();
        this.specialityName = education.getSpecialityName();
        this.studyGrade = education.getStudyGrade();
    }

}
