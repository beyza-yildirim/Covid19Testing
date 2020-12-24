package covid19testing.service;

import covid19testing.dao.ApplicationRepository;
import covid19testing.dao.PatientRepository;
import covid19testing.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ApplicationService {
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    PatientRepository patientRepository;

    @Transactional
    public Application createApplication(double fever, boolean cough, boolean shortnessBreath,
                                         String otherSymptoms, Aches aches, ChestPain chestPain,
                                         Tiredness tiredness, SoreThroat soreThroat) {
        if (fever < 34.0 || (fever > 43.0 && fever < 93.0) || fever > 109.0) { //if out of range both in celsius and fahrenheit
            throw new IllegalArgumentException("Invalid Body Temperature!");
        }
        Application application = new Application();
        application.setApplicationID();
        application.setFever(fever);
        application.setCough(cough);
        application.setShortnessBreath(shortnessBreath);
        application.setOtherSymptoms(otherSymptoms);
        application.setAches(aches);
        application.setChestPain(chestPain);
        application.setTiredness(tiredness);
        application.setSoreThroat(soreThroat);
        application.setStatus(Status.InProcess);
        applicationRepository.save(application);
        return application;
    }
    
    @Transactional
    public void deleteApplication(String applicationID) {
        Application app = applicationRepository.findApplicationByApplicationID(applicationID);
        if (app == null) {
            throw new IllegalArgumentException("Application with invalid ID!");
        }
        applicationRepository.deleteApplicationByApplicationID(applicationID);
    }

    //TODO: Add missing parameters
    @Transactional
    public Application updateApplication(double fever, boolean cough, boolean shortnessBreath,
                                         String otherSymptoms, Status status, String applicationID) {
        Application application = applicationRepository.findApplicationByApplicationID(applicationID);
        if(application == null){
            throw new IllegalArgumentException("Application cannot be null!");
        }
        application.setFever(fever);
        application.setCough(cough);
        application.setShortnessBreath(shortnessBreath);
        application.setOtherSymptoms(otherSymptoms);
        application.setStatus(status);
        applicationRepository.save(application);
        return application;
    }
    @Transactional
    public Application getApplicationByApplicationID(String id){
        return applicationRepository.findApplicationByApplicationID(id);
    }
    @Transactional
    public List<Application> getAllApplications() {
        return new ArrayList<Application>((Collection<? extends Application>) applicationRepository.findAll());
    }
    @Transactional
    public Application addPatientToApplication(Application app, Patient p){
        if(app == null){
            throw new IllegalArgumentException("Application cannot be null!");
        }
        if(p == null){
            throw new IllegalArgumentException("Patient cannot be null!");
        }
        app.setApplicant(p);
        applicationRepository.save(app);
        return app;
    }
    @Transactional
    public Application evaluateApplication(Application app, boolean result) {//true = accepted false = rejected
        if(app == null){
            throw new IllegalArgumentException("Application cannot be null!");
        }
        app.setResult(result);
        app.setStatus(Status.ResultReady);
        applicationRepository.save(app);
        return app;
    }
    
}
