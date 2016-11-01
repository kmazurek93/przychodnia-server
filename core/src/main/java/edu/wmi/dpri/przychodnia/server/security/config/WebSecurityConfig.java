package edu.wmi.dpri.przychodnia.server.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.wmi.dpri.przychodnia.server.security.RestAuthenticationEntryPoint;
import edu.wmi.dpri.przychodnia.server.security.SkipPathRequestMatcher;
import edu.wmi.dpri.przychodnia.server.security.ajax.AjaxAuthenticationProvider;
import edu.wmi.dpri.przychodnia.server.security.ajax.AjaxLoginProcessingFilter;
import edu.wmi.dpri.przychodnia.server.security.jwt.JwtAuthenticationProcessingFilter;
import edu.wmi.dpri.przychodnia.server.security.jwt.JwtAuthenticationProvider;
import edu.wmi.dpri.przychodnia.server.security.jwt.JwtTokenExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lupus on 22.10.16.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
@EnableWebSecurity
@Profile("secure")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String JWT_TOKEN_HEADER_PARAM = "Authorization";
    private static final String LOGIN_ENDPOINT = "/security/login";
    private static final String TOKEN_BASED_AUTH_ENDPOINTS = "/**";
    private static final String TOKEN_REFRESH_ENDPOINT = "/security/auth/token";
    private static final String REGISTRATION_ENDPOINT = "/public/**";

    @Inject
    private RestAuthenticationEntryPoint authenticationEntryPoint;
    @Inject
    private AuthenticationSuccessHandler successHandler;
    @Inject
    private AuthenticationFailureHandler failureHandler;
    @Inject
    private AjaxAuthenticationProvider ajaxAuthenticationProvider;
    @Inject
    private JwtAuthenticationProvider jwtAuthenticationProvider;
    @Inject
    private JwtTokenExtractor jwtTokenExtractor;
    @Inject
    private AuthenticationManager authenticationManager;
    @Inject
    private ObjectMapper objectMapper;

    @Bean
    protected AjaxLoginProcessingFilter buildAjaxLoginProcessingFilter() throws Exception {
        AjaxLoginProcessingFilter filter = new AjaxLoginProcessingFilter(LOGIN_ENDPOINT, successHandler, failureHandler, objectMapper);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    @Bean
    protected JwtAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter() throws Exception {
        List<String> pathsToSkip = Arrays.asList(TOKEN_REFRESH_ENDPOINT, LOGIN_ENDPOINT, REGISTRATION_ENDPOINT);
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, TOKEN_BASED_AUTH_ENDPOINTS);
        JwtAuthenticationProcessingFilter filter
                = new JwtAuthenticationProcessingFilter(failureHandler, jwtTokenExtractor, matcher);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(ajaxAuthenticationProvider);
        auth.authenticationProvider(jwtAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .exceptionHandling()
                .authenticationEntryPoint(this.authenticationEntryPoint)

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_ENDPOINT).permitAll()
                .antMatchers(TOKEN_REFRESH_ENDPOINT).permitAll()
                .antMatchers(REGISTRATION_ENDPOINT).permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE, TOKEN_BASED_AUTH_ENDPOINTS).authenticated()
                .antMatchers(HttpMethod.POST, TOKEN_BASED_AUTH_ENDPOINTS).authenticated()
                .antMatchers(HttpMethod.PUT, TOKEN_BASED_AUTH_ENDPOINTS).authenticated()
                .antMatchers(HttpMethod.GET, TOKEN_BASED_AUTH_ENDPOINTS).authenticated()
                .and()
                .addFilterBefore(buildAjaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
//    }
}
