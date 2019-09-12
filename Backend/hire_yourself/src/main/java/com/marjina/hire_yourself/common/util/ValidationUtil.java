package com.marjina.hire_yourself.common.util;

import com.marjina.hire_yourself.common.response.ErrorDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.marjina.hire_yourself.common.util.consts.GlobalConst.*;
import static org.apache.commons.lang3.math.NumberUtils.isDigits;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;

public class ValidationUtil {

    private static final String NOT_INT_MSG = "{valid.digits}";
    private static final String INVALID_LIST_MSG = "{valid.list}";
    private static final String FIELD_NOT_NULL_MSG = "{field.not.null}";
    private static final String NOT_VALID_SIZE_MSG = "{string.valid.size}";
    private static final String INVALID_LIST_SIZE_MSG = "{list.size.valid}";
    private static final String LIST_ELEMENTS_NOT_UNIQUE_MSG = "{list.elems.unique}";
    private static final String LIST_ELEMENTS_NOT_VALID = "{list.elems.size}";
    private static final String INVALID_LOG_TYPE_MSG = "{invalid.log.type}";
    private static final String VALID_DEVICE_MSG = "{valid.device}";
    private static final String VALID_SEARCH_TYPE = "{valid.search.type}";
    private static final int MAX_DIGITS_LENGTH = 10;
    private static final String FIELD_VALUE_RANGE_MSG = "{field.value.range}";
    private static final String INVALID_PAGE = "{invalid.page}";

    /**
     * Check if val is less than max limit of characters
     *
     * @param val String value which will be checked on validation
     * @param max Max number of characters which String val can take
     * @return boolean
     */
    public static boolean isLessThanMaxChars(String val, int max) {
        return StringUtils.length(val) < max;
    }

    /**
     * String validation which must not be longer than max length
     *
     * @param field     Field which was validated
     * @param val       Value which must be validate
     * @param maxLength Max length of value
     * @param context   context in which the constraint is evaluated
     * @return boolean status
     */

    public static boolean validateStringSize(String field, String val, int maxLength, ConstraintValidatorContext context) {
        boolean isValid = isLessThanMaxChars(val, maxLength);

        if (!isValid) {
            String msgTemplate = field + BLANK_SPACE + NOT_VALID_SIZE_MSG + maxLength;
            buildErrorMsg(msgTemplate, context);
        }

        return isValid;
    }

    /**
     * Check if given parameter has expected size
     *
     * @param param   Parameter to validate
     * @param minSize Min size
     * @param maxSize Max size
     * @return Boolean state
     */
    public static boolean paramHasExpectedSize(int param, int minSize, int maxSize) {
        return param >= minSize && param <= maxSize;
    }

    /**
     * Validate number to be not blank, be in range between  and integer value
     *
     * @param number   Number value
     * @param field    Field
     * @param minValue Min number value
     * @param maxValue Max number value
     * @param context  Validator context
     * @return Boolean state
     */
    public static boolean isValidNumber(
            String number, String field, int minValue, int maxValue, ConstraintValidatorContext context) {
        boolean isValid = StringUtils.isNotBlank(number);

        if (isValid) {
            isValid = isDigits(number);

            if (isValid) {
                int ind = NumberUtils.toInt(number);
                isValid = paramHasExpectedSize(ind, minValue, maxValue);

                if (!isValid) {
                    String msg = field + BLANK_SPACE + FIELD_VALUE_RANGE_MSG;
                    buildErrorMsg(msg, context);
                }
            } else {
                String msg = field + BLANK_SPACE + NOT_INT_MSG;
                buildErrorMsg(msg, context);
            }
        } else {
            String msg = field + BLANK_SPACE + FIELD_NOT_NULL_MSG;
            buildErrorMsg(msg, context);
        }

        return isValid;
    }

    /**
     * Limit validations which must be integer format and must not be bigger than max limit
     *
     * @param limit    Limit of extracted categories per page
     * @param maxLimit Max value which limit can get
     * @param context  context in which the constraint is evaluated
     * @return boolean status
     */
    public static boolean validateLimit(String limit, int maxLimit, ConstraintValidatorContext context) {
        boolean isValid = StringUtils.isBlank(limit);

        if (!isValid) {
            isValid = limitIsDigits(limit, context);

            if (isValid) {
                isValid = validateLimitSize(limit, maxLimit, context);
            }
        }

        return isValid;
    }

    /**
     * Validate limit field to be integer
     *
     * @param limit   Limit per page
     * @param context Validation context for changing error messages
     * @return boolean
     */
    public static boolean limitIsDigits(String limit, ConstraintValidatorContext context) {
        boolean isValid = isDigits(limit);

        if (!isValid) {
            String msgTemplate = LIMIT_FIELD + BLANK_SPACE + NOT_INT_MSG;
            buildErrorMsg(msgTemplate, context);
        }

        return isValid;
    }

