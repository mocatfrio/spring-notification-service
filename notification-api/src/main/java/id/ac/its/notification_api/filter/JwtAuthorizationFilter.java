package id.ac.its.notification_api.filter;

import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private final String secret;
    private final UserDetailsService service;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, String secret, UserDetailsService service) {
        super(authenticationManager);
        this.secret = secret;
        this.service = service;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        var authenticationToken = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private Authentication getAuthentication(HttpServletRequest request) {
        var token = request.getHeader(HttpHeaders.AUTHORIZATION);
        var parsedToken = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token);
        var username = parsedToken.getBody().getSubject();
        var user = service.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(username, user.getPassword(), user.getAuthorities());
    }
}
