package fabrice.sabackend;

import fabrice.sabackend.Module.Patient;
import fabrice.sabackend.Repository.PatientRepository;
import fabrice.sabackend.security.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class SaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaBackendApplication.class, args);


    }
    //@Bean
    public CommandLineRunner start(PatientRepository patientRepository) {
        return args -> {

            List<Patient> patients= patientRepository.findAll();
            patients.forEach(e-> System.out.println(e.toString()));

        };
    }
    @Bean
    public CommandLineRunner commandLineRunnerUserDetailsService(AccountService accountService) {
        return args -> {

           // accountService.addRole("ADMIN");
           // accountService.addRole("USER");

            //accountService.addUser("Fabrice","1234","1234");
           // accountService.addUser("Cedric","1234","1234");
           // accountService.addUser("Patrick","1234","1234");

            //accountService.addNewRoleToUser("Cedric","USER");
            //accountService.addNewRoleToUser("Fabrice","USER");
           // accountService.addNewRoleToUser("Fabrice","ADMIN");
           // accountService.addNewRoleToUser("Patrick","ADMIN");
           // accountService.addNewRoleToUser("Patrick","USER");

        };
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
