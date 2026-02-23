package fabrice.sabackend.security;


import fabrice.sabackend.security.entities.UserDetailServiceimpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig {

    private UserDetailServiceimpl userDetailsServiceImpl;

   // @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder passwordEncoder) {
        return  new InMemoryUserDetailsManager(
                User.withUsername("Fabrice").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build(),
                User.withUsername("Cedric").password(passwordEncoder.encode("1234")).roles("USER").build()
        );

    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(e->e.loginPage("/login").defaultSuccessUrl("/").permitAll());
        http.authorizeHttpRequests(er->er.requestMatchers("/webjars/**").permitAll());
        //http.authorizeHttpRequests(ar->ar.requestMatchers("/user/**").hasRole("USER"));
        //http.authorizeHttpRequests(ar->ar.requestMatchers("/admin/**").hasRole("ADMIN"));
        http.authorizeHttpRequests(ar->ar.anyRequest().authenticated());
        http.exceptionHandling(ex->ex.accessDeniedPage("/notAuthorized"));
        http.userDetailsService(userDetailsServiceImpl);
        return http.build();
    }
}
