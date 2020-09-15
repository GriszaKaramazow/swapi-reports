package pl.softwareplant.swapireports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("pl.softwareplant.swapireports")
public class SwapiReportsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwapiReportsApplication.class, args);
	}

}
