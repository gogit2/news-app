package co.ahmoh.newsapp.security;
import co.ahmoh.newsapp.entities.Admin;
import co.ahmoh.newsapp.repositories.AdminRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class NewsUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    public NewsUserDetailsService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid email or password"));
        return new NewsUserDetails(admin);
    }
}
