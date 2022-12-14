package main008.BED.oauth2_jwt.config;

import main008.BED.oauth2_jwt.filter.JwtVerificationFilter;
import main008.BED.oauth2_jwt.handler.OAuth2UserSuccessHandler;
import main008.BED.oauth2_jwt.handler.UserAccessDeniedHandler;
import main008.BED.oauth2_jwt.handler.UserAuthenticationEntryPoint;
import main008.BED.oauth2_jwt.jwt.JwtTokenizer;
import main008.BED.oauth2_jwt.utils.CustomAuthorityUtils;
import main008.BED.users.service.UsersService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    private final UsersService usersService;

    public SecurityConfiguration(JwtTokenizer jwtTokenizer,
                                 CustomAuthorityUtils authorityUtils,
                                 UsersService usersService) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
        this.usersService = usersService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                .headers().frameOptions().sameOrigin()
//                .and()
                .csrf().disable()
                .cors()
                .and()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // ????????? ???????????? ????????? ??????
                .and()

                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling()  // ??????
                .authenticationEntryPoint(new UserAuthenticationEntryPoint())  // ??????
                .accessDeniedHandler(new UserAccessDeniedHandler())            // ??????

                .and()
                .apply(new CustomFilterConfigurer())  // ??????

                .and()
                .authorizeHttpRequests(authorize -> authorize // url authorization ?????? ??????
                        .antMatchers(HttpMethod.POST, "/auth/**").hasRole("USER")
                        .antMatchers(HttpMethod.PATCH, "/auth/**").hasRole("USER")
                        .antMatchers(HttpMethod.DELETE, "/auth/**").hasRole("USER")
                        .antMatchers(HttpMethod.GET, "/auth/**").hasRole("USER")
                        .anyRequest().permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(new OAuth2UserSuccessHandler(jwtTokenizer, authorityUtils, usersService))  // (1)
                );

        return http.build();
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PATCH", "DELETE"));
//
//        configuration.setAllowCredentials(true);
//        configuration.setAllowedHeaders(Arrays.asList("*"));
//        configuration.setExposedHeaders(Arrays.asList("Authorization"));
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }


    // ??????
    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils); // JwtVerificationFilter??? ??????????????? ??????????????? JwtVerificationFilter?????? ???????????? ???????????? ???????????? DI ?????????.

            builder.addFilterAfter(jwtVerificationFilter, OAuth2LoginAuthenticationFilter.class); // JwtVerificationFilter??? OAuth2LoginAuthenticationFilter??? ????????? ?????? ????????? ??????????????? JwtAuthenticationFilter ?????? ??????
        }
    }
}