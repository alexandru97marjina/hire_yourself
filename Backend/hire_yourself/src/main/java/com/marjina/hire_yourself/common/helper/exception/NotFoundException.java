package com.marjina.hire_yourself.common.helper.exception;

import com.marjina.hire_yourself.common.util.translates.ValidTransl;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class NotFoundException extends Exception {

    private String message;

    /**
     * Init constructor
     */
    public NotFoundException() {
        super();
    }

    /**
     * Init new exception with specific message and field
     *
     * @param msgTemplate thrown message
     */
    public NotFoundException(String msgTemplate) {
        super(ValidTransl.translate(msgTemplate));
        this.message = ValidTransl.translate(msgTemplate);
    }

}
