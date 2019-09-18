package com.marjina.hire_yourself.controllers;

import com.marjina.hire_yourself.common.helper.exception.AppException;
import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.response.ErrorDTO;
import com.marjina.hire_yourself.common.response.ResponseDTO;
import com.marjina.hire_yourself.services.security.SecurityService;
import com.marjina.hire_yourself.services.security.dto.SecurityReqDTO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.marjina.hire_yourself.common.util.consts.GlobalConst.*;
import static java.util.Collections.emptyList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/security")
public class SecurityController {

    @Autowired
    private SecurityService service;

    /**
     * Register a user
     *
     * @param securityReqDTO SecurityReqDto
     * @return ResponseEntity
     */
    @ApiOperation(value = "Register a user")
    @ApiImplicitParam(name = TOKEN, value = TOKEN_DESC, paramType = HEADER_FIELD, required = true, dataType = STRING_FIELD)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success response", response = ResponseDTO.class),
            @ApiResponse(code = 400, message = "Validation not passed", response = ErrorDTO.class),
            @ApiResponse(code = 403, message = "Incorrect bearer token", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "User not found", response = NotFoundException.class)
    })
    @PostMapping(value = "/users/reg", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> registration(@RequestBody SecurityReqDTO securityReqDTO) throws AppException {
        service.register(securityReqDTO);

        return ResponseEntity.ok(new ResponseDTO<>(SUCCESS, null, "Successful registration!", emptyList()));
    }

    /**
     * Login a user
     *
     * @param securityReqDTO SecurityReqDTO
     * @return ResponseEntity
     */
    @ApiOperation(value = "Register a user")
    @ApiImplicitParam(name = TOKEN, value = TOKEN_DESC, paramType = HEADER_FIELD, required = true, dataType = STRING_FIELD)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success response", response = ResponseDTO.class),
            @ApiResponse(code = 400, message = "Validation not passed", response = ErrorDTO.class),
            @ApiResponse(code = 403, message = "Incorrect bearer token", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "User not found", response = NotFoundException.class)
    })
    @PostMapping(value = "/users/log", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> login(@RequestBody SecurityReqDTO securityReqDTO) throws AppException, NotFoundException {
        Boolean logged = service.login(securityReqDTO);

        if(!logged){
            return ResponseEntity.ok(new ResponseDTO<>(ERROR_STATUS, false, "Wrong password or email", emptyList()));
        }

        return ResponseEntity.ok(new ResponseDTO<>(SUCCESS, true, "Successful login!", emptyList()));
    }

}
