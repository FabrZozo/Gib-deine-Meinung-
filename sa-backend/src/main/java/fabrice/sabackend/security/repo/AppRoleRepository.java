package fabrice.sabackend.security.repo;

import fabrice.sabackend.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, String> {
}
