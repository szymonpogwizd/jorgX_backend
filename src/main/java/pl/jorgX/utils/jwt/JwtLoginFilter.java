package pl.jorgX.utils.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.Collections;

public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    private final JwtUtils jwtUtils;

    public JwtLoginFilter(String url, AuthenticationManager authManager, JwtUtils jwtUtils) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        try {
            JwtRequest creds = new ObjectMapper().readValue(request.getInputStream(), JwtRequest.class);
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(), Collections.emptyList())
            );
        } catch (AuthenticationException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Niepoprawne dane logowania");
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
        long expirationTime = 3_600_000; // 1 godzina

        String userRole = auth.getAuthorities().stream().findFirst().get().getAuthority();

        String token = Jwts.builder()
                .setSubject(auth.getName())
                .claim("role", userRole)
//                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(jwtUtils.getSecretKey())
                .compact();
        res.addHeader("Access-Control-Expose-Headers", "Authorization");
        res.addHeader("Authorization", "Bearer " + token);
    }
}
