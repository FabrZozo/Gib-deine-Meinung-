package fabrice.sabackend.security.service;

import fabrice.sabackend.security.entities.AppRole;
import fabrice.sabackend.security.entities.AppUser;
import fabrice.sabackend.security.repo.AppUserRepository;

public interface AccountService {
    public AppUser addUser(String username, String password,String confirmPassword);
    AppRole addRole(String role);
    public void addNewRoleToUser(String username, String role);
    public void removeRoleFromUser(String username, String role);
    AppUser loadUserByUsername(String username);

}
