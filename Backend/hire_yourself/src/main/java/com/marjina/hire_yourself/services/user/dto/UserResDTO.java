package com.marjina.hire_yourself.services.user.dto;

import com.marjina.hire_yourself.services.experience.dto.ExperienceResDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResDTO {

    private Integer id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String cvPath;
    private Integer educationId;
    private Integer graduationYear;
    private List<ExperienceResDTO> experience;
    private Integer activityId;

}
