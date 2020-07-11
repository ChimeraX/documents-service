package org.chimerax.hades.service;

import lombok.AllArgsConstructor;
import lombok.val;
import org.chimerax.common.security.jwt.JWTService;
import org.chimerax.common.security.jwt.JWTServiceHelper;
import org.chimerax.common.security.jwt.JWTToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 10-Jun-20
 * Time: 8:32 PM
 */
@Component
@AllArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {


    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final int TOKEN_STARTING_POSITION = "Bearer ".length();
    private AuthorizationService authorizationService;

    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(AUTHORIZATION_HEADER);
        if (header != null) {
            String token = header.substring(TOKEN_STARTING_POSITION);
            boolean valid = authorizationService.validate(token);
            if (valid) {
                val userDetails = authorizationService.extractJWTUser(token);
                JWTToken.Details details = new JWTToken.Details(request);
                SecurityContextHolder.getContext().setAuthentication(new JWTToken(userDetails, details));
            }
        }

        filterChain.doFilter(request, response);
    }
}
