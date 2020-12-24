package covid19testing.dao;

import covid19testing.model.Test;
import org.springframework.data.repository.CrudRepository;

public interface TestRepository extends CrudRepository<Test, String> {

	Test findTestByTestID(String testID);

	void deleteTestByTestID(String testID);

}
