package edu.brandeis.gps.rseg127.lms.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//Keeping in memory code just in case it is needed for future tests

//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        auth.inMemoryAuthentication()
//            .withUser("admin")
//                .password(encoder.encode("password"))
//                .roles("ADMIN")
//            .and()
//            .withUser("librarian")
//                .password(encoder.encode("password"))
//                .roles("LIBRARIAN")
//            .and()
//            .withUser("patron")
//                .password(encoder.encode("password"))
//                .roles("PATRON");
//    }

//Configuration for authenticating users from a database
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.jdbcAuthentication()
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password_hash as password, true as enabled from user where username=?")
                .authoritiesByUsernameQuery("select username, user_type as role from user where username=?")
        ;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
            
                .antMatchers("/lms.css", "/lms.js", "/login", "/error")
                .permitAll()

                // patrons need get access to view assets, bookbag
                .antMatchers(HttpMethod.GET, "/api/assets/**", "/api/cart", "/api/authors/**")
                .hasAnyAuthority("PAT", "LIB", "ADM")

                // patrons need post to add to bookbag
                .antMatchers(HttpMethod.POST, "/api/cart")
                .hasAnyAuthority("PAT", "LIB", "ADM")

                // patrons need delete to remove from bookbag
                .antMatchers(HttpMethod.DELETE, "/api/cart/*")
                .hasAnyAuthority("PAT", "LIB", "ADM")

                // patrons need put to checkout a copy
                .antMatchers(HttpMethod.PUT, "/api/assets/copy/*")
                .hasAnyAuthority("PAT", "LIB", "ADM")

                // librarians and admins can do whatever they want with the rest of the api
                .antMatchers("/api/**", "/assets", "/users", "/audit-log", "/loans")
                .hasAnyAuthority("LIB", "ADM")

                .antMatchers("/","/browser","/bookbag", "/borrowed")
                .hasAnyAuthority("PAT", "LIB", "ADM")
            .and()
                .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?error")
                    .defaultSuccessUrl("/")
            .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                    .deleteCookies("JSESSIONID")
            .and()
                .httpBasic();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
