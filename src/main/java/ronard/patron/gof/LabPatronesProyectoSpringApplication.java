package ronard.patron.gof;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class LabPatronesProyectoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabPatronesProyectoSpringApplication.class, args);
	}
}
