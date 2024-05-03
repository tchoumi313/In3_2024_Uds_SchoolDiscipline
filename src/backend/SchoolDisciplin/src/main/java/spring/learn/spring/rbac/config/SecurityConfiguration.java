package spring.learn.spring.rbac.config;

import spring.learn.spring.rbac.services.SpringUserService;
import spring.learn.spring.repository.RolePermissionRepository;
import spring.learn.spring.repository.UserRepository;
import spring.learn.spring.repository.UserRoleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private SpringUserService springUserService;
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private RolePermissionRepository rolePermissionRepository;

    public SecurityConfiguration(SpringUserService springUserService,
            UserRepository userRepository, UserRoleRepository userRoleRepository,
            RolePermissionRepository rolePermissionRepository) {
        this.springUserService = springUserService;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.rolePermissionRepository = rolePermissionRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .cors()
                .and().addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.userRepository,
                        this.userRoleRepository, this.rolePermissionRepository))
                .authorizeRequests()

                // configure access rules
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/register").permitAll()
                .antMatchers(HttpMethod.POST, "/charge/chargeList").permitAll()
                .antMatchers(HttpMethod.POST, "/users/updateUserStatus").permitAll()
                .antMatchers(HttpMethod.POST, "/roles/getAllRole").access("hasRole('SUPER_ADMIN')")
                .antMatchers(HttpMethod.POST, "/roles/getActiveRole").access("hasRole('SUPER_ADMIN')")
                .antMatchers(HttpMethod.POST, "/roles/createRole").access("hasRole('SUPER_ADMIN')")
                .antMatchers(HttpMethod.POST, "/roles/updateRole").access("hasRole('SUPER_ADMIN')")
                .antMatchers(HttpMethod.PUT, "/projet/create").access("hasRole('ADMIN')")
                .antMatchers(HttpMethod.POST, "/roles/deleteRole/**").access("hasRole('SUPER_ADMIN')")
                .antMatchers(HttpMethod.POST, "/roles/findRoleById/**").access("hasRole('SUPER_ADMIN')")
                .antMatchers(HttpMethod.POST, "/users-roles/getAllUsersRole/").access("hasRole('SUPER_ADMIN')")
                .antMatchers(HttpMethod.POST, "/users-roles/createUserRole/").access("hasRole('SUPER_ADMIN')")
                .antMatchers(HttpMethod.POST, "/users-roles/deleteUserRole/**").access("hasRole('SUPER_ADMIN')")
                .antMatchers(HttpMethod.POST, "/users-roles/assignRoleToUser/**").access("hasRole('SUPER_ADMIN')")
                .antMatchers(HttpMethod.POST, "/users-roles/removeRoleToUser/**").access("hasRole('SUPER_ADMIN')")
                .antMatchers(HttpMethod.POST, "/users-roles/getAllUserRole/**").access("hasRole('SUPER_ADMIN')")

                // Swagger UI route protection
                .antMatchers(HttpMethod.GET,
                        "/v2/api-docs",
                        "/v3/api-docs/**",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**",
                        "/swagger-ui.html")
                .permitAll()
                .antMatchers("/", "/list", "/charge/**", "/estimation/**", "/bigimport/**", "/projet/**",
                        "/bucket-settings/**")
                .access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
                .antMatchers("/newuser/**", "/delete-user-*", "/chart/pieUser-dashboard").access("hasRole('ADMIN')")
                .antMatchers("/chart/pieUserYear", "/chart/pieChargeYear", "/chart/pieCharge", "/chart/pieUser")
                .access("hasRole('ADMIN') or hasRole('USER')")
                .antMatchers("/edit-user-*")
                .access("hasRole('ADMIN') or hasRole('DBA')").and().authorizeRequests()
                .anyRequest().authenticated();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.springUserService);
        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}