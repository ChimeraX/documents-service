package org.chimerax.hades.configuration;

import lombok.AllArgsConstructor;
import org.chimerax.common.security.jwt.JWTFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 24-Apr-20
 * Time: 9:09 PM
 */

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebConfiguration extends WebSecurityConfigurerAdapter {

    private JWTFilter jwtFilter;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                // .requiresChannel()
                // .anyRequest().requiresSecure()
                // .and()
                .csrf().disable()
                .cors().and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
