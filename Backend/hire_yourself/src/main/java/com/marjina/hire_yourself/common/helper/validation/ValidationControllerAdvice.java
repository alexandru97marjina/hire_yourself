package com.marjina.hire_yourself.common.helper.validation;

import com.marjina.hire_yourself.common.helper.exception.AppException;
import com.marjina.hire_yourself.common.helper.exception.NotFoundException;
import com.marjina.hire_yourself.common.response.ErrorDTO;
import com.marjina.hire_yourself.common.response.ResponseDTO;
import com.marjina.hire_yourself.common.util.ValidationUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.List;

import static com.marjina.hire_yourself.common.util.consts.GlobalConst.ERROR_STATUS;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;

@ControllerAdvice
public class ValidationControllerAdvice {

    private static final String CONSTRAINT_VIOLATION = "Constraint violation provided";
    private static final String INVALID_METHOD_ARGUMENTS = "Invalid method arguments";

    /**
     * Get error response to web
     *
     * @param ex Exception
     * @return Information about error
     */
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ResponseDTO> processAppException(AppException ex) {
        return new ResponseEntity<>(
                new ResponseDTO<>(ERROR_STATUS, null, ex.getMessage(), ex.getErrors()),
                HttpStatus.valueOf(ex.getCode())
        );
    }

    /**
     * Add custom not found exception
     *
     * @param ex Exception
     * @return Return response
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseDTO> processNotFoundException(NotFoundException ex) {
        return new ResponseEntity<>(
                new ResponseDTO<>(ERROR_STATUS, null, ex.getMessage(), null),
                HttpStatus.valueOf(SC_NOT_FOUND)
        );
    }

    /**
     * Catch MethodArgumentNotValidException and return errors
     *
     * @param exception MethodArgumentNotValidException
     * @return Return response
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> handleMethodArgumentException(MethodArgumentNotValidException exception) {
        List<ErrorDTO> errors = ValidationUtil.getFormattedClassErrors(exception.getBindingResult().getAllErrors());
        Collections.sort(errors);

        return new ResponseEntity<>(
                new ResponseDTO<>(ERROR_STATUS, null, INVALID_METHOD_ARGUMENTS, errors),
                HttpStatus.valueOf(SC_BAD_REQUEST)
        );
    }

    /**
     * Catch constraint exception validation and return errors
     *
     * @param exception ConstraintViolationException
     * @return Return response
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> handleConstraintViolationException(ConstraintViolationException exception) {
        List<ErrorDTO> errors = ValidationUtil.getFormattedFieldErrors(exception.getConstraintViolations());
        Collections.sort(errors);

        return new ResponseEntity<>(
                new ResponseDTO<>(ERROR_STATUS, null, CONSTRAINT_VIOLATION, errors),
                HttpStatus.valueOf(SC_BAD_REQUEST)
        );
    }

}
