package com.marjina.hire_yourself.common.response;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;

import static com.marjina.hire_yourself.common.util.consts.GlobalConst.FIELD_STR;
import static com.marjina.hire_yourself.common.util.consts.GlobalConst.URI_FIELD;

/**
 * Build custom error response for each error
 */
@Data
public class ErrorDTO implements Serializable, Comparable<ErrorDTO> {

    private static final Logger log = LogManager.getLogger();
    private static final String CODE_FIELD = "code";
    private static final long serialVersionUID = 1L;
    private static final String DESCRIPTION_FIELD = "description";

    private final String uri;
    private final int code;
    private final String field;
    private final String message;

    /**
     * Init constructor
     *
     * @param code Message about error problem
     */
    public ErrorDTO(int code) {
        this(null, code, null, null);
    }

    /**
     * Init constructor
     *
     * @param uri         Place where error occurred
     * @param code        Http status code
     * @param field       Field which made error exception
     * @param description Message about error problem
     */
    public ErrorDTO(String uri, int code, String field, String description) {
        this.uri = uri;
        this.code = code;
        this.field = field;
        this.message = description;

        // Save to logs
        log.error(toString());
    }

    /**
     * Error message for logging
     *
     * @return String value
     */
    @Override
    public String toString() {
        String str = this.getClass().getSimpleName();
        str += "{";
        str += URI_FIELD + "=" + uri + ", ";
        str += CODE_FIELD + "=" + code + ", ";
        str += FIELD_STR + "=" + field + ", ";
        str += DESCRIPTION_FIELD + "=" + message + '}';
        str += "}";

        return str;
    }

    /**
     * Compare objects ErrorDTO by messages bytes length
     *
     * @param errorDTO ErrorDTO
     * @return 1 if received object greater, or -1 in all other cases
     */
    @Override
    public int compareTo(ErrorDTO errorDTO) {
        String nextError = errorDTO.getMessage() + errorDTO.getField();
        String currentError = this.getMessage() + this.getField();

        return currentError.getBytes().length > nextError.getBytes().length ? 1 : -1;
    }

}
