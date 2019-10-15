package com.marjina.hire_yourself.services.user.dto;

import com.marjina.hire_yourself.services.education.dto.EducationResDTO;
import com.marjina.hire_yourself.services.experience.dto.ExperienceResDTO;
import com.marjina.hire_yourself.services.post.dto.PostResDTO;
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
    private String firstName;
    private String lastName;
    private Integer age;
    private String address;
    private String phone;
    private String cvPath;
    private Boolean active;
    private Integer graduationYear;
    private String activityField;
    private EducationResDTO education;
    private List<ExperienceResDTO> experience;
    private Integer postLimit;
    private List<PostResDTO> posts;
    private List<PostResDTO> favoritePosts;

}
