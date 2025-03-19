package org.example.listingservice.configurations;


import lombok.RequiredArgsConstructor;
import org.example.listingservice.filters.JwtFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
@EnableWebMvc
@ComponentScan
public class WebSecurityConfig2 {
    private final JwtFilter jwtFilter;
    @Value("${api.prefix}")
    private String apiPrefix;



//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
//                .authorizeHttpRequests(requests -> {
//                    requests.requestMatchers(
//                                    String.format("%s/users/register", apiPrefix),
//                                    String.format("%s/users/login", apiPrefix),
//                                    String.format("%s/users/refreshToken",apiPrefix))
//                            .permitAll()
//                            .requestMatchers(POST,
//                                    String.format("%s/users/avatar",apiPrefix)).permitAll()
//                            .anyRequest()
//                            .authenticated();
//                }).csrf(AbstractHttpConfigurer::disable);
//        return http.build();
//    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(POST, "/graphql").hasRole("ADMIN")
                        .requestMatchers("/ws/**").permitAll()
                        .requestMatchers("/swagger-ui/**").authenticated()
                        .requestMatchers("/swagger-ui.html").authenticated()
                        .requestMatchers(GET, String.format("%s/buildings/admin/search**", apiPrefix)).hasRole("ADMIN")
                        .requestMatchers(GET, String.format("%s/users/search**", apiPrefix)).hasRole("ADMIN")
                        .requestMatchers("/**").permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }


}
