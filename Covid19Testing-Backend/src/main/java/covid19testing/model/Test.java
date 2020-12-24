package covid19testing.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@Entity
public class Test {

    //private boolean result;
    private int fever;
    private Date date;
    private Time time;
    private Patient tester;
    private TestCenter testSite;
    private String testID;

	@Enumerated(EnumType.STRING)
        private Status status;
	
	@Enumerated(EnumType.STRING)
        private Result result;

    /*public boolean getResult() {
        return this.result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }*/

    public int getFever() {
        return this.fever;
    }

    public void setFever(int Fever) {
        this.fever = fever;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    @ManyToOne(optional = false)
    public Patient getTester() {
        return tester;
    }

    public void setTester(Patient patient) {
        this.tester = patient;
    }

    @ManyToOne(optional = false)
    public TestCenter getTestSite() {
        return testSite;
    }

    public void setTestSite(TestCenter testCenter) {
        this.testSite = testCenter;
    }

    public void setTestID(String value) {
        this.testID = value;
    }

    public void setTestID() {
        this.testID = UUID.randomUUID().toString();
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Id
    public String getTestID() {
        return this.testID;
    }
}

