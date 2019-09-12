package com.marjina.hire_yourself.common.helper.validation;

import com.marjina.hire_yourself.common.helper.exception.AppException;
import com.marjina.hire_yourself.common.response.ErrorDTO;
import com.marjina.hire_yourself.common.util.translates.ValidTransl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.marjina.hire_yourself.common.util.ObjectUtil.decryptAccessToken;
import static com.marjina.hire_yourself.common.util.consts.GlobalConst.*;
import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;

@Component
public class RequestValidation {

    /**
     * Decrypt given token in 2 parts: decryptedSecret && decryptedTime and validate each one.
     * decryptedSecret must be equals with secret from config
     * decryptedTime must not be less than currentTime - {validTime} milliseconds
     *
     * @param token               Encrypted token. Format: { base64(base64(secretKeyword) + base64(currentTime)) }
     * @param secret              Secret keyword from config
     * @param tokenExpireInterval Time interval in milliseconds after which the token will expire
     * @return boolean status
     */
    public static boolean tokenIsValid(String token, String secret, Long tokenExpireInterval) {
        Map<String, String> decryptedToken = decryptAccessToken(token);

        if (!CollectionUtils.isEmpty(decryptedToken)) {
            String decryptedSecret = decryptedToken.get(SECRET_FIELD);
            Long decryptedTime = Long.valueOf(decryptedToken.get(CURR_TIME_FIELD));

            return secret.equals(decryptedSecret) && System.currentTimeMillis() - decryptedTime < tokenExpireInterval;
        }

        return false;
    }

    /**
     * Validate token given from request. It must be equals with secret keyword from config
     *
     * @param token               Token from request
     * @param secret              Secret keyword from config
     * @param tokenExpireInterval Defines time interval in milliseconds after which the token will expire
     * @throws AppException Thrown exception after validation error
     */
    public void validateToken(String token, String secret, Long tokenExpireInterval) throws AppException {
        if (StringUtils.isEmpty(token) || (!secret.equals(token) && !tokenIsValid(token, secret, tokenExpireInterval))) {
            String msg = ValidTransl.translate("UNAUTHORIZED_TOKEN");
            ErrorDTO errorDTO = new ErrorDTO(null, SC_FORBIDDEN, TOKEN, msg);
            List<ErrorDTO> localErrors = Collections.singletonList(errorDTO);

            throw new AppException(SC_FORBIDDEN, localErrors);
        }
    }

}
