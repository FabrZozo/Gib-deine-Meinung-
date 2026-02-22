package fabrice.sabackend.Repository;

import fabrice.sabackend.Module.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    Page<Patient> findByFirstNameContains(String firstName, Pageable pageable);

}
