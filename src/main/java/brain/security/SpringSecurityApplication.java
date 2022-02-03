package brain.security; 

import java.text.DateFormat;
import java.text.SimpleDateFormat;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import brain.security.Dao.UserRepository;
import brain.security.Model.UserSecurity;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = brain.security.Dao.UserRepository.class)
public class SpringSecurityApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	/*	ApplicationContext ctxt = SpringApplication.run(SpringSecurityApplication.class, args);*/
		/*ApplicationContext ctxt = SpringApplication.run(SpringSecurityApplication.class, args);
		UserRepository ur = ctxt.getBean(UserRepository.class);
		DateFormat d1 = new SimpleDateFormat("yyyy/MM/dd"); 
		UserSecurity u = new UserSecurity();
		u.setId(1);
		u.setRoles("ROLE_ADMIN");
		u.setRoles("ROLE_ADMIN");
		u.setPassword("pass0022");
		u.setActive(true);
		u.setUserName("ADMIN");
		
		UserSecurity ad = new UserSecurity(); 
		ad.setId(2);
		ad.setRoles("ROLE_USER");
		ad.setRoles("USER");
		ad.setPassword("pass1986");
		ad.setActive(true);
		ad.setUserName("USER");
		ur.save(u);
		ur.save(ad);*/
	}
	
	@Bean
	   public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	   }
	
	

}
