package my.com.cmg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PhiShqProjectJobsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhiShqProjectJobsApplication.class, args);
		
		System.out.println("application started..");
	}
	
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	


}
