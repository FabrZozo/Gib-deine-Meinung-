package fabrice.sabackend;

import fabrice.sabackend.Module.Patient;
import fabrice.sabackend.Repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class SaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaBackendApplication.class, args);


    }
    @Bean
    public CommandLineRunner start(PatientRepository patientRepository) {
        return args -> {

            List<Patient> patients= patientRepository.findAll();
            patients.forEach(e-> System.out.println(e.toString()));

        };
    }

}
