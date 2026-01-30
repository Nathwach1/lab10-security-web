package com.example.lab10.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;

@Configuration
public class SecurityConfig {

    private final CustomAuthenticationSuccessHandler successHandler;

    public SecurityConfig(CustomAuthenticationSuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // 🔐 AUTHORIZATION
                .authorizeHttpRequests(auth -> auth

                        // 🌍 PUBLIC
                        .requestMatchers("/", "/css/**", "/js/**", "/images/**").permitAll()

                        // 🚫 SEULEMENT SI NON CONNECTÉ
                        .requestMatchers("/login", "/register").anonymous()

                        // 👑 ADMIN
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // 👤 USER (ADMIN INTERDIT)
                        .requestMatchers("/notes/**").hasRole("USER")

                        .anyRequest().authenticated()
                )

                // 🔑 LOGIN
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler(successHandler)
                        .permitAll()
                )

                // 🚪 LOGOUT SÉCURISÉ
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )

                // 🛡️ SECURITY HEADERS (LAB 13)
                .headers(headers -> headers
                        .contentTypeOptions(Customizer.withDefaults())
                        .frameOptions(frame -> frame.deny())
                        .referrerPolicy(ref -> ref
                                .policy(ReferrerPolicyHeaderWriter.ReferrerPolicy.NO_REFERRER))
                        .contentSecurityPolicy(csp -> csp
                                .policyDirectives(
                                        "default-src 'self'; " +
                                                "style-src 'self'; " +
                                                "script-src 'self'; " +
                                                "img-src 'self';"
                                )
                        )
                )

                // 🛡️ CSRF (ON GARDE ACTIVÉ)
                .csrf(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
