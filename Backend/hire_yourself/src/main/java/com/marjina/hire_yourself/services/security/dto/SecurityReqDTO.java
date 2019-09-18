package com.marjina.hire_yourself.services.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityReqDTO {

    private String email;
    private String password;

}
