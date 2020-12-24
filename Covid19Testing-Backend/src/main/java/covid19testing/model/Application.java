package covid19testing.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Application {

    private double fever;
    private boolean cough;
    private boolean shortnessBreath;
    private boolean result;
    private String otherSymptoms;
    private Patient applicant;
    private String applicationID;

	@Enumerated(EnumType.STRING)
        private Tiredness tiredness;

	@Enumerated(EnumType.STRING)
        private SoreThroat soreThroat;

	@Enumerated(EnumType.STRING)
        private Aches aches;

	@Enumerated(EnumType.STRING)
        private ChestPain chestPain;

	@Enumerated(EnumType.STRING)
        private ApplicationResult applicationResult;

	@Enumerated(EnumType.STRING)
        private Status status;

    public Aches getAches() {
        return aches;
    }

    public void setAches(Aches aches) {
        this.aches = aches;
    }

    public ApplicationResult getApplicationResult() {
        return applicationResult;
    }

    public void setApplicationResult(ApplicationResult applicationResult) {
        this.applicationResult = applicationResult;
    }

    public Tiredness getTiredness() {
        return tiredness;
    }

    public void setTiredness(Tiredness tiredness) {
        this.tiredness = tiredness;
    }

    public ChestPain getChestPain() {
        return chestPain;
    }

    public void setChestPain(ChestPain chestPain) {
        this.chestPain = chestPain;
    }

    public SoreThroat getSoreThroat() {
        return soreThroat;
    }

    public void setSoreThroat(SoreThroat soreThroat) {
        this.soreThroat = soreThroat;
    }

    public double getFever() {
        return this.fever;
    }

    public void setFever(double Fever) {
        this.fever = fever;
    }

    public boolean getCough() {
        return this.cough;
    }

    public void setCough(boolean cough) {
        this.cough = cough;
    }

    public boolean getShortnessBreath() {
        return this.shortnessBreath;
    }

    public void setShortnessBreath(boolean shortnessBreath) {
        this.shortnessBreath = shortnessBreath;
    }

    public String getOtherSymptoms() {
        return this.otherSymptoms;
    }

    public void setOtherSymptoms(String otherSymptoms) {
        this.otherSymptoms = otherSymptoms;
    }

    @ManyToOne(optional = true)
    public Patient getApplicant() {
        return applicant;
    }

    public void setApplicant(Patient patient) {
        this.applicant = patient;
    }

    public void setApplicationID(String value) {
        this.applicationID = value;
    }

    public void setApplicationID() {
        this.applicationID = UUID.randomUUID().toString();
    }

    @Id
    public String getApplicationID() {
        return this.applicationID;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
