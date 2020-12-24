package covid19testing.dto;

//add attributes

import covid19testing.model.*;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Date;
import java.sql.Time;

public class TestDto {

    private int fever;
    private Date date;
    private Time time;
    private String patientInsuranceNo;
    private String testCenterName;
    private String testID;
    private boolean result;
    private Status status;


    public TestDto() {
    }

    public TestDto(int fever, Date date, Time time, String patientInsuranceNo, String testCenterName, String testID) {
        this.fever = fever;
        this.date = date;
        this.time = time;
        this.patientInsuranceNo = patientInsuranceNo;
        this.testCenterName = testCenterName;
        this.testID = testID;
    }

//enums, status? result?

    public int getFever() {
        return this.fever;
    }

    public void setFever(int Fever) {
        this.fever = fever;
    }

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

    public String getPatientInsuranceNo() {
        return patientInsuranceNo;
    }

    public void setPatientInsuranceNo(String patientInsuranceNo) {
        this.patientInsuranceNo = patientInsuranceNo;
    }

    public String getTestCenterName() {
        return testCenterName;
    }

    public void setTestCenterName(String testCenterName) {
        this.testCenterName = testCenterName;
    }

    public String getTestID() {
        return testID;
    }

    public void setTestID(String testID) {
        this.testID = testID;
    }
}	
