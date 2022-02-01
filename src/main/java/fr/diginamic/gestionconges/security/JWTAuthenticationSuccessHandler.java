package fr.diginamic.gestionconges.security;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.diginamic.gestionconges.entities.Collaborateur;
import fr.diginamic.gestionconges.repositories.CollaborateurRepository;
import io.jsonwebtoken.Jwts;

@Configuration
public class JWTAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Value("${jwt.expires_in}")
    private Integer EXPIRES_IN;

    @Value("${jwt.cookie}")
    private String TOKEN_COOKIE;

    @Value("${jwt.secret}")
    private String SECRET;

    @Autowired
    private CollaborateurRepository collaborateurRepository;

    @Autowired
    private ObjectMapper mapper;


    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        User user = (User) authentication.getPrincipal();

        String rolesList = user.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.joining(","));

        Collaborateur collab = collaborateurRepository.findByIdentifiant(user.getUsername()).orElseThrow(()-> new IllegalArgumentException("L'email ne correspond à aucun collaborateur"));

        response.setContentType("application/json");
        response.getWriter().write(mapper.writeValueAsString(new UserDto(collab)));

        Map<String, Object> infosSupplementaireToken = new HashMap<>();
        infosSupplementaireToken.put("roles", rolesList);

        String jws = Jwts.builder()
                .setSubject(user.getUsername())
                .addClaims(infosSupplementaireToken)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRES_IN * 1000))
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, SECRET)
                .compact();

        Cookie authCookie = new Cookie(TOKEN_COOKIE, (jws));
        authCookie.setHttpOnly(true);
        authCookie.setMaxAge(EXPIRES_IN * 1000);
        authCookie.setPath("/");
        response.addCookie(authCookie);
    }
}