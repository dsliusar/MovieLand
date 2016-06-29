package com.dsliusar.web.interceptors;

import com.dsliusar.services.security.AuthenticationService;
import com.dsliusar.tools.annotations.SecurityRolesAllowed;
import com.dsliusar.tools.constants.Constant;
import com.dsliusar.tools.enums.Roles;
import com.dsliusar.tools.exceptions.MovieLandSecurityException;
import com.dsliusar.tools.http.entities.UserSecureTokenEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.UUID;


public class AuthorizationSecurityRoleHandlerInterceptor extends HandlerInterceptorAdapter {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        LOGGER.info("Authorization started");
        MDC.put("requestId", UUID.randomUUID().toString());
        UserSecureTokenEntity userSecureTokenEntity = null;
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        SecurityRolesAllowed roles = method.getAnnotation(SecurityRolesAllowed.class);
        if (roles != null) {
            for (Roles rolesEnum : roles.roles()) {
                if (rolesEnum.equals(Roles.GUEST)) {
                    return super.preHandle(request, response, handler);
                }
                String token = request.getHeader(Constant.SECURITY_TOKEN_HEADER_NAME);
                userSecureTokenEntity = authenticationService.getUserByToken(token);
                if (rolesEnum.equals(userSecureTokenEntity.getUserRole())) {
                    LOGGER.info("User with role {} have been successfully validated using token {}",
                            userSecureTokenEntity.getUserRole(),
                            token);
                    return super.preHandle(request, response, handler);
                }
            }
            throw new MovieLandSecurityException("Current role " +
                    userSecureTokenEntity.getUserRole() +
                    " is prohibited to do this operation");
        }
        return super.preHandle(request, response, handler);
    }
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        MDC.remove("requestId");
        super.postHandle(request, response, handler, modelAndView);
    }

}
