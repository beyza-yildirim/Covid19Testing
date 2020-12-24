package covid19testing.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@Entity
public class Appointment {

    private Date date;
    private Time time;
    private Patient appointmentPatient;
    private TestCenter location;
    private String appointmentID;
    public boolean available;

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return this.time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @ManyToOne(optional = true)
    public Patient getappointmentPatient() {
        return appointmentPatient;
    }

    public void setappointmentPatient(Patient patient) {
        this.appointmentPatient = patient;
    }

    @ManyToOne(optional = true)
    public TestCenter getLocation() {
        return location;
    }

    public void setLocation(TestCenter testCenter) {
        this.location = testCenter;
    }

    public void setAppointmentID(String value) {
        this.appointmentID = value;
    }

    public void setAppointmentID() {
        this.appointmentID = UUID.randomUUID().toString();
    }

    @Id
    public String getAppointmentID() {
        return this.appointmentID;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
