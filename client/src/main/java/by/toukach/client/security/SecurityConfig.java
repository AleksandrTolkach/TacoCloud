package by.toukach.client.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

  private static final String[] WHITE_LIST_URLS = {
      "/hello",
      "/register",
      "/verifyRegistration*",
      "/resendVerifyToken*"
  };

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .cors()
        .and()
        .csrf()
        .disable()
        .authorizeHttpRequests()
        .antMatchers(WHITE_LIST_URLS).permitAll()
        .antMatchers("/api/**").authenticated()
        .and()
        .oauth2Login(oauth2login ->
            oauth2login.loginPage("/oauth2/authorization/taco-admin-client"))
        .oauth2Client(Customizer.withDefaults());

    return http.build();
  }
}
