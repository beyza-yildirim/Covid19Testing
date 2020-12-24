package covid19testing;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
public class Covid19TestingApplication {

  public static void main(String[] args) {
    SpringApplication.run(Covid19TestingApplication.class, args);
  }

  @RequestMapping("/")
  public String greeting(){
    return "Bonjour! Hi! Marhaba! Merhaba!";
  }

}
