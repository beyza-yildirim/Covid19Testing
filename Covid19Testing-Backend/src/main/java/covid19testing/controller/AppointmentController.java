package covid19testing.controller;

import covid19testing.dto.ApplicationDto;
import covid19testing.dto.AppointmentDto;
import covid19testing.dto.PatientDto;
import covid19testing.model.Application;
import covid19testing.model.Appointment;
import covid19testing.model.Patient;
import covid19testing.service.AppointmentService;
import covid19testing.service.PatientService;
import covid19testing.service.TestCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    PatientService patientService;
    @Autowired
    TestCenterService testCenterService;

    @PostMapping(value = {"/appointments/create", "/appointments/create/"})
    public AppointmentDto createAppointment(@RequestParam Date date, @RequestParam Time time, @RequestParam String centerName) {
        Appointment appointment = appointmentService.createAppointment(date, time);
        appointmentService.assignAppointmentToTestCenter(centerName, appointment);
        AppointmentDto appointmentDto = convertToDto(appointment);
        return appointmentDto;
    }

    @DeleteMapping(value = {"/appointments/delete/{appointmentID}", "/appointments/delete/{appointmentID}/"})
    public void deleteAppointment(@PathVariable("appointmentID") String appointmentID){
        appointmentService.deleteAppointment(appointmentID);
    }

    @PutMapping(value = {"/appointments/cancel/{appointmentID}", "/appointments/cancel/{appointmentID}/"})
    public AppointmentDto cancelAppointmentForPatient(@PathVariable("appointmentID") String appointmentID){
        Appointment apt = appointmentService.getAppointmentByAppointmentID(appointmentID);
        appointmentService.cancelAppointmentForPatient(appointmentID);
        return convertToDto(apt);
    }

    @GetMapping(value = {"/appointments/getAllAppointments", "/appointments/getAllAppointments/"})
    public List<AppointmentDto> getAllAppointments(){
        List<AppointmentDto> list = new ArrayList<>();
        for (Appointment apt : appointmentService.getAllAppointments()) {
            list.add(convertToDto(apt));
        }
        return list;
    }

    @GetMapping(value = {"/appointments/getAppointmentsForPatient/{insuranceNumber}", "/appointments/getAppointmentsForPatient/{insuranceNumber}/"})
    public List<AppointmentDto> getAppointmentsForPatient(@PathVariable ("insuranceNumber") String insuranceNumber){
        List<AppointmentDto> list = new ArrayList<>();
        for(Appointment app: patientService.getPatientByInsuranceNumber(insuranceNumber).getAppointments()){
            list.add(convertToDto(app));
        }
        return list;
    }

    @GetMapping(value = {"/appointments/getAppointmentsForCenter/{centerName}", "/appointments/getAppointmentsForCenter/{centerName}/"})
    public List<AppointmentDto> getAppointmentsForCenter(@PathVariable ("centerName") String centerName){
        List<AppointmentDto> list = new ArrayList<>();
        for(Appointment app: testCenterService.getTestCenterByCenterName(centerName).getAppointments()){
            list.add(convertToDto(app));
        }
        return list;
    }

    @PutMapping(value = {"/appointments/assignCenter", "/appointments/assignCenter/"})
    public AppointmentDto assignTestCenter(@RequestParam String appointmentID, @RequestParam String centerName){
        Appointment apt = appointmentService.getAppointmentByAppointmentID(appointmentID);
        appointmentService.assignAppointmentToTestCenter(centerName, apt);
        return convertToDto(apt);
    }

    @PutMapping(value = {"/appointments/bookPatient", "/appointments/bookPatient/"})
    public AppointmentDto bookPatient(@RequestParam String appointmentID, @RequestParam String patientInsuranceNumber){
        Appointment apt = appointmentService.getAppointmentByAppointmentID(appointmentID);
        Patient p = patientService.getPatientByInsuranceNumber(patientInsuranceNumber);
        appointmentService.bookAppointmentForPatient(p, apt);
        return convertToDto(apt);
    }

    private AppointmentDto convertToDto(Appointment a) {
        AppointmentDto appointmentDto;
        if(a.getappointmentPatient() == null){
            appointmentDto = new AppointmentDto(a.getDate(), a.getTime(), null,
                    a.getLocation().getName(), a.getAppointmentID(), a.isAvailable());
        }
        else{
            appointmentDto = new AppointmentDto(a.getDate(), a.getTime(), a.getappointmentPatient().getInsuranceNumber(),
                    a.getLocation().getName(), a.getAppointmentID(), a.isAvailable());
        }
        return appointmentDto;
    }
}
