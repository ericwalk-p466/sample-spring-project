package p466.tacocloud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.
        UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.
        HttpSecurity;

import p466.tacocloud.User;
import p466.tacocloud.data.UserRepository;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            User user = userRepo.findByUsername(username);
            if (user != null) {
                return user;
            }
            throw new UsernameNotFoundException(
                    "User '" + username + "' not found");
        };
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/design", "/orders")
                        .hasRole("USER")
                        .requestMatchers("/", "/**").permitAll()
                ).formLogin(Customizer.withDefaults()).build();
        // Note, I could not figure out how to get the login.html page to be used over the default login page.

        // This section from the assignment handout did not work
        // I'm hoping I was able to figure it out with my IntelliJ's lsp
//        return http
//                .authorizeRequests()
//                .antMatchers("/design","/orders").hasRole("USER")
//                .antMatchers("/","/**").permitAll()
//                .and()
//                .build();

    }
}

