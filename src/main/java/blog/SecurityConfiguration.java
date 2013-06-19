package blog;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.EnableWebSecurity;
import org.springframework.security.config.annotation.web.HttpConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpConfiguration http) throws Exception {
        http
            .authorizeUrls()
                .antMatchers("/mvc/blog").permitAll()
                .antMatchers("/**").hasRole("ADMIN")
                .and()
            .formLogin()
                .loginUrl("/mvc/auth/login")
                .defaultSuccessUrl("/mvc/blog/posts")
                .failureUrl("/mvc/auth/login")
                .usernameParameter("user")
                .passwordParameter("pwd")
                .permitAll();
        http
            .logout()
                .logoutUrl("/mvc/auth/logout")
                .logoutSuccessUrl("/mvc/blog")
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
    public void configure(WebSecurityBuilder builder) throws Exception {
        builder
            .ignoring()
                .antMatchers("/mvc/static/**");
    }

}