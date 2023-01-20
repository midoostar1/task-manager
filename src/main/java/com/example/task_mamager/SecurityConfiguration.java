package com.example.task_mamager;

import com.example.task_mamager.services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    private final UserDetailsLoader usersLoader;

    public SecurityConfiguration(UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http



        /* Login configuration */
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/profile") // user's home page, it can be any URL
                .permitAll() // Anyone can go to the login.html page

                /* Logout configuration */
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout") // append a query string value

                /* Pages that can be viewed without having to log in */
                .and()
                .authorizeRequests()
                .requestMatchers("/", "/signup","/js/**", "/css/**","/crypto") // anyone can see the home and the ads pages
                .permitAll()



                /* Pages that require authentication */
                .and()
                .authorizeRequests()
                .requestMatchers(
                        "/following/user",
                        "/searchPost",
                        "/admin/**",
                        "/super-admin/**",
                        "/addAsset",
                        "/userFinance",
                        "/posts", //only authenticated users can create ads
                        "/userProfile",
                        "/finance/**"// only authenticated users can edit ads
                )
                .authenticated()


                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()




//                ************************************************************







//                *****************************************************************************


                .and()
                .authorizeRequests()
                .requestMatchers("/super-admin/**")
                .hasAnyAuthority("super-admin")

                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied")
        ;


        return http.build();
    }


}