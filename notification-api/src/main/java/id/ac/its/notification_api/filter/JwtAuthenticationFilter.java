package id.ac.its.notification_api.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.PublicKey;
import java.util.Collections;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    private PublicKey publicKey;

    public JwtAuthenticationFilter(AuthenticationManager manager, InputStream publicKeyStream) throws IOException {
        super(manager);
        this.publicKey = loadPublicKey(publicKeyStream);
    }

    private PublicKey loadPublicKey(InputStream stream) throws IOException {
        var reader = new InputStreamReader(stream);
        var parser = new PEMParser(reader);
        var info = (SubjectPublicKeyInfo) parser.readObject();
        var converter = new JcaPEMKeyConverter();
        return converter.getPublicKey(info);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        var context = SecurityContextHolder.getContext();
        var authentication = authenticate(request);
        context.setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private Authentication authenticate(HttpServletRequest request) {
        var token = request.getHeader(HttpHeaders.AUTHORIZATION);
        var claims = parse(token);
        var id = claims.get("userid", Integer.class);
        var role = claims.get("role", String.class);
        var authorities = Collections.singleton(new SimpleGrantedAuthority(role));
        return new UsernamePasswordAuthenticationToken(id, token, authorities);
    }

    private Claims parse(String token) {
        return Jwts.parser()
                .setSigningKey(publicKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