    /**
     * Validate limit field size
     *
     * @param limit    Limit per page
     * @param maxLimit Max value of limit
     * @param context  Validation context for changing error messages
     * @return boolean
     */
    public static boolean validateLimitSize(String limit, int maxLimit, ConstraintValidatorContext context) {
        boolean isValid = !(NumberUtils.toInt(limit) > maxLimit || limit.length() > 2);

        if (!isValid) {
            String msgTemplate = LIMIT_FIELD + BLANK_SPACE + NOT_VALID_SIZE_MSG + maxLimit;
            buildErrorMsg(msgTemplate, context);
        }

        return isValid;
    }

    /**
     * Page validations which must be integer format
     *
     * @param page    Number of category page
     * @param context context in which the constraint is evaluated
     * @return boolean status
     */
    public static boolean validatePage(String page, ConstraintValidatorContext context) {
        boolean isValid = StringUtils.isNotBlank(page) && isDigits(page) && Integer.parseInt(page) > 0;

        if (!isValid) {
            buildErrorMsg(INVALID_PAGE, context);
        } else if (!isLessThanMaxChars(page, MAX_DIGITS_LENGTH)) {
            String msgTemplate = PAGE_FIELD + BLANK_SPACE + NOT_VALID_SIZE_MSG + MAX_DIGITS_LENGTH;
            buildErrorMsg(msgTemplate, context);
            isValid = false;
        }

        return isValid;
    }

    /**
     * Get field errors in ErrorDTO format
     *
     * @param errors Set of ConstraintViolation errors
     * @return List with ErrorDTO items
     */
    public static List<ErrorDTO> getFormattedFieldErrors(Set<ConstraintViolation<?>> errors) {
        return errors.stream().map(e -> {
            String propertyPath = e.getPropertyPath().toString();
            String field = getField(propertyPath);

            return new ErrorDTO(null, SC_BAD_REQUEST, field, e.getMessage());
        }).collect(Collectors.toList());
    }

    /**
     * Verify if given list doesn't contains given element
     *
     * @param list List
     * @param elem Element to verify
     * @param <T>  List data type
     * @return Boolean state
     */
    public static <T> boolean noneMatchInList(List<T> list, T elem) {
        return list == null || list.stream().noneMatch(e -> e.equals(elem));
    }

    /**
     * Get class errors in ErrorDTO format
     *
     * @param errors Set of MethodArgument errors
     * @return List with ErrorDTO items
     */
    public static List<ErrorDTO> getFormattedClassErrors(List<ObjectError> errors) {
        return errors.stream().map(e -> {
            String field = null;
            String[] errorCodes = e.getCodes();

            if (ArrayUtils.isNotEmpty(errorCodes) && errorCodes.length >= 2) {
                String errorCode = errorCodes[1];
                field = getField(errorCode);
            }

            return new ErrorDTO(null, SC_BAD_REQUEST, field, e.getDefaultMessage());
        }).collect(Collectors.toList());
    }

    /**
     * Get field from received path of errors
     *
     * @param path Error path
     * @return Field
     */
    public static String getField(String path) {
        int position = path.lastIndexOf('.') + 1;

        return path.substring(position);
    }

    /**
     * Validate given val to be not null
     *
     * @param field   Field which was validated
     * @param val     Value which must be validate
     * @param context context in which the constraint is evaluated
     * @return boolean status
     */
    public static boolean validateString(String field, String val, ConstraintValidatorContext context) {
        boolean isValid = StringUtils.isNotBlank(val);

        if (!isValid) {
            String msgTemplate = field + BLANK_SPACE + FIELD_NOT_NULL_MSG;
            buildErrorMsg(msgTemplate, context);
        }

        return isValid;
    }


    /**
     * Validate given val to be not null and lessThanMaxChars
     *
     * @param field     Field which was validated
     * @param val       Value which must be validate
     * @param maxLength Max length of value
     * @param context   context in which the constraint is evaluated
     * @return boolean status
     */
    public static boolean validateString(String field, String val, int maxLength, ConstraintValidatorContext context) {
        boolean isValid = StringUtils.isNotBlank(val);

        if (isValid) {
            isValid = validateStringSize(field, val, maxLength, context);
        } else {
            String msgTemplate = field + BLANK_SPACE + FIELD_NOT_NULL_MSG;
            buildErrorMsg(msgTemplate, context);
        }

        return isValid;
    }

    /**
     * Use HashSet to keep only unique elements
     *
     * @param list         List
     * @param expectedSize Expected size
     * @return Boolean state
     */
    public static boolean keepUniqueListValues(List list, int expectedSize) {
        Set<?> set = new HashSet(list);

        return set.size() == expectedSize;
    }

    /**
     * ID validation which must be lower than max length and must be a positive integer
     *
     * @param id        id to be validated
     * @param field     field
     * @param maxLength max length for id
     * @param context   constraint validator context to build error message
     * @return boolean status
     */
    public static boolean isValidId(String id, String field, int maxLength, ConstraintValidatorContext context) {
        boolean isValid = isDigits(id);

        if (!isValid) {
            String msg = id + BLANK_SPACE + NOT_INT_MSG;
            buildErrorMsg(msg, context);
        } else {
            if (!isLessThanMaxChars(id, maxLength)) {
                String msg = field + BLANK_SPACE + NOT_VALID_SIZE_MSG + maxLength;
                buildErrorMsg(msg, context);

                isValid = false;
            }
        }

        return isValid;
    }

