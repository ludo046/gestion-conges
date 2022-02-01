package fr.diginamic.gestionconges.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public static Authentication authenticationMem;


    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        User user = (User) authentication.getPrincipal();

        Collaborateur collab = collaborateurRepository.findByIdentifiant(user.getUsername()).orElseThrow(()-> new IllegalArgumentException("L'email ne correspond à aucun collaborateur"));

        authenticationMem =  new UsernamePasswordAuthenticationToken(collab.getIdentifiant(), null, Arrays.asList(new Role(collab.getRole())));
        SecurityContextHolder.getContext().setAuthentication(authenticationMem);

    }
}