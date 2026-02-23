package fabrice.sabackend.security.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data @NoArgsConstructor
@AllArgsConstructor
public class AppRole {
    @Id
    private String role;
    @ManyToMany
    private Collection<AppUser> users=new ArrayList<>();
}
