package org.example.couponservice.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin(Customizer.withDefaults());
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers(HttpMethod.GET, "/coupon/{code:^[A-Z]*$}", "/", "/showGetCoupon")
                    .hasAnyRole("User", "Admin").requestMatchers(HttpMethod.GET, "/showCreateCoupon", "createCoupon", "createResponse").hasRole("Admin")
                    .requestMatchers(HttpMethod.POST, "/coupon", "/saveCoupon").hasRole("Admin").requestMatchers(HttpMethod.POST, "/getCoupon").hasRole("Admin");
        });
        return http.build();
    }
}
