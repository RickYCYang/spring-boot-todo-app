package net.javaguides.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {
    // private static final String ADMIN = "ADMIN";
    // private static final String USER = "USER";
    @SuppressWarnings("unused")
    private static UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
            throws Exception {
        return configuration.getAuthenticationManager();
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf((csrf) -> csrf.disable()).authorizeHttpRequests(authroize -> {
            /* role based security control */
            // authroize.requestMatchers(HttpMethod.POST, "/api/**").hasRole(ADMIN);
            // authroize.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole(ADMIN);
            // authroize.requestMatchers(HttpMethod.PUT, "/api/**").hasRole(ADMIN);
            // authroize.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole(USER, ADMIN);
            // authroize.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole(USER, ADMIN);
            // authroize.requestMatchers(HttpMethod.GET, "/api/**").permitAll();
            authroize.anyRequest().authenticated();
        }).httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }

    // @Bean
    // public UserDetailsService userDetailsService() {
    // UserDetails admin = User.builder().username("admin")
    // .password(passwordEncoder().encode("foobared")).roles(ADMIN).build();
    // UserDetails rick = User.builder().username("rick")
    // .password(passwordEncoder().encode("foobared")).roles(USER).build();

    // return new InMemoryUserDetailsManager(admin, rick);
    // }
}
