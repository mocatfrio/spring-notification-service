package id.ac.its.notification_api.config;

import id.ac.its.notification_api.filter.JwtAuthenticationFilter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@ConfigurationProperties(prefix = "application.security")
@Data
@EqualsAndHashCode(callSuper = false)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private Resource publicKey;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        var manager = authenticationManager();
        var filter = new JwtAuthenticationFilter(manager, publicKey.getInputStream());
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(filter)
                .authorizeRequests()
                .anyRequest().hasAuthority("Admin");
    }
}
