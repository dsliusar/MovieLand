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


/**
 * Interceptor for authorizing the user and logging every request
 */
public class AuthorizationSecurityRoleHandlerInterceptor extends HandlerInterceptorAdapter {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * Method works before controller and authorizing the user
     * If user role is prohibited to do the operation throws MovielandSecurity exception
     * Logging every request and generating the UUID using MDC
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
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

    /**
     * Delete the request after the controller got executed
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        MDC.remove("requestId");
        super.postHandle(request, response, handler, modelAndView);
    }

}
