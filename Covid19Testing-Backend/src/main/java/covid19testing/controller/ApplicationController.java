package covid19testing.controller;

import covid19testing.dto.ApplicationDto;
import covid19testing.dto.AppointmentDto;
import covid19testing.model.*;
import covid19testing.service.ApplicationService;
import covid19testing.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;
    @Autowired
    PatientService patientService;
    //TODO: Fix the issue with fever = 0.0
    @PostMapping(value = {"/applications/create", "/applications/create/"})
    public ApplicationDto createApplication(@RequestParam double fever, @RequestParam boolean cough,
                                            @RequestParam boolean shortnessBreath, @RequestParam String otherSymptoms,
                                            @RequestParam Aches aches, @RequestParam ChestPain chestPain,
                                            @RequestParam Tiredness tiredness, @RequestParam SoreThroat soreThroat,
                                            @RequestParam String patientInsuranceNumber) {
        Application a = applicationService.createApplication(fever, cough, shortnessBreath, otherSymptoms, aches, chestPain, tiredness, soreThroat);
        Patient p = patientService.getPatientByInsuranceNumber(patientInsuranceNumber);
        Application newApp = applicationService.addPatientToApplication(a, p);
        return convertToDto(a);//TODO: newapp
    }

    @GetMapping(value = {"/applications/getApplicationByApplicationID/{applicationID}", "/applications/getApplicationByApplicationID/{applicationID}"})
    public ApplicationDto getApplicationByApplicationID(@PathVariable("applicationID") String applicationID) {
        Application app = applicationService.getApplicationByApplicationID(applicationID);
        return convertToDto(app);
    }

    @GetMapping(value = {"/applications/getAllApplications", "/applications/getAllApplications/"})
    public List<ApplicationDto> getAllApplications(){
        List<ApplicationDto> list = new ArrayList<>();
        for (Application app : applicationService.getAllApplications()) {
            list.add(convertToDto(app));
        }
        return list;
    }

    @GetMapping({"/applications/getApplicationsForPatient/{insuranceNumber}", "/applications/getApplicationsForPatient/{insuranceNumber}/"})
    public List<ApplicationDto> getApplicationsForPatient(@PathVariable ("insuranceNumber") String insuranceNumber){
        List<ApplicationDto> list = new ArrayList<>();
        for(Application app: patientService.getPatientByInsuranceNumber(insuranceNumber).getApplications()){
            list.add(convertToDto(app));
        }
        return list;
    }

    @DeleteMapping(value = {"/applications/deleteApplication/{applicationID}", "/applications/deleteApplication/{applicationID}"})
    public void deleteApplication(@PathVariable("applicationID") String applicationID){
        applicationService.deleteApplication(applicationID);
    }

    @PutMapping(value = {"/applications/evaluateApplication/{applicationID}", "/applications/evaluateApplication/{applicationID}"})
    public ApplicationDto evaluateApplication(@PathVariable("applicationID") String applicationID, @RequestParam boolean result){
        Application app = applicationService.getApplicationByApplicationID(applicationID);
        applicationService.evaluateApplication(app, result);
        return convertToDto(app);
    }

    private ApplicationDto convertToDto(Application app) {
        ApplicationDto applicationDto = new ApplicationDto(app.getFever(), app.getCough(), app.getShortnessBreath(), app.getOtherSymptoms(),
                app.getResult(), app.getApplicant().getInsuranceNumber(), app.getApplicationID(), app.getTiredness(),
                app.getSoreThroat(), app.getAches(), app.getChestPain(), app.getStatus());
        return applicationDto;
    }
}
