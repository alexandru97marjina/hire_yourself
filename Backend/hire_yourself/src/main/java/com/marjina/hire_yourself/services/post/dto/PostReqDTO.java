package com.marjina.hire_yourself.services.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostReqDTO implements Serializable {

    private Integer userId;
    private String title;
    private String description;
    private String imagePath;
    private Integer salaryMin;
    private Integer salaryMax;
    private String jobPosition;
    private Integer minExperience;
    private Integer maxExperience;
    private Integer activityId;
    private Integer educationId;
    private String jobLocation;
    private String email;
    private Boolean active;
    private String dateCreated;
    private String dateUpdated;
    private String dateExpired;

}
