package com.marjina.hire_yourself.common.util;

import com.marjina.hire_yourself.common.helper.exception.AppException;
import io.micrometer.core.instrument.util.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

import static com.marjina.hire_yourself.common.util.consts.GlobalConst.LOCAL_HOST_IP;
import static com.marjina.hire_yourself.common.util.consts.GlobalConst.USER_IP_FIELD;
import static org.apache.http.HttpStatus.SC_NOT_ACCEPTABLE;

public class RequestUtil {

    /**
     * Check if it can be converted to URI
     *
     * @param s String
     * @return URI builder
     */
    public static URIBuilder fromString(String s) {
        try {
            return new URIBuilder(s);
        } catch (URISyntaxException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Get client ip from request
     *
     * @param request Request data
     * @return string User ip
     */
    public static String getClientIp(HttpServletRequest request) throws AppException {
        String ipFromHeader = request.getHeader("X-FORWARDED-FOR");
        String clientIp = StringUtils.isNotBlank(ipFromHeader) ? ipFromHeader : request.getRemoteAddr();

        if (clientIp != null && clientIp.equals(LOCAL_HOST_IP)) {
            try {
                // Get localhost ip
                InetAddress inetAddress = InetAddress.getLocalHost();
                clientIp = inetAddress.getHostAddress();
            } catch (UnknownHostException e) {
                System.out.println(e);
                throw ExceptionUtil.newAppException(SC_NOT_ACCEPTABLE, USER_IP_FIELD, "Unknown host exception");
            }
        }

        return clientIp;
    }

}