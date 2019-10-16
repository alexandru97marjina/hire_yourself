package com.marjina.hire_yourself.controllers;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.response.ErrorDTO;
import com.marjina.hire_yourself.common.response.ResponseDTO;
import com.marjina.hire_yourself.services.activity.ActivityService;
import com.marjina.hire_yourself.services.activity.dto.ActivityReqDTO;
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
@RequestMapping("/api/activity")
public class ActivityController {

    @Autowired
    private ActivityService service;

    /**
     * Create a activity
     *
     * @param reqDTO ActivityReqDTO
     * @return ResponseEntity
     */
    @ApiOperation(value = "Create a activity field")
    @ApiImplicitParam(name = TOKEN, value = TOKEN_DESC, paramType = HEADER_FIELD, required = true, dataType = STRING_FIELD)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success response", response = ResponseDTO.class),
            @ApiResponse(code = 400, message = "Validation not passed", response = ErrorDTO.class),
            @ApiResponse(code = 403, message = "Incorrect bearer token", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "Activity not found", response = NotFoundException.class)
    })
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> createActivity(@RequestBody ActivityReqDTO reqDTO) throws NotFoundException {
        service.createActivity(reqDTO);

        return ResponseEntity.ok(new ResponseDTO<>(SUCCESS, null, "Successful activity create", emptyList()));
    }

    /**
     * Update a activity
     *
     * @param reqDTO     ActivityReqDTO
     * @param activityId Activity Id
     * @return ResponseEntity
     */
    @ApiOperation(value = "Update a activity field")
    @ApiImplicitParam(name = TOKEN, value = TOKEN_DESC, paramType = HEADER_FIELD, required = true, dataType = STRING_FIELD)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success response", response = ResponseDTO.class),
            @ApiResponse(code = 400, message = "Validation not passed", response = ErrorDTO.class),
            @ApiResponse(code = 403, message = "Incorrect bearer token", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "Activity not found", response = NotFoundException.class)
    })
    @PutMapping(value = "/{activityId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> updateActivity(
            @PathVariable Integer activityId, @RequestBody ActivityReqDTO reqDTO) throws NotFoundException {
        service.updateActivity(activityId, reqDTO);

        return ResponseEntity.ok(new ResponseDTO<>(SUCCESS, null, "Successful activity update", emptyList()));
    }

    /**
     * Get a activity by id
     *
     * @param activityId Activity Id
     * @return ResponseEntity
     */
    @ApiOperation(value = "Update a activity field")
    @ApiImplicitParam(name = TOKEN, value = TOKEN_DESC, paramType = HEADER_FIELD, required = true, dataType = STRING_FIELD)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success response", response = ResponseDTO.class),
            @ApiResponse(code = 400, message = "Validation not passed", response = ErrorDTO.class),
            @ApiResponse(code = 403, message = "Incorrect bearer token", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "Activity not found", response = NotFoundException.class)
    })
    @GetMapping(value = "/{activityId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> getActivityById(@PathVariable Integer activityId) throws NotFoundException {

        return ResponseEntity.ok(new ResponseDTO<>(SUCCESS, service.getActivityById(activityId), "Successful activity request", emptyList()));
    }

    /**
     * Delete a activity by id
     *
     * @param activityId Activity Id
     * @return ResponseEntity
     */
    @ApiOperation(value = "Delete a activity field")
    @ApiImplicitParam(name = TOKEN, value = TOKEN_DESC, paramType = HEADER_FIELD, required = true, dataType = STRING_FIELD)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success response", response = ResponseDTO.class),
            @ApiResponse(code = 400, message = "Validation not passed", response = ErrorDTO.class),
            @ApiResponse(code = 403, message = "Incorrect bearer token", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "Activity not found", response = NotFoundException.class)
    })
    @DeleteMapping(value = "/{activityId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> deleteActivityById(@PathVariable Integer activityId) throws NotFoundException {
        service.deleteActivity(activityId);

        return ResponseEntity.ok(new ResponseDTO<>(SUCCESS, null, "Successful activity delete", emptyList()));
    }

    /**
     * Get a list of activity
     *
     * @return ResponseEntity
     */
    @ApiOperation(value = "Get a list of activity")
    @ApiImplicitParam(name = TOKEN, value = TOKEN_DESC, paramType = HEADER_FIELD, required = true, dataType = STRING_FIELD)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success response", response = ResponseDTO.class),
            @ApiResponse(code = 400, message = "Validation not passed", response = ErrorDTO.class),
            @ApiResponse(code = 403, message = "Incorrect bearer token", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "Activity not found", response = NotFoundException.class)
    })
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> getListOfActivity() throws NotFoundException {
        return ResponseEntity.ok(new ResponseDTO<>(SUCCESS, service.getActivityList(), "Successful activity list request", emptyList()));
    }

    /**
     * Get a list of activity
     *
     * @return ResponseEntity
     */
    @ApiOperation(value = "Get a list of activity")
    @ApiImplicitParam(name = TOKEN, value = TOKEN_DESC, paramType = HEADER_FIELD, required = true, dataType = STRING_FIELD)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success response", response = ResponseDTO.class),
            @ApiResponse(code = 400, message = "Validation not passed", response = ErrorDTO.class),
            @ApiResponse(code = 403, message = "Incorrect bearer token", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "Activity not found", response = NotFoundException.class)
    })
    @GetMapping(value = "/names", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> getListOfActivityNames() throws NotFoundException {
        return ResponseEntity.ok(new ResponseDTO<>(SUCCESS, service.getActivityNameList(), "Successful activity list request", emptyList()));
    }

}
