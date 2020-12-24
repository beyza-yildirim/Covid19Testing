package covid19testing.dto;

import java.sql.Date;
import java.sql.Time;

public class AppointmentDto {
    private Date date;
    private Time time;
    private String patientInsuranceNumber;
    private String testCenterName;
    private String appointmentID;
    public boolean available;

    public AppointmentDto() {
    }

    public AppointmentDto(Date date, Time time, String patientInsuranceNumber, String testCenterName,
                          String appointmentID, boolean available){
        this.appointmentID = appointmentID;
        this.available = available;
        this.date = date;
        this.patientInsuranceNumber = patientInsuranceNumber;
        this.testCenterName = testCenterName;
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getPatientInsuranceNumber() {
        return patientInsuranceNumber;
    }

    public void setPatientInsuranceNumber(String patientInsuranceNumber) {
        this.patientInsuranceNumber = patientInsuranceNumber;
    }

    public String getTestCenterName() {
        return testCenterName;
    }

    public void setTestCenterName(String testCenterName) {
        this.testCenterName = testCenterName;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
