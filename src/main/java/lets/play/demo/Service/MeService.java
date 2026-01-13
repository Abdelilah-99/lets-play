package lets.play.demo.Service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lets.play.demo.DTOs.MeResDto;
import lets.play.demo.Entity.User;
import lets.play.demo.Repository.UserRepo;

@Service
public class MeService {
    private final UserRepo userRepo;

    public MeService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public MeResDto getMyInfo() {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepo.findById(id).orElseThrow(() -> {
            throw new UsernameNotFoundException("User Not Exsist");
        });
        return new MeResDto(user.email, user.name, user.role);
    }
}
