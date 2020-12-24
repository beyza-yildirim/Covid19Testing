package covid19testing.service;

import covid19testing.dao.ApplicationRepository;
import covid19testing.dao.AppointmentRepository;
import covid19testing.dao.PatientRepository;
import covid19testing.dao.TestRepository;
import covid19testing.model.Application;
import covid19testing.model.Appointment;
import covid19testing.model.Patient;
import covid19testing.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    TestRepository testRepository;

    @Transactional
    public Patient createPatient(String name, String surname, String address, String city,
                                 String province, String insuranceNumber, int age,
                                 String travel, String precondition, String medication) throws IllegalAccessException {
        patientCheckParams(name, surname, address, city, province, insuranceNumber, age, travel, precondition, medication);
        Patient oldPatient = patientRepository.findPatientByInsuranceNumber(insuranceNumber);//check if ins number exists already
        if (oldPatient != null) {
            throw new IllegalArgumentException("patient with insurance number" + insuranceNumber + "Already Exists");
        }

        Patient yeniPatient = new Patient();
        yeniPatient.setAddress(address);
        yeniPatient.setAge(age);
        yeniPatient.setCity(city);
        yeniPatient.setProvince(province);
        yeniPatient.setInsuranceNumber(insuranceNumber);
        yeniPatient.setName(name);
        yeniPatient.setSurname(surname);
        yeniPatient.setPreCondition(precondition);
        yeniPatient.setMedication(medication);
        yeniPatient.setTravel(travel);
        patientRepository.save(yeniPatient);
        return yeniPatient;
    }
    public Patient createPatient(String insuranceNumber) throws IllegalAccessException {
        Patient oldPatient = patientRepository.findPatientByInsuranceNumber(insuranceNumber);//check if ins number exists already
        if (oldPatient != null) {
            throw new IllegalArgumentException("patient with insurance number" + insuranceNumber + "Already Exists");
        }
        Patient yeniPatient = new Patient();
        yeniPatient.setInsuranceNumber(insuranceNumber);
        patientRepository.save(yeniPatient);
        return yeniPatient;
    }

    @Transactional
    public Patient getPatientByInsuranceNumber(String insuranceNumber) {
        if (insuranceNumber == null || insuranceNumber.trim().length() == 0) {
            throw new IllegalArgumentException("invalid insurance number!");
        }
        Patient patientFound = patientRepository.findPatientByInsuranceNumber(insuranceNumber);
        if (patientFound == null) {
            throw new IllegalArgumentException("insurance number does not exist!");
        }
        return patientFound;
    }

    @Transactional
    public void deletePatient(String insuranceNumber) {
        Patient p = patientRepository.findPatientByInsuranceNumber(insuranceNumber);
        if (p == null) {
            throw new IllegalArgumentException("The patient with insurance number" + insuranceNumber + "does not exist!");
        }
        for (Appointment appointment : p.getAppointments()) {
            appointmentRepository.deleteAppointmentByAppointmentID(appointment.getAppointmentID());
        }
        for (Application app : p.getApplications()) {
            applicationRepository.deleteApplicationByApplicationID(app.getApplicationID());
        }
        for (Test t : p.getTests()) {
            testRepository.deleteTestByTestID(t.getTestID());
        }

        patientRepository.deletePatientByInsuranceNumber(insuranceNumber);
    }

    @Transactional
    public List<Patient> getAllPatients() {
        return new ArrayList<Patient>((Collection<? extends Patient>) patientRepository.findAll());
    }

    @Transactional
    public Patient updatePatient(String name, String surname, String address, String city,
                                 String province, String insuranceNumber, int age,
                                 String travel, String precondition, String medication) throws IllegalAccessException {
        patientCheckParams(name, surname, address, city, province, insuranceNumber, age, travel, precondition, medication);

        Patient p = patientRepository.findPatientByInsuranceNumber(insuranceNumber);

        if (p == null) {
            throw new IllegalArgumentException("The patient with insurance number" + insuranceNumber + "does not exist!");
        }
        p.setAddress(address);
        p.setAge(age);
        p.setCity(city);
        p.setProvince(province);
        p.setInsuranceNumber(insuranceNumber);
        p.setName(name);
        p.setSurname(surname);
        p.setPreCondition(precondition);
        p.setMedication(medication);
        p.setTravel(travel);
        patientRepository.save(p);
        return p;
    }

    @Transactional
    public List<Appointment> getAllAppointmentsForPatient(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient Cannot be null!");
        }
        List<Appointment> appointments = new ArrayList<>();
        for (Appointment a : appointmentRepository.findAll()) {
            if (a.getappointmentPatient().getInsuranceNumber().equals(patient.getInsuranceNumber())) {
                appointments.add(a);
            }
        }
        return appointments;
    }

    @Transactional
    public List<Application> getAllApplicationsForPatient(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient Cannot be null!");
        }
        List<Application> applications = new ArrayList<>();
        for (Application app : applicationRepository.findAll()) {
            if (app.getApplicant().getInsuranceNumber().equals(patient.getInsuranceNumber())) {
                applications.add(app);
            }
        }
        return applications;
    }

    @Transactional
    public List<Test> getAllTestsForPatient(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient Cannot be null!");
        }
        List<Test> tests = new ArrayList<>();
        for (Test test : testRepository.findAll()) {//is this better or should we use patient.getTests()???
            if (test.getTester().getInsuranceNumber().equals(patient.getInsuranceNumber())) {
                tests.add(test);
            }
        }
        return tests;
    }

    //TODO: make a getAllTestsfor city or province
    //TODO: patient
    ////////////////private methods

    private void patientCheckParams(String name, String surname, String address, String city,
                                    String province, String insuranceNumber, int age,
                                    String travel, String precondition, String medication) throws IllegalAccessException {
        String error = "";
        if (age <= 0 || age > 120) {
            error += "Age not valid!";
        }
        if (name == null || name.trim().length() == 0) {
            error += "Invalid name!";
        }
        if (surname == null || surname.trim().length() == 0) {
            error += "Invalid surname!";
        }
        if (address == null || address.trim().length() == 0) {
            error += "Invalid address!";
        }
        if (city == null || city.trim().length() == 0) {
            error += "Invalid city!";
        }
        if (province == null || province.trim().length() == 0) {
            error += "Invalid province!";
        }
        if (insuranceNumber == null || insuranceNumber.trim().length() == 0) { //add more details
            error += "Invalid insurance number!";
        }
        if (travel == null || travel.trim().length() == 0) {
            error += "Travel history empty!";
        }
        if (precondition == null || precondition.trim().length() == 0) {
            error += "Precondition empty!";
        }
        if (medication == null || medication.trim().length() == 0) {
            error += "Medication  empty!";
        }
        if (error.length() != 0) {
            throw new IllegalAccessException(error);
        }
    }
}
