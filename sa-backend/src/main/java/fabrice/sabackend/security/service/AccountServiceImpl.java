package fabrice.sabackend.security.service;

import fabrice.sabackend.security.entities.AppRole;
import fabrice.sabackend.security.entities.AppUser;
import fabrice.sabackend.security.repo.AppRoleRepository;
import fabrice.sabackend.security.repo.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private AppUserRepository appUserRepository;

    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    AccountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public AppUser addUser(String username, String password,String confirmPassword) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if(appUser != null) throw new IllegalArgumentException("Username already exists");
        else if(!password.equals(confirmPassword)) throw new IllegalArgumentException("Passwords do not match");
        else appUser = new AppUser(UUID.randomUUID().toString(),username,passwordEncoder.encode(password),new ArrayList<>());
        return appUserRepository.save(appUser);
    }

    @Override
    public AppRole addRole(String role) {
        AppRole appRole = appRoleRepository.findById(role).orElse(null);
        if(appRole != null) throw new IllegalArgumentException("Role already exists");
        return appRoleRepository.save(new AppRole(role,new ArrayList<>()));
    }

    @Override
    public void addNewRoleToUser(String username, String role) {
         AppUser appUser = appUserRepository.findByUsername(username);
         if(appUser == null) throw new IllegalArgumentException("Username not exists");
        AppRole appRole = appRoleRepository.findById(role)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));
         appUser.getRoles().add(appRole);
         appRole.getUsers().add(appUser);

    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if(appUser == null) throw new IllegalArgumentException("Username not exists");
        AppRole appRole = appRoleRepository.findById(role)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));
        appUser.getRoles().remove(appRole);
        appRole.getUsers().remove(appUser);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
