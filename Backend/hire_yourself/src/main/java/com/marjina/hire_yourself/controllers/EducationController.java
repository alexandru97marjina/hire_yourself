package com.marjina.hire_yourself.controllers;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.response.ErrorDTO;
import com.marjina.hire_yourself.common.response.ResponseDTO;
import com.marjina.hire_yourself.services.education.EducationService;
import com.marjina.hire_yourself.services.education.dto.EducationReqDTO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

import static com.marjina.hire_yourself.common.util.consts.GlobalConst.*;
import static java.util.Collections.emptyList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/educations")
public class EducationController {

    @Autowired
    private EducationService service;

    /**
     * Create a education entity
     *
     * @param reqDTO EducationReqDto
     * @return ResponseEntity
     */
    @ApiOperation(value = "Create a education")
    @ApiImplicitParam(name = TOKEN, value = TOKEN_DESC, paramType = HEADER_FIELD, required = true, dataType = STRING_FIELD)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success response", response = ResponseDTO.class),
            @ApiResponse(code = 400, message = "Validation not passed", response = ErrorDTO.class),
            @ApiResponse(code = 403, message = "Incorrect bearer token", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "Education not found", response = NotFoundException.class)
    })
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> createEducation(@RequestBody EducationReqDTO reqDTO) throws NotFoundException, ParseException {
        service.createEducation(reqDTO);

        return ResponseEntity.ok(new ResponseDTO<>(SUCCESS, null, "Successful education create", emptyList()));
    }

    /**
     * Update a education
     *
     * @param reqDTO      EducationReqDTO
     * @param educationId EducationId
     * @return ResponseEntity
     */
    @ApiOperation(value = "Update a education")
    @ApiImplicitParam(name = TOKEN, value = TOKEN_DESC, paramType = HEADER_FIELD, required = true, dataType = STRING_FIELD)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success response", response = ResponseDTO.class),
            @ApiResponse(code = 400, message = "Validation not passed", response = ErrorDTO.class),
            @ApiResponse(code = 403, message = "Incorrect bearer token", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "Education not found", response = NotFoundException.class)
    })
    @PutMapping(value = "/{educationId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> updateEducation(
            @PathVariable Integer educationId,
            @RequestBody EducationReqDTO reqDTO) throws NotFoundException, ParseException {
        service.updateEducation(educationId, reqDTO);

        return ResponseEntity.ok(new ResponseDTO<>(SUCCESS, null, "Successful post update", emptyList()));
    }

    /**
     * Get a education by Id
     *
     * @param educationId Education Id
     * @return ResponseEntity
     */
    @ApiOperation(value = "Get post by id")
    @ApiImplicitParam(name = TOKEN, value = TOKEN_DESC, paramType = HEADER_FIELD, required = true, dataType = STRING_FIELD)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success response", response = ResponseDTO.class),
            @ApiResponse(code = 400, message = "Validation not passed", response = ErrorDTO.class),
            @ApiResponse(code = 403, message = "Incorrect bearer token", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "User not found", response = NotFoundException.class)
    })
    @GetMapping(value = "/{educationId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> getEducationById(
            @PathVariable Integer educationId) throws NotFoundException {
        return ResponseEntity.ok(new ResponseDTO<>(SUCCESS, service.getEducationById(educationId), "Successful education request", emptyList()));
    }

    /**
     * Delete a post by Id
     *
     * @param educationId Post Id
     * @return ResponseEntity
     */
    @ApiOperation(value = "Get education by id")
    @ApiImplicitParam(name = TOKEN, value = TOKEN_DESC, paramType = HEADER_FIELD, required = true, dataType = STRING_FIELD)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success response", response = ResponseDTO.class),
            @ApiResponse(code = 400, message = "Validation not passed", response = ErrorDTO.class),
            @ApiResponse(code = 403, message = "Incorrect bearer token", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "User not found", response = NotFoundException.class)
    })
    @DeleteMapping(value = "/{educationId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> deleteEducationById(@PathVariable Integer educationId) throws NotFoundException {
        service.deleteEducation(educationId);

        return ResponseEntity.ok(new ResponseDTO<>(SUCCESS, null, "Successful post delete", emptyList()));
    }

    /**
     * Get list of educations
     *
     * @return ResponseEntity
     */
    @ApiOperation(value = "Get all educations")
    @ApiImplicitParam(name = TOKEN, value = TOKEN_DESC, paramType = HEADER_FIELD, required = true, dataType = STRING_FIELD)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success response", response = ResponseDTO.class),
            @ApiResponse(code = 400, message = "Validation not passed", response = ErrorDTO.class),
            @ApiResponse(code = 403, message = "Incorrect bearer token", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "Education not found", response = NotFoundException.class)
    })
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> getAllEducations() throws NotFoundException {
        return ResponseEntity.ok(new ResponseDTO<>(SUCCESS, service.getListOfEducations(), "Successful education list request", emptyList()));
    }

}
