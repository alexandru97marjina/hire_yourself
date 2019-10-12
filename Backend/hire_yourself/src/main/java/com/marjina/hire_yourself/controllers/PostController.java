package com.marjina.hire_yourself.controllers;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.response.ErrorDTO;
import com.marjina.hire_yourself.common.response.ResponseDTO;
import com.marjina.hire_yourself.services.post.PostService;
import com.marjina.hire_yourself.services.post.dto.PostReqDTO;
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
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService service;

    /**
     * Create a post
     *
     * @param reqDTO PostReqDto
     * @return ResponseEntity
     */
    @ApiOperation(value = "Create a post")
    @ApiImplicitParam(name = TOKEN, value = TOKEN_DESC, paramType = HEADER_FIELD, required = true, dataType = STRING_FIELD)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success response", response = ResponseDTO.class),
            @ApiResponse(code = 400, message = "Validation not passed", response = ErrorDTO.class),
            @ApiResponse(code = 403, message = "Incorrect bearer token", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "Post not found", response = NotFoundException.class)
    })
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> createPost(@RequestBody PostReqDTO reqDTO) throws NotFoundException, ParseException {
        service.createPost(reqDTO);

        return ResponseEntity.ok(new ResponseDTO<>(SUCCESS, null, "Successful post create", emptyList()));
    }

    /**
     * Update a post
     *
     * @param reqDTO PostReqDto
     * @param postId PostId
     * @return ResponseEntity
     */
    @ApiOperation(value = "Update a post")
    @ApiImplicitParam(name = TOKEN, value = TOKEN_DESC, paramType = HEADER_FIELD, required = true, dataType = STRING_FIELD)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success response", response = ResponseDTO.class),
            @ApiResponse(code = 400, message = "Validation not passed", response = ErrorDTO.class),
            @ApiResponse(code = 403, message = "Incorrect bearer token", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "User not found", response = NotFoundException.class)
    })
    @PutMapping(value = "/{postId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> updatePost(
            @PathVariable Integer postId,
            @RequestBody PostReqDTO reqDTO) throws NotFoundException, ParseException {
        service.updatePost(postId, reqDTO);

        return ResponseEntity.ok(new ResponseDTO<>(SUCCESS, null, "Successful post update", emptyList()));
    }

    /**
     * Get a post by Id
     *
     * @param postId Post Id
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
    @GetMapping(value = "/{postId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> getPostById(
            @PathVariable Integer postId) throws NotFoundException {
        return ResponseEntity.ok(new ResponseDTO<>(SUCCESS, service.getPostById(postId), "Successful post request", emptyList()));
    }

    /**
     * Delete a post by Id
     *
     * @param postId Post Id
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
    @DeleteMapping(value = "/{postId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> deletePostById(@PathVariable Integer postId) throws NotFoundException {
        service.deletePostById(postId);

        return ResponseEntity.ok(new ResponseDTO<>(SUCCESS, null, "Successful post delete", emptyList()));
    }

    /**
     * Get list of posts
     *
     * @return ResponseEntity
     */
    @ApiOperation(value = "Get all posts")
    @ApiImplicitParam(name = TOKEN, value = TOKEN_DESC, paramType = HEADER_FIELD, required = true, dataType = STRING_FIELD)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success response", response = ResponseDTO.class),
            @ApiResponse(code = 400, message = "Validation not passed", response = ErrorDTO.class),
            @ApiResponse(code = 403, message = "Incorrect bearer token", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "User not found", response = NotFoundException.class)
    })
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> getAllPosts() throws NotFoundException {
        return ResponseEntity.ok(new ResponseDTO<>(SUCCESS, service.getAllPosts(), "Successful post list request", emptyList()));
    }

}
