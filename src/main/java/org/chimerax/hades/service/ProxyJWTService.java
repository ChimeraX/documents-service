package org.chimerax.hades.service;

import org.chimerax.common.security.jwt.JWTService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 14-Jun-20
 * Time: 9:20 AM
 */
@Service
public class ProxyJWTService implements JWTService {
    private RestTemplate restTemplate;

    @Override
    public UserDetails extractJWSUser(String token) {
        return null;
    }

    @Override
    public UserDetails extractJWTUser(String token) {
        return null;
    }

    @Override
    public String generateToken(UserDetails userDetails,
                                Map<String, Object> headers,
                                Map<String, Object> extra) {
        return "null";
    }
}
