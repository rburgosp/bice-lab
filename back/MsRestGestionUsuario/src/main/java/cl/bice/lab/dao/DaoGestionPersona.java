package cl.bice.lab.dao;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.bice.lab.dto.DtoRespuestaCrud;
import cl.bice.lab.dto.DtoUsuario;
import cl.bice.lab.entity.Persona;
import cl.bice.lab.repository.PersonaRepository;

@Service
public class DaoGestionPersona {

	private static final Logger logger = LoggerFactory.getLogger(DaoGestionPersona.class);

	@Autowired
	PersonaRepository personaRepository;
	@Autowired
	DaoGestionCommodity daoCommodity;
	public DtoRespuestaCrud crearPersona(DtoUsuario usuario)	{

		DtoRespuestaCrud respuestaCrud=new DtoRespuestaCrud();
		try {
			Persona persona=new Persona();	
			persona.setRutMantisa(usuario.getRutMantisa());
			persona.setRutDv(usuario.getRutDv());
			persona.setNombre(usuario.getNombre());
			persona.setApellido(usuario.getApellido());
			persona.setEmail(usuario.getEmail());


			personaRepository.save(persona);
			respuestaCrud.setMensajeTransaccion("Insercion Correcta");
			respuestaCrud.setRespuestaTransaccion(true);
		}catch(Exception e) {
			logger.error("Error en Transaccion " +e);
			for(StackTraceElement st:e.getStackTrace()) {
				logger.error("Error en Transaccion " +st);
			}
			respuestaCrud.setMensajeTransaccion("Error Transaccion "+e.getMessage());
			respuestaCrud.setRespuestaTransaccion(false);	
		}
		return respuestaCrud;
	}

	public List<DtoUsuario> consultaClienteTodos() {
		List<DtoUsuario> stListDtoUsuario= new ArrayList<DtoUsuario>();
		try {

			List<Persona> stListPersona= new ArrayList<Persona>();
			personaRepository.findAll().forEach(stListPersona::add); 
		
			for(Persona persona:stListPersona) {
				DtoUsuario datosCommodity=daoCommodity.obtenerCommodityId(persona.getRutMantisa());
				DtoUsuario dtoUsuario=new DtoUsuario();
				dtoUsuario.setRutMantisa(persona.getRutMantisa());
				dtoUsuario.setRutDv(persona.getRutDv());
				dtoUsuario.setNombre(persona.getNombre());
				dtoUsuario.setApellido(persona.getApellido());
				dtoUsuario.setEmail(persona.getEmail());
				dtoUsuario.setTotalCommodityClp(datosCommodity.getTotalCommodityClp());
				dtoUsuario.setTotalCommodityUsd(datosCommodity.getTotalCommodityUsd());
				 	
				stListDtoUsuario.add(dtoUsuario);
			}

		}catch(Exception e) {
			logger.error("Error en Transaccion " +e);
			for(StackTraceElement st:e.getStackTrace()) {
				logger.error("Error en Transaccion " +st);
			}		
		}

		return stListDtoUsuario;

	}

	public DtoUsuario obtenerUsuarioId(int idUsuario) {

		DtoUsuario dtoUsuario=new DtoUsuario();
		try {
			Persona persona= personaRepository.findByRut(idUsuario)	; 
			if(persona!=null) {

				DtoUsuario datosCommodity=new DaoGestionCommodity().obtenerCommodityId(persona.getRutMantisa());
				dtoUsuario.setRutMantisa(persona.getRutMantisa());
				dtoUsuario.setRutDv(persona.getRutDv());
				dtoUsuario.setNombre(persona.getNombre());
				dtoUsuario.setApellido(persona.getApellido());
				dtoUsuario.setEmail(persona.getEmail());
				dtoUsuario.setTotalCommodityClp(datosCommodity.getTotalCommodityClp());
				dtoUsuario.setTotalCommodityUsd(datosCommodity.getTotalCommodityUsd());

			}
		}catch(Exception e) {
			logger.error("Error en Transaccion " +e);
			for(StackTraceElement st:e.getStackTrace()) {
				logger.error("Error en Transaccion " +st);
			}		
		}
		return dtoUsuario;
	}

	public DtoRespuestaCrud actualizarPersona(int idUser,DtoUsuario usuario) {

		DtoRespuestaCrud respuestaCrud=new DtoRespuestaCrud();
		try {

			Persona personaUpdate = personaRepository.findByRut(idUser);
			if(personaUpdate!=null) {		
				personaUpdate.setNombre(usuario.getNombre());
				personaUpdate.setApellido(usuario.getApellido());
				personaUpdate.setEmail(usuario.getEmail());

				personaRepository.save(personaUpdate);

				respuestaCrud.setMensajeTransaccion("Registro "+idUser+" Actualizado");
				respuestaCrud.setRespuestaTransaccion(true);
			}else {
				respuestaCrud.setMensajeTransaccion("Registro "+idUser+" No Encontrado");
				respuestaCrud.setRespuestaTransaccion(true);
			}
		} catch(Exception e) {
			logger.error("Error en Transaccion " +e);
			for(StackTraceElement st:e.getStackTrace()) {
				logger.error("Error en Transaccion " +st);
			}
			respuestaCrud.setMensajeTransaccion("Error Transaccion "+e.getMessage());
			respuestaCrud.setRespuestaTransaccion(false);		
		}

		return respuestaCrud;
	}

	public DtoRespuestaCrud eliminarPersona(int idUser) {

		DtoRespuestaCrud respuestaCrud=new DtoRespuestaCrud();
		try {

			Persona personaDelete = personaRepository.findByRut(idUser);
			if(personaDelete!=null) {		
				personaRepository.deleteById(personaDelete.getId());
				daoCommodity.eliminarCommodity(idUser);
				respuestaCrud.setMensajeTransaccion("Registro "+idUser+" Eliminado");
				respuestaCrud.setRespuestaTransaccion(true);
			}else {
				respuestaCrud.setMensajeTransaccion("Registro "+idUser+" No Encontrado");
				respuestaCrud.setRespuestaTransaccion(true);
			}
		} catch(Exception e) {
			logger.error("Error en Transaccion " +e);
			for(StackTraceElement st:e.getStackTrace()) {
				logger.error("Error en Transaccion " +st);
			}
			respuestaCrud.setMensajeTransaccion("Error Transaccion "+e.getMessage());
			respuestaCrud.setRespuestaTransaccion(false);		
		}

		return respuestaCrud;
	}

}
