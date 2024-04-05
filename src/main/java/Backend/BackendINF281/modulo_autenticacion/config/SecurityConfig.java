package Backend.BackendINF281.modulo_autenticacion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import Backend.BackendINF281.modulo_autenticacion.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtauthenticationfilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityfilterchain(HttpSecurity http) throws  Exception
    {
        return http
                .csrf(csrf -> 
                    csrf
                    .disable())
                .authorizeHttpRequests( authRequest -> 
                    authRequest
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/nuestraOrg/{id}").permitAll()
                        .anyRequest().authenticated()
                )
                            .sessionManagement(sessionManager -> 
                sessionManager
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtauthenticationfilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
