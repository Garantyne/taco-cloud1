package tacos1.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import tacos1.Repository.UserRepository;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //этот метод смотрит что бы пользователь который хочет авторизоваться был в базе данных
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            tacos1.entity.User user = userRepo.findByUsername(username);
            if (user != null) {
                return user;
            }
            throw new UsernameNotFoundException("User ‘" + username + "’ not found");
        };
    }


        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{
            http
                    .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                            .requestMatchers("/design", "/orders/**").hasRole("USER") // Доступ только для зарегистрированных пользователей
                            .requestMatchers("/", "/login", "/register").permitAll() // Доступ для всех пользователей
                            .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
                    )
                    .formLogin(form -> form
                            .loginPage("/login")
                            .defaultSuccessUrl("/design", true) // Перенаправление после успешного логина
                    ); // Разрешаем выход всем
            return http.build();
        }
/*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                .antMatchers("/design", "/orders").access("hasRole(‘USER’)")
                .antMatchers("/", "/**").access("permitAll()")
                .and()
                .build();
    }*/
}
