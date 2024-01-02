package com.kerupuksda.kerupuksdawebapi.controllers;

import com.kerupuksda.kerupuksdawebapi.configuration.security.jwt.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;


@Slf4j
public class BaseController {

    @Autowired
    private JwtUtils jwtUtils;

    protected String getUsername(HttpServletRequest request){
        String usernameFromToken = "";
        try {
            String jwtToken = parseJwt(request);
            usernameFromToken = jwtUtils.getUserNameFromJwtToken(jwtToken);
        } catch (Exception e) {
            log.error("Error parsing username from token {}", e);
        }
        return ObjectUtils.isEmpty(usernameFromToken) ? "" : usernameFromToken;
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());

        }
        return null;
    }

}
