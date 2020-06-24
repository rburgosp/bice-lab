package cl.bice.lab.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.bice.lab.dao.DaoGestionCommodity;
import cl.bice.lab.dao.DaoGestionPersona;
import cl.bice.lab.dto.DtoRegistrosDetalle;
import cl.bice.lab.dto.DtoRespuestaCrud;
import cl.bice.lab.dto.DtoUsuario;
import cl.bice.lab.util.ApiError;


@RestController
@RequestMapping(path="/service/v1")
public class ControllerGestionUsuario {

	private static final Logger logger = LoggerFactory.getLogger(ControllerGestionUsuario.class);

	@Autowired ObjectMapper mapper;
	@Autowired DaoGestionPersona servicioPersona;
	@Autowired DaoGestionCommodity servicioCommodity;

	private static final String HEADER_NAME="detalle_ejecucion";
	private static final String HEADER_VALUE="exitosa";
	private static final String MSJ_ERROR="Error transaccion usuario";

	//obtener Usario por Rut
	@CrossOrigin
	@GetMapping(path="/findUserbyIdUser/{idUser}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerUsuariosId(@PathVariable(name="idUser") int idUser) throws JsonProcessingException {


		String jsonOutput = null;
		HttpStatus statusResponse=HttpStatus.OK;
		String headerName=HEADER_NAME;
		String headerValue=HEADER_VALUE;

		try {

			DtoUsuario usurio=servicioPersona.obtenerUsuarioId(idUser);
			//Pretty Print JSON
			jsonOutput = mapper.writeValueAsString(usurio);

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


	//obtener Usarios Todos
	@CrossOrigin
	@GetMapping(path="/findUserAll",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerUsuariosTodos() throws JsonProcessingException {


		String jsonOutput = null;
		HttpStatus statusResponse=HttpStatus.OK;
		String headerName=HEADER_NAME;
		String headerValue=HEADER_VALUE;

		try {

			List<DtoUsuario> usuariosTodos=servicioPersona.consultaClienteTodos();
			//Pretty Print JSON

			jsonOutput = mapper.writeValueAsString(usuariosTodos);

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

	//Crear un Usuario
	@CrossOrigin
	@PostMapping(path="/createUser",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> crearUsuario (@RequestBody DtoUsuario valorUsuario) throws JsonProcessingException {
		String jsonOutput = null;
		HttpStatus statusResponse=HttpStatus.OK;
		String headerName=HEADER_NAME;
		String headerValue=HEADER_VALUE;

		try {
			logger.info("RutMantisa: "+valorUsuario.getRutMantisa());
			logger.info("RutDv: "+valorUsuario.getRutDv());
			logger.info("Nombre: "+valorUsuario.getNombre());
			logger.info("Apellido: "+valorUsuario.getApellido());
			logger.info("Email: "+valorUsuario.getEmail());
			logger.info("FechaNacimiento: "+valorUsuario.getFechaNacimiento());

			DtoRespuestaCrud dtoRetoroCrud=servicioPersona.crearPersona(valorUsuario);	

			//Pretty Print JSON
			jsonOutput = mapper.writeValueAsString(dtoRetoroCrud);

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

	//Eliminar un usuario
	@CrossOrigin
	@DeleteMapping(path="/deleteUser/{idUser}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> eliminarUsuario (@PathVariable(name="idUser") int idUser) throws JsonProcessingException {


		String jsonOutput = null;
		HttpStatus statusResponse=HttpStatus.OK;
		String headerName=HEADER_NAME;
		String headerValue=HEADER_VALUE;
		try {
			DtoRespuestaCrud dtoRetorno=servicioPersona.eliminarPersona(idUser);	

			//Pretty Print JSON
			jsonOutput = mapper.writeValueAsString(dtoRetorno);

		}catch(JsonProcessingException e) {
			statusResponse= HttpStatus.INTERNAL_SERVER_ERROR;
			headerValue=e.getMessage();
			ApiError appiError=new ApiError();
			appiError.setStatus(statusResponse);
			appiError.setMessage(e.getMessage());	
			//Pretty Print JSON
			jsonOutput = mapper.writeValueAsString(appiError);

			logger.error("Error " +e);
			for(StackTraceElement st:e.getStackTrace()) {
				logger.error("Error " +st);
			}

		}

		return ResponseEntity.status(statusResponse).header(headerName,headerValue).body(jsonOutput); 

	}
	//Actualizar parcialmente registros usuarios
	@CrossOrigin
	@PatchMapping(path="/updateUser/{idUser}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> actualizarUsuario(@PathVariable (name="idUser") int idUser,
			@RequestBody DtoUsuario valorUsuario) throws JsonProcessingException {


		String jsonOutput = null;
		HttpStatus statusResponse=HttpStatus.OK;
		String headerName=HEADER_NAME;
		String headerValue=HEADER_VALUE;
		try {


			DtoRespuestaCrud dtoRetoroCrud=servicioPersona.actualizarPersona(idUser,valorUsuario);	

			logger.info("RutMantisa: "+valorUsuario.getRutMantisa());
			logger.info("RutDv: "+valorUsuario.getRutDv());
			logger.info("Nombre: "+valorUsuario.getNombre());
			logger.info("Apellido: "+valorUsuario.getApellido());
			logger.info("Email: "+valorUsuario.getEmail());
			logger.info("FechaNacimiento: "+valorUsuario.getFechaNacimiento());

			//Pretty Print JSON
			jsonOutput = mapper.writeValueAsString(dtoRetoroCrud);

		}catch(Exception e) {


			statusResponse= HttpStatus.INTERNAL_SERVER_ERROR;
			headerValue=e.getMessage();
			ApiError appiError=new ApiError();
			appiError.setStatus(statusResponse);
			appiError.setMessage(e.getMessage());	
			//Pretty Print JSON
			jsonOutput = mapper.writeValueAsString(appiError);

			logger.error("Error " +e);
			for(StackTraceElement st:e.getStackTrace()) {
				logger.error("Error " +st);
			}

		}

		return ResponseEntity.status(statusResponse).header(headerName,headerValue).body(jsonOutput); 

	}

	//Almacenar Commodity Usuario
	@CrossOrigin
	@PostMapping(path="/saveBuyCommodity",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> guardarCompraCommodity (@RequestBody DtoUsuario valorUsuario) throws JsonProcessingException {
		String jsonOutput = null;
		HttpStatus statusResponse=HttpStatus.OK;
		String headerName=HEADER_NAME;
		String headerValue=HEADER_VALUE;

		try {
			logger.info("RutMantisa: "+valorUsuario.getRutMantisa());
			logger.info("RutDv: "+valorUsuario.getRutDv());
			logger.info("Nombre: "+valorUsuario.getNombre());
			logger.info("Apellido: "+valorUsuario.getApellido());
			logger.info("Email: "+valorUsuario.getEmail());
			logger.info("FechaNacimiento: "+valorUsuario.getFechaNacimiento());
			logger.info("Total Usd: "+valorUsuario.getTotalCommodityUsd());
			logger.info("Total Clp: "+valorUsuario.getTotalCommodityClp());
			DtoRespuestaCrud dtoRetoroCrud;
			if(servicioCommodity.obtenerCommodityId(valorUsuario.getRutMantisa()).getRutMantisa()==0) {
				dtoRetoroCrud=servicioCommodity.guardarCommodity(valorUsuario);	
			}else {
				dtoRetoroCrud=servicioCommodity.actualizarCommodity(valorUsuario.getRutMantisa(), valorUsuario);	
			}

			//Pretty Print JSON
			jsonOutput = mapper.writeValueAsString(dtoRetoroCrud);
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

	//obtener Usarios Todos
	@CrossOrigin
	@GetMapping(path="/findUserAllForGrid",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerUsuariosTodosGrilla() throws JsonProcessingException {


		String jsonOutput = null;
		HttpStatus statusResponse=HttpStatus.OK;
		String headerName=HEADER_NAME;
		String headerValue=HEADER_VALUE;

		try {
			DtoRegistrosDetalle registroDetalle=new DtoRegistrosDetalle();
			List<DtoUsuario> usuariosTodos=servicioPersona.consultaClienteTodos();
			registroDetalle.setData(usuariosTodos);
			registroDetalle.setTotal(usuariosTodos.size());
			//Pretty Print JSON

			jsonOutput = mapper.writeValueAsString(registroDetalle);

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
