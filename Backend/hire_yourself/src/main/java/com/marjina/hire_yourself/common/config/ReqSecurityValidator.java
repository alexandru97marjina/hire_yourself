package com.marjina.hire_yourself.common.config;

import com.marjina.hire_yourself.common.helper.validation.RequestValidation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

import static com.marjina.hire_yourself.common.util.RequestUtil.getClientIp;
import static com.marjina.hire_yourself.common.util.consts.GlobalConst.TOKEN;

@Component
@Aspect
@EnableAutoConfiguration
public class ReqSecurityValidator {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private MainConfig mainConfig;

    @Autowired
    private RequestValidation requestValidation;

    /**
     * Validate all controllers methods by access token
     *
     * @param pjp Proceeding join point
     * @return ProceedingJoinPoint object
     * @throws Throwable t
     */
    @Around("within(@org.springframework.web.bind.annotation.RestController *)")
    public Object validateAPIRoutesByAccessToken(ProceedingJoinPoint pjp) throws Throwable {
        String clientIp = getClientIp(httpServletRequest);
        String token = httpServletRequest.getHeader(TOKEN);

        requestValidation.validateToken(token, mainConfig.getSecret(), mainConfig.getTokenExpireInterval());

        StringBuilder sb = new StringBuilder()
                .append("{\"url\" : \"").append(httpServletRequest.getRequestURL()).append("\", ")
                .append("\"params\": \"").append(Arrays.toString(pjp.getArgs())).append("\", ")
                .append("\"method\": \"").append(httpServletRequest.getMethod()).append("\", ")
                .append("\"date\": \"").append(new Date(System.currentTimeMillis())).append("\", ")
                .append("\"ip\": \"").append(clientIp).append("\"}");

        System.out.println(sb.toString());

        return pjp.proceed();
    }

}
