package com.marjina.hire_yourself.services.post.dto;

import com.marjina.hire_yourself.common.persistence.models.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResDTO implements Serializable {

    private Integer id;
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
    private Date dateCreated;
    private Date dateUpdated;
    private Date dateExpired;

    public PostResDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.imagePath = post.getImagePath();
        this.salaryMin = post.getSalaryMin();
        this.salaryMax = post.getSalaryMax();
        this.jobPosition = post.getJobPosition();
        this.minExperience = post.getMinExperience();
        this.maxExperience = post.getMaxExperience();
        this.activityId = post.getActivityField().getId();
        this.educationId = post.getEducation().getId();
        this.jobLocation = post.getJobLocation();
        this.email = post.getEmail();
        this.active = post.getActive();
        this.dateCreated = post.getDateCreated();
        this.dateUpdated = post.getDateUpdated();
        this.dateExpired = post.getDateExpired();
    }

}
