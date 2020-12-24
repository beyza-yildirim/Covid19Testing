package covid19testing.dao;

import covid19testing.model.Appointment;
import covid19testing.model.TestCenter;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, String> {
    Appointment findAppointmentByAppointmentID(String ID);

    List<Appointment> findAppointmentsByLocation(TestCenter location);

    void deleteAppointmentByAppointmentID(String ID);
}
