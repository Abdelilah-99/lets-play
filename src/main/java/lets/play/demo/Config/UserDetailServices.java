package lets.play.demo.Config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import lets.play.demo.Repository.RegisterRepo;

@Component
public class UserDetailServices implements UserDetailsService {
    private final RegisterRepo registerRepo;

    public UserDetailServices(RegisterRepo registerRepo) {
        this.registerRepo = registerRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        lets.play.demo.Entity.User user = registerRepo.findById(id).orElseThrow();
        return User.builder()
                .username(user.name)
                .password(user.password)
                .authorities(new SimpleGrantedAuthority(user.role))
                .build();
    }
}
