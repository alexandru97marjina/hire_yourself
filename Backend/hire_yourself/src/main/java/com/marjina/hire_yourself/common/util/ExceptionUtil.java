package com.marjina.hire_yourself.common.util;

import com.marjina.hire_yourself.common.helper.exception.AppException;
import com.marjina.hire_yourself.common.response.ErrorDTO;

import java.util.Collections;

public final class ExceptionUtil {

    /**
     * Create new app exception with custom error;
     *
     * @param code  HttpCode
     * @param field Field of error
     * @param msg   Error msg
     * @return AppException
     */
    public static AppException newAppException(int code, String field, String msg) {
        return new AppException(code, Collections.singletonList(new ErrorDTO(null, code, field, msg)));
    }


}
