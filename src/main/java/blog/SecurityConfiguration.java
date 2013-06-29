package blog;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeUrls()
                .antMatchers("/mvc/blog").permitAll()
                .antMatchers("/mvc/blog/**").hasRole("ADMIN")
            .and()
                .formLogin()
                    .loginUrl("/mvc/auth/login")
                    .defaultSuccessUrl("/mvc/blog/posts")
                    .failureUrl("/mvc/auth/login")
                    .usernameParameter("user")
                    .passwordParameter("pwd")
                    .permitAll()
            .and()
                .logout()
                    .logoutUrl("/mvc/auth/logout")
                    .logoutSuccessUrl("/mvc/blog")
                    .permitAll();
        http.authorizeUrls()
                .antMatchers("/mvc/rest/**")
                .permitAll();
    }
    
    @Override
    protected void registerAuthentication(AuthenticationManagerBuilder registry) throws Exception {
        registry
            .inMemoryAuthentication()
                .withUser("admin")
                    .password("admin")
                    .roles("ADMIN");
    }
    
    @Override
    public void configure(WebSecurity builder) throws Exception {
        builder
            .ignoring()
                .antMatchers("/mvc/static/**");
    }

}