package covid19testing.PersistenceTests;

import covid19testing.dao.*;
import covid19testing.model.Application;
import covid19testing.model.Appointment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PatientPersistenceTesting {

    public static String insuranceNumber = "GKJD482929";
    public static String name = "Abdullatif";
    public static String surname = "Hassan";
    public static String address = "1234fndkfmk";
    public static String city = "SaintLaurent";
    public static String province = "QC";
    public static int age = 21;
    public static String travel = "none";
    public static String preCondition = "none";
    public static String medication = "hydroxychloroquin";

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    TestCenterRepository testCenterRepository;
    @Autowired
    TestRepository testRepository;

    @BeforeEach
    public void clearDatabase(){
        patientRepository.deleteAll();
        applicationRepository.deleteAll();
       appointmentRepository.deleteAll();
        testCenterRepository.deleteAll();
        testRepository.deleteAll();
    }

    @Test
    public void patientCreateTest(){

    }



}
