package com.marjina.hire_yourself.services.activity.dto;


import com.marjina.hire_yourself.common.persistence.models.ActivityField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityNameResDTO {

    private Integer id;
    private String activityName;

    public ActivityNameResDTO(ActivityField activityField) {
        this.id = activityField.getId();
        this.activityName = activityField.getActivityName();
    }
}
