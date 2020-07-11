package org.chimerax.hades.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.chimerax.common.security.jwt.GrantedAuthorityImpl;
import org.chimerax.common.security.jwt.UserDetailsImpl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 10-Jun-20
 * Time: 8:31 PM
 */

@Service
@AllArgsConstructor
public class AuthorizationService {

    private static final String URL = "http://prometheus";
    private RestTemplate restTemplate;

    public boolean validate(final String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(token);
        HttpEntity<Void> httpEntity = new HttpEntity<>(null, httpHeaders);
        return restTemplate
                .exchange(URL + "/authenticate/currentUser", HttpMethod.GET, httpEntity, Void.class)
                .getStatusCode() == HttpStatus.OK;
    }

    public UserDetails extractJWTUser(String token) {
        return this.extractUser(this.extractJWTClaims(token));
    }

    private UserDetails extractUser(final Claims claims) {
        String username = claims.getSubject();
        List<GrantedAuthority> authorities = this.extractAuthorities(claims);
        return UserDetailsImpl.builder().username(username).authorities(authorities).build();
    }

    private List<GrantedAuthority> extractAuthorities(final Claims claims) {
        List<String> authorities = claims.get("auth", List.class);
        if (authorities == null) {
            return new ArrayList<>();
        } else {
            return authorities.stream().map(GrantedAuthorityImpl::new).collect(Collectors.toList());
        }
    }

    private Claims extractJWTClaims(final String token) {
        return Jwts.parser().parseClaimsJwt(this.transformJWSToJWT(token)).getBody();
    }

    private String transformJWSToJWT(final String token) {
        return token.substring(0, token.lastIndexOf(".") + 1);
    }

}
