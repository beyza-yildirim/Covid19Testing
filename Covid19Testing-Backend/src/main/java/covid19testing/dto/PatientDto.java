package covid19testing.dto;

import covid19testing.model.Patient;

public class PatientDto {
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

    public PatientDto() {
    }

    public PatientDto(String name, String surname, String address, String city, String province,
                   String insuranceNumber, int age, String travel, String preCondition, String medication){
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.city = city;
        this.province = province;
        this.insuranceNumber = insuranceNumber;
        this.age = age;
        this.travel = travel;
        this.preCondition = preCondition;
        this.medication = medication;
    }
    public PatientDto(String insuranceNumber){
        this.insuranceNumber = insuranceNumber;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreCondition() {
        return preCondition;
    }

    public void setPreCondition(String preCondition) {
        this.preCondition = preCondition;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTravel() {
        return travel;
    }

    public void setTravel(String travel) {
        this.travel = travel;
    }
}
