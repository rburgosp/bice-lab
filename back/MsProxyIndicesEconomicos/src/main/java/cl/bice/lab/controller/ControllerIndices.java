package cl.bice.lab.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cl.bice.lab.util.ApiError;
import cl.bice.lab.util.HttpCliente;



@RestController
@RequestMapping(path="/service/v1")
public class ControllerIndices {

	private static final Logger logger = LoggerFactory.getLogger(ControllerIndices.class);

	@Autowired ObjectMapper mapper;


	private static final String HEADER_NAME="detalle_ejecucion";
	private static final String HEADER_VALUE="exitosa";
	private static final String MSJ_ERROR="Error transaccion usuario";
	private static final String URI_SERVICIO_COMMODITY = "https://www.indecon.online/date/";		

	//obtener Commodity
	@CrossOrigin
	@GetMapping(path="/findCommodoty/{commodity}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerCommodity(@PathVariable(name="commodity") String commodity) throws JsonProcessingException {


		String jsonOutput = null;
		HttpStatus statusResponse=HttpStatus.OK;
		String headerName=HEADER_NAME;
		String headerValue=HEADER_VALUE;

		try {

			SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date(System.currentTimeMillis());
			logger.info(formatter.format(date));

			StringBuilder uriServicioCommodity=new StringBuilder();
			uriServicioCommodity.append(URI_SERVICIO_COMMODITY)

			.append(commodity)
			.append("/")
			//.append(formatter.format(date));
			.append("28-02-2020");	
			HttpCliente cliente=new HttpCliente();
			Object result=cliente.clienteGet(uriServicioCommodity.toString());
			logger.info(""+result);

			//Pretty Print JSON
			jsonOutput = mapper.writeValueAsString(result);

		}catch(Exception e) {
			statusResponse= HttpStatus.INTERNAL_SERVER_ERROR;
			headerValue=e.getMessage();
			ApiError appiError=new ApiError();
			appiError.setStatus(statusResponse);
			appiError.setMessage(e.getMessage());
			//Pretty Print JSON
			jsonOutput = mapper.writeValueAsString(appiError);

			logger.error(MSJ_ERROR +e);
			for(StackTraceElement st:e.getStackTrace()) {
				logger.error(MSJ_ERROR +st);
			}

		}
		return ResponseEntity.status(statusResponse).header(headerName,headerValue).body(jsonOutput);
	}



	//obtener Commodity Venta
	@CrossOrigin
	@GetMapping(path="/findCommodotyCompra",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerCotizacion() throws JsonProcessingException {


		String jsonOutput = null;
		HttpStatus statusResponse=HttpStatus.OK;
		String headerName=HEADER_NAME;
		String headerValue=HEADER_VALUE;
		List<String> listaCommodity= new ArrayList<String>();
		listaCommodity.add("oro");
		listaCommodity.add("cobre");
		listaCommodity.add("plata");

		try {
			List<Object> indiceEconomico=new ArrayList<Object>();
			SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date(System.currentTimeMillis());
			logger.info(formatter.format(date));

			for(String commodity:listaCommodity) {
				StringBuilder uriServicioCommodity=new StringBuilder();
				uriServicioCommodity.append(URI_SERVICIO_COMMODITY)

				.append(commodity)
				.append("/")
				//.append(formatter.format(date));
				.append("28-02-2020");


				HttpCliente cliente=new HttpCliente();
				Object result=cliente.clienteGet(uriServicioCommodity.toString());
				logger.info("Indice Para "+commodity+" : "+result);
				indiceEconomico.add(result);


			}


			//Pretty Print JSON
			jsonOutput = mapper.writeValueAsString(indiceEconomico);

			logger.info("Listado Idices: "+mapper);

		}catch(Exception e) {
			statusResponse= HttpStatus.INTERNAL_SERVER_ERROR;
			headerValue=e.getMessage();
			ApiError appiError=new ApiError();
			appiError.setStatus(statusResponse);
			appiError.setMessage(e.getMessage());
			//Pretty Print JSON
			jsonOutput = mapper.writeValueAsString(appiError);

			logger.error(MSJ_ERROR +e);
			for(StackTraceElement st:e.getStackTrace()) {
				logger.error(MSJ_ERROR +st);
			}

		}
		return ResponseEntity.status(statusResponse).header(headerName,headerValue).body(jsonOutput);
	}




}

