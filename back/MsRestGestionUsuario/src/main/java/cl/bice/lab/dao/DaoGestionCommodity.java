package cl.bice.lab.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.bice.lab.dto.DtoRespuestaCrud;
import cl.bice.lab.dto.DtoUsuario;
import cl.bice.lab.entity.Commodity;
import cl.bice.lab.repository.CommodityRepository;


@Service
public class DaoGestionCommodity {
	private static final Logger logger = LoggerFactory.getLogger(DaoGestionCommodity.class);

	@Autowired
	CommodityRepository commodityRepo;

	public DtoRespuestaCrud guardarCommodity(DtoUsuario usuario)	{

		DtoRespuestaCrud respuestaCrud=new DtoRespuestaCrud();
		try {
			Commodity commodity=new Commodity();	
			commodity.setRutMantisa(usuario.getRutMantisa());
			commodity.setTotalUsd(usuario.getTotalCommodityUsd());
			commodity.setTotalClp(usuario.getTotalCommodityClp());	
			commodityRepo.save(commodity);
			logger.info("Commodity Guardao");
			respuestaCrud.setMensajeTransaccion("Insercion Commodity Correcta");
			respuestaCrud.setRespuestaTransaccion(true);

			List<DtoUsuario> con=consultaCommodityTodos();
			for(DtoUsuario c:con ) {
				logger.info("save rut "+c.getRutMantisa());
				logger.info("save clp "+c.getTotalCommodityClp());
				logger.info("save usd "+c.getTotalCommodityUsd());
			}
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


	public List<DtoUsuario> consultaCommodityTodos() {
		List<DtoUsuario> stListDtoUsuario= new ArrayList<DtoUsuario>();
		try {

			List<Commodity> stListCommodity= new ArrayList<Commodity>();
			commodityRepo.findAll().forEach(stListCommodity::add); 

			for(Commodity commodity:stListCommodity) {
				DtoUsuario dtoUsuario=new DtoUsuario();
				dtoUsuario.setRutMantisa(commodity.getRutMantisa());
				dtoUsuario.setTotalCommodityClp(commodity.getTotalClp());
				dtoUsuario.setTotalCommodityUsd(commodity.getTotalUsd());
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


	public DtoUsuario obtenerCommodityId(int idUsuario) {

		DtoUsuario dtoUsuario=new DtoUsuario();
		try {
			Commodity commodity= commodityRepo.findCommodityByRut(idUsuario); 
			if(commodity!=null) {
				dtoUsuario.setRutMantisa(commodity.getRutMantisa());
				dtoUsuario.setTotalCommodityClp(commodity.getTotalClp());
				dtoUsuario.setTotalCommodityUsd(commodity.getTotalUsd());

				logger.info("Coomodity Rut "+commodity.getRutMantisa());
				logger.info("Coomodity CLp "+commodity.getTotalClp());
				logger.info("Coomodity Usd "+commodity.getTotalUsd());
			}
			logger.info("Coomodity Rut "+idUsuario);


		}catch(Exception e) {
			logger.error("Error en Transaccion " +e);
			for(StackTraceElement st:e.getStackTrace()) {
				logger.error("Error en Transaccion " +st);
			}		
		}
		return dtoUsuario;
	}

	public DtoRespuestaCrud actualizarCommodity(int idUser,DtoUsuario usuario) {

		DtoRespuestaCrud respuestaCrud=new DtoRespuestaCrud();
		try {

			Commodity commodityUpdate = commodityRepo.findCommodityByRut(idUser);
			if(commodityUpdate!=null) {		
				commodityUpdate.setTotalClp(usuario.getTotalCommodityClp());	
				commodityUpdate.setTotalUsd(usuario.getTotalCommodityUsd());
				commodityRepo.save(commodityUpdate);

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

	public DtoRespuestaCrud eliminarCommodity(int idUser) {

		DtoRespuestaCrud respuestaCrud=new DtoRespuestaCrud();
		try {

			Commodity commodityDelete = commodityRepo.findCommodityByRut(idUser);
			if(commodityDelete!=null) {		
				commodityRepo.deleteById(commodityDelete.getId());
				respuestaCrud.setMensajeTransaccion("Registro "+idUser+" Eliminado");
				respuestaCrud.setRespuestaTransaccion(true);
				logger.info("Commodity para Rut: "+idUser+" Eliminado.");
			}else {
				logger.info("Commodity para Rut: "+idUser+" No encontrado para Eliminar.");
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
