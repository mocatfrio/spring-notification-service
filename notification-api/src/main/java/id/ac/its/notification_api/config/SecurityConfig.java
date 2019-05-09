package id.ac.its.notification_api.config;

import id.ac.its.notification_api.filter.JwtAuthenticationFilter;
import id.ac.its.notification_api.filter.JwtAuthorizationFilter;
import id.ac.its.notification_api.service.UserDetailsServiceImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ConfigurationProperties(prefix = "application.security")
@Setter
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder encoder;
    private final UserDetailsServiceImpl userDetailsService;
    @Getter
    private String secret;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.encoder = new BCryptPasswordEncoder(10);
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        var authenticationManager = authenticationManager();
        var authenticationFilter = new JwtAuthenticationFilter(authenticationManager, secret);
        var authorizationFilter = new JwtAuthorizationFilter(authenticationManager, secret);
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .addFilter(authenticationFilter)
                .addFilter(authorizationFilter)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder);
    }
}
