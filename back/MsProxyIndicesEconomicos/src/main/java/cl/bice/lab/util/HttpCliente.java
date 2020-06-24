package cl.bice.lab.util;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;



public class HttpCliente {

	private static final Logger logger = LoggerFactory.getLogger(HttpCliente.class);

	private static final String MSJ_ERROR="Error llamada servicio";

	public Object clienteGet(String urlServicio) {

		try {
			logger.info("Conectando a Servicio: "+urlServicio);

			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ResponseEntity<?>  response = restTemplate.exchange(urlServicio, HttpMethod.GET,entity,Object.class);
			
			logger.info("Exito en llamada a Servicio ");
			
			return response.getBody();
			
		}catch(Exception e) {
			logger.error(MSJ_ERROR +e);
			for(StackTraceElement st:e.getStackTrace()) {
				logger.error(MSJ_ERROR +st);
			}
			return e;
		}

	}

}
