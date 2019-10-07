package com.marjina.hire_yourself.controllers;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.response.ErrorDTO;
import com.marjina.hire_yourself.common.response.ResponseDTO;
import com.marjina.hire_yourself.services.user.UserService;
import com.marjina.hire_yourself.services.user.dto.UserReqDTO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.marjina.hire_yourself.common.util.consts.GlobalConst.*;
import static java.util.Collections.emptyList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    /**
     * Create a user
     *
     * @param reqDTO UserReqDto
     * @return ResponseEntity
     */
    @ApiOperation(value = "Create a user")
    @ApiImplicitParam(name = TOKEN, value = TOKEN_DESC, paramType = HEADER_FIELD, required = true, dataType = STRING_FIELD)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success response", response = ResponseDTO.class),
            @ApiResponse(code = 400, message = "Validation not passed", response = ErrorDTO.class),
            @ApiResponse(code = 403, message = "Incorrect bearer token", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "User not found", response = NotFoundException.class)
    })
    @PostMapping(value = "/create", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> createUser(@RequestBody UserReqDTO reqDTO) throws NotFoundException {
        service.createUser(reqDTO);

        return ResponseEntity.ok(new ResponseDTO<>(SUCCESS, null, "Successful user create", emptyList()));
    }

    /**
     * Update a user
     *
     * @param reqDTO UserReqDto
     * @param userId User ID
     * @return ResponseEntity
     */
    @ApiOperation(value = "Update a user")
    @ApiImplicitParam(name = TOKEN, value = TOKEN_DESC, paramType = HEADER_FIELD, required = true, dataType = STRING_FIELD)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success response", response = ResponseDTO.class),
            @ApiResponse(code = 400, message = "Validation not passed", response = ErrorDTO.class),
            @ApiResponse(code = 403, message = "Incorrect bearer token", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "User not found", response = NotFoundException.class)
    })
    @PutMapping(value = "/update/{userId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> updateUser(
            @PathVariable Integer userId,
            @RequestBody UserReqDTO reqDTO) throws NotFoundException {
        service.updateUser(userId, reqDTO);

        return ResponseEntity.ok(new ResponseDTO<>(SUCCESS, null, "Successful user update", emptyList()));
    }

    /**
     * Get user by Id
     *
     * @param userId User ID
     * @return ResponseEntity
     */
    @ApiOperation(value = "Get a user")
    @ApiImplicitParam(name = TOKEN, value = TOKEN_DESC, paramType = HEADER_FIELD, required = true, dataType = STRING_FIELD)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success response", response = ResponseDTO.class),
            @ApiResponse(code = 400, message = "Validation not passed", response = ErrorDTO.class),
            @ApiResponse(code = 403, message = "Incorrect bearer token", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "User not found", response = NotFoundException.class)
    })
    @GetMapping(value = "/{userId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable Integer userId) throws NotFoundException {
        service.getUser(userId);

        return ResponseEntity.ok(new ResponseDTO<>(SUCCESS, null, "Successful user request", emptyList()));
    }

    /**
     * Get all users
     *
     * @return ResponseEntity
     */
    @ApiOperation(value = "Get all users")
    @ApiImplicitParam(name = TOKEN, value = TOKEN_DESC, paramType = HEADER_FIELD, required = true, dataType = STRING_FIELD)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success response", response = ResponseDTO.class),
            @ApiResponse(code = 400, message = "Validation not passed", response = ErrorDTO.class),
            @ApiResponse(code = 403, message = "Incorrect bearer token", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "User not found", response = NotFoundException.class)
    })
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> getAllUsers() throws NotFoundException {
        return ResponseEntity.ok(new ResponseDTO<>(SUCCESS, service.getAllUsers(),
                "Successful user list request", emptyList()));
    }

    /**
     * Delete user by Id
     *
     * @param userId User ID
     * @return ResponseEntity
     */
    @ApiOperation(value = "Get a user")
    @ApiImplicitParam(name = TOKEN, value = TOKEN_DESC, paramType = HEADER_FIELD, required = true, dataType = STRING_FIELD)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success response", response = ResponseDTO.class),
            @ApiResponse(code = 400, message = "Validation not passed", response = ErrorDTO.class),
            @ApiResponse(code = 403, message = "Incorrect bearer token", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "User not found", response = NotFoundException.class)
    })
    @DeleteMapping(value = "/{userId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> deleteUserById(@PathVariable Integer userId) throws NotFoundException {
        service.deleteUser(userId);

        return ResponseEntity.ok(new ResponseDTO<>(SUCCESS, null, "Successful deleted user", emptyList()));
    }

}
