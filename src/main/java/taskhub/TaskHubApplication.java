package taskhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskHubApplication {

	public String PORT = System.getenv("PORT");
	public static void main(String[] args) {
		SpringApplication.run(TaskHubApplication.class, args);
	}

}
