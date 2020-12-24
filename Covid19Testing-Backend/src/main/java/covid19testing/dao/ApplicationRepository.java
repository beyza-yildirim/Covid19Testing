package covid19testing.dao;

import covid19testing.model.Application;
import covid19testing.model.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ApplicationRepository extends CrudRepository<Application, String> {
    Application findApplicationByApplicationID(String id);

    List<Application> findApplicationsByApplicant(Patient applicant); //double check

    void deleteApplicationByApplicationID(String id);
}
