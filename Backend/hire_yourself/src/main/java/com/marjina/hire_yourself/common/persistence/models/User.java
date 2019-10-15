package com.marjina.hire_yourself.common.persistence.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String password;

    private Boolean active;

    @Column(name = "post_limit")
    private Integer postLimit;

    private Integer age;

    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "cv_path")
    private String cvPath;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private UserRole userRole;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "education_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @JsonBackReference
    private Education education;

    @Column(name = "graduation_year")
    private Integer graduationYear;

    @OneToMany(mappedBy = "user", fetch = LAZY)
    @JsonManagedReference
    private List<Experience> experiences;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "activity_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @JsonBackReference
    private ActivityField activityField;

    @OneToMany(mappedBy = "user", fetch = LAZY)
    @JsonManagedReference
    private List<Post> posts;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "user_favorites",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "post_id")}
    )
    @JsonIgnore
    private List<Post> favoritePosts;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd H:i:s")
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd H:i:s")
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedAt;

}
