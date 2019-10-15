package com.marjina.hire_yourself.services.user.dto;

import com.marjina.hire_yourself.services.experience.dto.ExperienceReqDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReqDTO implements Serializable {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private Integer age;
    private String phone;
    private String cvPath;
    private Integer postLimit;
    private Boolean active;
    private Integer educationId;
    private Integer graduationYear;
    private List<ExperienceReqDTO> experience;
    private Integer activityId;

}
