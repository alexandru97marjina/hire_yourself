package com.marjina.hire_yourself.common.helper.exception;

import com.marjina.hire_yourself.common.response.ErrorDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import static com.marjina.hire_yourself.common.util.consts.GlobalConst.SOMETHING_WRONG;

/**
 * Custom exception which allows to show the field which caused exception in case if it exists
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppException extends Exception {

    private List<ErrorDTO> errors;
    private int code;

    /**
     * Init constructor
     */
    public AppException() {
        super();
    }

    /**
     * Init custom AppException
     *
     * @param code   Status code
     * @param errors List of errors
     */
    public AppException(int code, List<ErrorDTO> errors) {
        super(SOMETHING_WRONG);
        this.errors = errors;
        this.code = code;
    }

}