    /**
     * Check if list is not null, has expected size and if unique param is true,
     * check if list has only unique elements. Also if parameter {@code validateSize} is 'true',
     * will do validation for size, otherwise set {@code expectedSize} to current list size,
     * to not fail on {@code keepOnlyUniqueElements()} validation.
     *
     * @param list         List to validate
     * @param validateSize Need to validate size
     * @param expectedSize Expected list size
     * @param unique       Need to contain unique elements
     * @param context      Validator context
     * @return Boolean state
     */
    public static boolean isValidList(List list, boolean validateSize, int expectedSize, boolean unique,
                                      ConstraintValidatorContext context) {
        boolean isValid = CollectionUtils.isNotEmpty(list);

        if (isValid) {
            if (validateSize) {
                isValid = list.size() == expectedSize;
            } else {
                expectedSize = list.size();
            }

            if (isValid && unique) {
                isValid = keepUniqueListValues(list, expectedSize);

                if (!isValid) {
                    buildErrorMsg(LIST_ELEMENTS_NOT_UNIQUE_MSG, context);
                } else {

                }
            } else {
                buildErrorMsg(INVALID_LIST_SIZE_MSG, context);
            }
        } else {
            buildErrorMsg(INVALID_LIST_MSG, context);
        }

        return isValid;
    }

    /**
     * Check if list is not null, is not bigger than expected size and if unique param is true,
     * check if list has only unique elements, if validateListWord is true then validate List of string words.
     * Also if parameter {@code validateSize} is 'true',
     * will do validation for size, otherwise set {@code maxListSize} to current list size,
     * to not fail on {@code keepOnlyUniqueElements()} validation.
     *
     * @param list             List to validate
     * @param validateSize     Need to validate size
     * @param maxListSize      Max list size
     * @param unique           Need to contain unique elements
     * @param wordExpectedSize List words expected size
     * @param validateListWord Validate List Words
     * @param context          Validator context
     * @return Boolean state
     */
    public static boolean isValidList(List<String> list, boolean validateSize, int maxListSize, boolean unique,
                                      int wordExpectedSize, boolean validateListWord, ConstraintValidatorContext context) {
        boolean isValid = CollectionUtils.isNotEmpty(list);

        if (isValid) {
            if (validateSize) {
                isValid = list.size() < maxListSize;
            } else {
                maxListSize = list.size();
            }

            if (isValid && unique) {
                isValid = keepUniqueListValues(list, maxListSize);

                if (!isValid) {
                    buildErrorMsg(LIST_ELEMENTS_NOT_UNIQUE_MSG, context);
                }
            }

            if (isValid && validateListWord) {
                isValid = validateListWords(list, wordExpectedSize, context);

                if (!isValid) {
                    buildErrorMsg(LIST_ELEMENTS_NOT_VALID, context);
                }
            } else {
                buildErrorMsg(INVALID_LIST_SIZE_MSG, context);
            }
        } else {
            buildErrorMsg(INVALID_LIST_MSG, context);
        }

        return isValid;
    }

    /**
     * Check if words of a String List are valid based on size and not be blank
     *
     * @param list        List of String to be validated
     * @param maxWordSize Max wordSize in a list
     * @param context     Validator context
     * @return Boolean state
     */
    public static boolean validateListWords(List<String> list, Integer maxWordSize, ConstraintValidatorContext context) {
        String msgTemplate;
        Boolean isValid = true;

        for (String word : list) {
            isValid = StringUtils.isNotBlank(word);

            if (isValid) {
                isValid = isLessThanMaxChars(word, maxWordSize);

                if (!isValid) {
                    msgTemplate = word + BLANK_SPACE + NOT_VALID_SIZE_MSG + maxWordSize;
                    buildErrorMsg(msgTemplate, context);
                }
            } else {
                msgTemplate = FIELD_NOT_NULL_MSG;
                buildErrorMsg(msgTemplate, context);
            }
        }

        return isValid;

    }

    /**
     * Check if search type is not null and is one of allowed
     *
     * @param searchType  Search type
     * @param searchTypes Allowed search types
     * @param context     ConstraintValidatorContext
     * @return boolean state
     */
    public static boolean isValidSearchType(String searchType, String searchTypes, ConstraintValidatorContext context) {
        boolean isValid = searchType != null && ValidationUtil.containsValue(searchType, searchTypes);

        if (!isValid) {
            String msgTemplate = VALID_SEARCH_TYPE + searchTypes;
            buildErrorMsg(msgTemplate, context);
        }

        return isValid;
    }

    /**
     * Check if str contains val
     *
     * @param val String value which will be checked on validation
     * @param str String which must contain val
     * @return boolean state
     */
    public static boolean containsValue(String val, String str) {
        String[] parts = str.split(COMMA);

        return Arrays.asList(parts).contains(val);
    }

    /**
     * Build error message for given context and msgTemplate
     *
     * @param msgTemplate Validation message template
     * @param context     Constraint validation context
     */
    public static void buildErrorMsg(String msgTemplate, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(msgTemplate).addConstraintViolation();
    }

}
