package covid19testing.service;

import covid19testing.dao.AppointmentRepository;
import covid19testing.dao.PatientRepository;
import covid19testing.dao.TestCenterRepository;
import covid19testing.model.Appointment;
import covid19testing.model.Patient;
import covid19testing.model.TestCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    TestCenterRepository testCenterRepository;
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    TestService testService;

    @Transactional
    public Appointment createAppointment(Date date, Time time) {
        Appointment newAppointment = new Appointment();
        newAppointment.setDate(date);
        newAppointment.setTime(time);
        newAppointment.setAppointmentID();
        newAppointment.setAvailable(true);
        appointmentRepository.save(newAppointment);
        return newAppointment;
    }
    @Transactional
    public Appointment getAppointmentByAppointmentID(String id){
        Appointment appointment = appointmentRepository.findAppointmentByAppointmentID(id);
        return appointment;
    }

    @Transactional
    public Appointment cancelAppointmentForPatient(String appointmentID){
        Appointment apt = appointmentRepository.findAppointmentByAppointmentID(appointmentID);
        if(apt == null){
            throw new IllegalArgumentException("Appointment is null!");
        }
        apt.setappointmentPatient(null);
        apt.setAvailable(true);
        appointmentRepository.save(apt);
        return apt;
    }

    @Transactional
    public List<Appointment> getAllAppointments() {
        return new ArrayList<Appointment>((Collection<? extends Appointment>) appointmentRepository.findAll());
    }

    @Transactional
    public void deleteAppointment(String appointmentID) {
        Appointment appointment = appointmentRepository.findAppointmentByAppointmentID(appointmentID);
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment with id:" + appointmentID + "Does not exist");
        }
        appointmentRepository.deleteAppointmentByAppointmentID(appointmentID);
    }

    @Transactional
    public Appointment updateAppointment(Date date, Time time, String appointmentID, boolean available) {
        Appointment appointment = appointmentRepository.findAppointmentByAppointmentID(appointmentID);
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment with id:" + appointmentID + "Does not exist");
        }
        appointment.setTime(time);
        appointment.setDate(date);
        appointment.setAvailable(available);
        appointmentRepository.save(appointment);
        return appointment;
    }

    @Transactional
    public void bookAppointmentForPatient(Patient patient, Appointment appointment) {

        if (patient == null) {
            throw new IllegalArgumentException("Patient does not exist!");
        }
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment does not exist!");
        }

        if (!appointment.isAvailable()) {
           throw new IllegalArgumentException("Appointment already booked");
        }
        //TODO: create a test object for the patient (once beyza finishes testService)
        appointment.setappointmentPatient(patient);
        appointment.setAvailable(false);
        appointmentRepository.save(appointment);
    }

    @Transactional
    public void assignAppointmentToTestCenter(String centerName, Appointment appointment) {
        TestCenter center = testCenterRepository.findTestCenterByName(centerName);
        if (center == null) {
            //throw new IllegalArgumentException("Test Center does not exist!");
        }
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment does not exist!");
        }

        if (!appointment.isAvailable()) {
            throw new IllegalArgumentException("Appointment already booked");
        }
        appointment.setLocation(center);
        appointmentRepository.save(appointment);
    }
}
