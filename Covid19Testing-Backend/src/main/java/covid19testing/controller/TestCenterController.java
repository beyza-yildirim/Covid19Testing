package covid19testing.controller;

import covid19testing.dto.TestCenterDto;
import covid19testing.model.TestCenter;
import covid19testing.service.TestCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestCenterController {
    @Autowired
    TestCenterService testCenterService;

    @PostMapping(value = {"/testCenters/create", "/testCenters/create/"})
    public TestCenterDto createTestCenter(@RequestBody TestCenterDto c){
        TestCenter center = testCenterService.createTestCenter(c.getName(), c.getAddress(), c.getCity(), c.getProvince());
        return convertToDto(center);
    }

    private TestCenterDto convertToDto(TestCenter c){
        TestCenterDto testCenterDto = new TestCenterDto(c.getName(), c.getAddress(), c.getCity(), c.getProvince());
        return testCenterDto;
    }
}
