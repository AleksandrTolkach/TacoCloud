package by.toukach.authserver.authorization;

import by.toukach.authserver.data.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
      throws Exception {
    return http
        .cors().disable()
        .csrf().disable()
        .authorizeHttpRequests(authorizeRequests ->
            authorizeRequests
                .antMatchers("/oauth2/authorize**").permitAll()
                .anyRequest().authenticated()
        )
        .formLogin()
        .and().build();
  }

  @Bean
  public UserDetailsService userDetailsService(UserRepository repository) {
    return repository::findByUsername;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
