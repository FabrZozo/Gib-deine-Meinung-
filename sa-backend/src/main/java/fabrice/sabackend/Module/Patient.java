package fabrice.sabackend.Module;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Entity
@Getter @Setter @ToString @Builder
 @NoArgsConstructor @AllArgsConstructor

public class Patient {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Size(min=4, max=20)
    private String firstName;
    @Size(min=4, max=20)
    private String lastName;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    @DecimalMin("100")
    private int score;
    private boolean malade;
}
