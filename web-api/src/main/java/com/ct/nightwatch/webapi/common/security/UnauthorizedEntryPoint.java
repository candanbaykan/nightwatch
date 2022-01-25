package com.ct.nightwatch.webapi.common.security;

import com.ct.nightwatch.webapi.restcontroller.api.v1.exception.model.ErrorCode;
import com.ct.nightwatch.webapi.restcontroller.api.v1.exception.model.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint, Serializable {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ErrorResponse<String> errorResponse = new ErrorResponse<>(ErrorCode.UNAUTHORIZED, "Unauthorized", request.getRequestURI());
        response.getOutputStream().println(objectMapper.writeValueAsString(errorResponse));
    }

}
