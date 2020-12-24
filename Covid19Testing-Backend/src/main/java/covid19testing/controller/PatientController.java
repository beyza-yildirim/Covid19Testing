package covid19testing.controller;

import covid19testing.dto.PatientDto;
import covid19testing.model.Patient;
import covid19testing.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PatientController {
    @Autowired
    PatientService patientService;

    //TODO: Add get all applications per patient and all appointments
    @PostMapping(value = {"/patients/create", "/patients/create/"})
    public PatientDto createPatient(@RequestBody PatientDto p) throws IllegalAccessException {
        Patient patient = patientService.createPatient(p.getName(), p.getSurname(), p.getAddress(), p.getCity(),
                p.getProvince(), p.getInsuranceNumber(), p.getAge(), p.getTravel(), p.getPreCondition(), p.getMedication());
        return convertToDto(patient);
    }

    @GetMapping(value = {"/patients/getAllPatients", "/patients/getAllPatients/"})
    public List<PatientDto> getAllPatients() {
        List<PatientDto> list = new ArrayList<PatientDto>();
        for (Patient p : patientService.getAllPatients()) {
            list.add(convertToDto(p));
        }
        return list;
    }

    @DeleteMapping(value = {"/patients/delete/{insuranceNumber}", "/patients/delete/{insuranceNumber}/"})
    public void deletePatient(@PathVariable("insuranceNumber") String insuranceNumber) {
        patientService.deletePatient(insuranceNumber);
    }

    @PutMapping(value = {"/patients/update", "/patients/update/"} )
    public PatientDto updatePatient(@RequestBody PatientDto p) throws IllegalAccessException {
        Patient updatedPatient = patientService.updatePatient(p.getName(), p.getSurname(), p.getAddress(), p.getCity(),
                p.getProvince(), p.getInsuranceNumber(), p.getAge(), p.getTravel(), p.getPreCondition(), p.getMedication());
        return convertToDto(updatedPatient);
    }

    private PatientDto convertToDto(Patient p) {
        PatientDto patientDto = new PatientDto(p.getName(), p.getSurname(), p.getAddress(), p.getCity(),
                p.getProvince(), p.getInsuranceNumber(), p.getAge(), p.getTravel(), p.getPreCondition(), p.getMedication());

        return patientDto;
    }
}
