package tacos.security;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import tacos.User;
import tacos.data.UserRepository;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

  @Bean
  public UserDetailsService userDetailsService(UserRepository userRepository) {
    return (username -> {
      User user = userRepository.findByUsername(username);
      if (user == null) {
        throw new EntityNotFoundException(String.format("User with login %s doesn't exist", username));
      } else {
        return user;
      }
    });
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
        .csrf().disable()
        .authorizeHttpRequests(authorizeHttpRequest ->
            authorizeHttpRequest.requestMatchers("/design", "/orders").hasRole("USER")
                .requestMatchers(HttpMethod.POST, "/api/ingredients")
                .hasAuthority("SCOPE_writeIngredients")
                .requestMatchers(HttpMethod.DELETE, "/api/ingredients/{id}")
                .hasAuthority("SCOPE_deleteIngredients")
                .requestMatchers("/", "/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/orders")
                .hasAuthority("SCOPE_readOrders"))
        .formLogin(formLogin -> formLogin.loginPage("/login")
            .loginProcessingUrl("/auth")
            .usernameParameter("usr")
            .passwordParameter("pwd")
            .defaultSuccessUrl("/design", true))
        .logout((httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
            .logoutSuccessUrl("/")))
        .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
        .build();
  }
}
