package fabrice.sabackend.Controller;

import fabrice.sabackend.Module.Patient;
import fabrice.sabackend.Repository.PatientRepository;
import jakarta.validation.Valid;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/")
    public String home() {
        return "redirect:/user/index";
    }

    @GetMapping("/user/index")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String index(Model model,@RequestParam(name="page", defaultValue= "0")int page,
                        @RequestParam(name="size", defaultValue = "4") int size,
                        @RequestParam(name="Keyword",defaultValue = "") String kw) {

        Page<Patient> PagePatients= patientRepository.findByFirstNameContains(kw,PageRequest.of(page,size));
        model.addAttribute("patients", PagePatients.getContent());
        model.addAttribute("pages", new int[PagePatients.getTotalPages()]);
        model.addAttribute("current",page);
        model.addAttribute("keyword",kw);
        return "patients";
    }
    @GetMapping("/admin/deletePatient")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(@RequestParam(name="id") Long id,@RequestParam(name="Keyword",defaultValue = "") String Keyword,
                         @RequestParam(name="current",defaultValue ="0" ) int current) {
        patientRepository.deleteById(id);
        return "redirect:/user/index?page="+current+"&Keyword="+Keyword;
    }
    @GetMapping("/admin/formPatients")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String formPatient(Model model){
        model.addAttribute("patient", new Patient());
        return "formPatients";
    }
    @PostMapping("/admin/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String save(Model model, @Valid Patient patient, BindingResult bindingResult){
        if(bindingResult.hasErrors())return "formPatients";
        patientRepository.save(patient);
        return "redirect:/user/index";
    }
    @GetMapping("/admin/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String update(Model model, @RequestParam(name="id") Long id) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if(patient == null) throw new IllegalArgumentException("Patient not found");

        model.addAttribute("patient", patient);

        return "editPatients";
    }

}
