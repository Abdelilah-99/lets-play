package lets.play.demo.Config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lets.play.demo.Repository.RegisterRepo;

@Component
public class JwtConf extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final UserDetailServices userDetailServices;
    private final RegisterRepo registerRepo;

    public JwtConf(JwtUtils jwtUtils, UserDetailServices userDetailServices, RegisterRepo registerRepo) {
        this.jwtUtils = jwtUtils;
        this.userDetailServices = userDetailServices;
        this.registerRepo = registerRepo;
    }

    @Override
    public void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            final String jwt = authHeader.substring(7);
            final String id = jwtUtils.getIdFromToken(jwt);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (id != null && auth == null) {
                UserDetails userdetails = this.userDetailServices.loadUserByUsername(id);
                if (userdetails.getUsername().equals(id) || jwtUtils.validateJwtToken(jwt)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userdetails,
                            null,
                            userdetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        filterChain.doFilter(request, response);
    }
}
