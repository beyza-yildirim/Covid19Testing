package covid19testing.dao;

import covid19testing.model.Patient;
import org.springframework.data.repository.CrudRepository;


public interface PatientRepository extends CrudRepository<Patient, String> {

    Patient findPatientByInsuranceNumber(String insuranceNumber);

    void deletePatientByInsuranceNumber(String insuranceNumber);
}
