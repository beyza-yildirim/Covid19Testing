package covid19testing.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Patient {

    private String name;
    private String surname;
    private String address;
    private String city;
    private String province;
    private String insuranceNumber;
    private int age;
    private String travel;
    private String preCondition;
    private String medication;
    private Set<Application> applications;
    private Set<Appointment> appointments;
    private Set<Test> tests;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }


    @Id
    public String getInsuranceNumber() {
        return this.insuranceNumber;
    }

    public void setInsuranceNumber(String insurance) {
        this.insuranceNumber = insurance;
    }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public String getTravel() {
        return this.travel;
    }

    public void setTravel(String travel) {
        this.travel = travel;
    }

    public String getPreCondition() {
        return this.preCondition;
    }

    public void setPreCondition(String preCondition) {
        this.preCondition = preCondition;
    }

    public String getMedication() {
        return this.medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    @OneToMany(mappedBy = "applicant", fetch = FetchType.EAGER)
    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }

    @OneToMany(mappedBy = "appointmentPatient", fetch = FetchType.EAGER)
    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    @OneToMany(mappedBy = "tester", fetch = FetchType.EAGER)
    public Set<Test> getTests() {
        return tests;
    }

    public void setTests(Set<Test> tests) {
        this.tests = tests;
    }

}
