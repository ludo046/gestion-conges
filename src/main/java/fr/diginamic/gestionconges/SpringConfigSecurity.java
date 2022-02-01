package fr.diginamic.gestionconges;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import fr.diginamic.gestionconges.providers.AppAuthProvider;
import fr.diginamic.gestionconges.services.UserService;

@Configuration
@EnableWebSecurity
public class SpringConfigSecurity extends WebSecurityConfigurerAdapter {

    @Autowired  
    UserService userDetailsService;
    
    @Bean
    public AuthenticationProvider getProvider() {
        AppAuthProvider provider = new AppAuthProvider();
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.cors().and().authorizeRequests()
		.antMatchers("/login", "http://localhost:4200/login")
		.permitAll().anyRequest().authenticated().and().formLogin()
		.defaultSuccessUrl("http://localhost:4200/home", true).failureForwardUrl("http://localhost:4200/error");
        
    }

}