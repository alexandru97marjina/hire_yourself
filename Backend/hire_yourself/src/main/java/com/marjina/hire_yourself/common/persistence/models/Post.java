package com.marjina.hire_yourself.common.persistence.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @JsonBackReference
    private User user;

    private String title;

    private String description;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "salary_min")
    private String salaryMin;

    @Column(name = "salary_max")
    private String salaryMax;

    @Column(name = "job_position")
    private String jobPosition;

    private Integer experience;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "activity_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @JsonBackReference
    private ActivityField activityField;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "education_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @JsonBackReference
    private ActivityField education;

    @Column(name = "job_location")
    private String jobLocation;

    private String email;

    private Boolean active;

    @DateTimeFormat(pattern = "yyyy-MM-dd H:i:s")
    @Column(name = "date_created")
    private Date dateCreated;

    @DateTimeFormat(pattern = "yyyy-MM-dd H:i:s")
    @Column(name = "date_updated")
    private Date dateUpdated;

    @DateTimeFormat(pattern = "yyyy-MM-dd H:i:s")
    @Column(name = "date_expired")
    private Date dateExpired;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd H:i:s")
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd H:i:s")
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedAt;
}
