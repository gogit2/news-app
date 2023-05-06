package co.ahmoh.newsapp.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final NewsUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public WebSecurityConfig(NewsUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/admin/**","/news/new", "/news/save").hasRole("ADMIN") // Require authentication for accessing the admin view
            .antMatchers("/news/all").permitAll() // Allow anonymous access for the anonymous user view
            .anyRequest().authenticated() // Require authentication for any other requests
            .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/news/new") // Redirect to the admin news page after successful login
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/logout")
            .permitAll();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }


}

