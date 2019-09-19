package com.marjina.hire_yourself.services.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReqDTO {

    String email;
    String password;
    String firstName;
    String lastName;
    String address;
    String phone;

}
