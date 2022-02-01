package fr.diginamic.gestionconges.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import fr.diginamic.gestionconges.entities.Collaborateur;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Configuration
public class JWTAuthorizationFilter  extends OncePerRequestFilter {

    @Value("${jwt.cookie}")
    private String TOKEN_COOKIE;

    @Value("${jwt.secret}")
    private String SECRET;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        // Recherche du jeton par Cookie
    	if (JWTAuthenticationSuccessHandler.authenticationMem != null) {
            SecurityContextHolder.getContext().setAuthentication(JWTAuthenticationSuccessHandler.authenticationMem);
        }
    	

        chain.doFilter(req, res);

    }

}