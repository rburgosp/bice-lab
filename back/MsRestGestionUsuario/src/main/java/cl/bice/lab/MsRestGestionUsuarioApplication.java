package cl.bice.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication// (exclude = {org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class })
public class MsRestGestionUsuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsRestGestionUsuarioApplication.class, args);
	}

}
