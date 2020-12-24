package covid19testing.dto;

import covid19testing.model.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class ApplicationDto {
    private double fever;
    private boolean cough;
    private boolean shortnessBreath;
    private String otherSymptoms;
    private boolean result;
    private String patientInsuranceNumber;
    private String applicationID;
    private Tiredness tiredness;
    private SoreThroat soreThroat;
    private Aches aches;
    private ChestPain chestPain;
    private Status status;

    public ApplicationDto(){
    }

    public ApplicationDto(double fever, boolean cough, boolean shortnessBreath, String otherSymptoms,
                          boolean result, String patientInsuranceNumber, String applicationID, Tiredness tiredness,
                          SoreThroat soreThroat, Aches aches, ChestPain chestPain, Status status){
        this.fever = fever;
        this.cough = cough;
        this.shortnessBreath = shortnessBreath;
        this.otherSymptoms = otherSymptoms;
        this.result = result;
        this.patientInsuranceNumber = patientInsuranceNumber;
        this.applicationID = applicationID;
        this.tiredness = tiredness;
        this.soreThroat = soreThroat;
        this.aches = aches;
        this.chestPain = chestPain;
        this.status = status;
    }

    public double getFever() {
        return fever;
    }

    public void setFever(double fever) {
        this.fever = fever;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public String getOtherSymptoms() {
        return otherSymptoms;
    }

    public void setOtherSymptoms(String otherSymptoms) {
        this.otherSymptoms = otherSymptoms;
    }

    public String getPatientInsuranceNumber() {
        return patientInsuranceNumber;
    }

    public void setPatientInsuranceNumber(String patientInsuranceNumber) {
        this.patientInsuranceNumber = patientInsuranceNumber;
    }

    public boolean isCough() {
        return cough;
    }

    public void setCough(boolean cough) {
        this.cough = cough;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean isShortnessBreath() {
        return shortnessBreath;
    }

    public void setShortnessBreath(boolean shortnessBreath) {
        this.shortnessBreath = shortnessBreath;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public SoreThroat getSoreThroat() {
        return soreThroat;
    }

    public void setSoreThroat(SoreThroat soreThroat) {
        this.soreThroat = soreThroat;
    }

    public ChestPain getChestPain() {
        return chestPain;
    }

    public void setTiredness(Tiredness tiredness) {
        this.tiredness = tiredness;
    }

    public Tiredness getTiredness() {
        return tiredness;
    }

    public void setChestPain(ChestPain chestPain) {
        this.chestPain = chestPain;
    }

    public Aches getAches() {
        return aches;
    }

    public void setAches(Aches aches) {
        this.aches = aches;
    }
}
