package com.marjina.hire_yourself.services.activity.dto;

import com.marjina.hire_yourself.services.post.dto.PostResDTO;
import com.marjina.hire_yourself.services.user.dto.UserResDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityResDTO implements Serializable {

    private Integer id;
    private String activityName;
    private List<UserResDTO> users;
    private List<PostResDTO> posts;

}
