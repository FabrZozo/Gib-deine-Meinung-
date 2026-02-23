package fabrice.sabackend.security.entities;

import fabrice.sabackend.security.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailServiceimpl implements UserDetailsService {

    private AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser= accountService.loadUserByUsername(username);
        if(appUser == null) throw new UsernameNotFoundException("User not found");

        String [] roles= appUser.getRoles().stream().map(AppRole::getRole).toArray(String[]::new);

        return User.withUsername(username).password(appUser.getPassword()).roles(roles).build();
    }
}

