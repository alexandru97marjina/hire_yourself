package com.marjina.hire_yourself.controllers;

import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.response.ErrorDTO;
import com.marjina.hire_yourself.common.response.ResponseDTO;
import com.marjina.hire_yourself.services.favorites.FavoritesService;
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
@RequestMapping("/api/favorites")
public class FavoriteController {

    @Autowired
    private FavoritesService service;

    /**
     * Add post to favorites
     *
     * @param userId User Identifier
     * @param postId Post Identifier
     * @return ResponseEntity
     */
    @ApiOperation(value = "Add post to favorites")
    @ApiImplicitParam(name = TOKEN, value = TOKEN_DESC, paramType = HEADER_FIELD, required = true, dataType = STRING_FIELD)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success response", response = ResponseDTO.class),
            @ApiResponse(code = 400, message = "Validation not passed", response = ErrorDTO.class),
            @ApiResponse(code = 403, message = "Incorrect bearer token", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "Post not found", response = NotFoundException.class)
    })
    @GetMapping(value = "/{userId}/{postId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> addPostToFavorites(@PathVariable Integer userId, @PathVariable Integer postId) throws NotFoundException {
        service.addToFavorites(userId, postId);

        return ResponseEntity.ok(new ResponseDTO<>(SUCCESS, null, "Successful favorite added", emptyList()));
    }

    /**
     * Add post to favorites
     *
     * @param userId User Identifier
     * @param postId Post Identifier
     * @return ResponseEntity
     */
    @ApiOperation(value = "Remove post from favorites")
    @ApiImplicitParam(name = TOKEN, value = TOKEN_DESC, paramType = HEADER_FIELD, required = true, dataType = STRING_FIELD)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success response", response = ResponseDTO.class),
            @ApiResponse(code = 400, message = "Validation not passed", response = ErrorDTO.class),
            @ApiResponse(code = 403, message = "Incorrect bearer token", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "Post not found", response = NotFoundException.class)
    })
    @DeleteMapping(value = "/{userId}/{postId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> deletePostFromFavorites(@PathVariable Integer userId, @PathVariable Integer postId) throws NotFoundException {
        service.removeFromFavorites(userId, postId);

        return ResponseEntity.ok(new ResponseDTO<>(SUCCESS, null, "Successful favorite removed", emptyList()));
    }

    /**
     * Get user favorites
     *
     * @param userId User Identifier
     * @return ResponseEntity
     */
    @ApiOperation(value = "Add post to favorites")
    @ApiImplicitParam(name = TOKEN, value = TOKEN_DESC, paramType = HEADER_FIELD, required = true, dataType = STRING_FIELD)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success response", response = ResponseDTO.class),
            @ApiResponse(code = 400, message = "Validation not passed", response = ErrorDTO.class),
            @ApiResponse(code = 403, message = "Incorrect bearer token", response = ErrorDTO.class),
            @ApiResponse(code = 404, message = "Post not found", response = NotFoundException.class)
    })
    @GetMapping(value = "/{userId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> getUserFavorites(@PathVariable Integer userId) throws NotFoundException {
        return ResponseEntity.ok(new ResponseDTO<>(SUCCESS, service.getUserFavorites(userId), "Successful favorite added", emptyList()));
    }

}
