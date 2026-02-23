package fabrice.sabackend.security.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    @Id
    private String id;
    @Column(unique = true)
    private String username;
    private String password;
    @ManyToMany(mappedBy ="users", fetch = FetchType.EAGER)

    private Collection<AppRole> roles=new ArrayList<>();
}
