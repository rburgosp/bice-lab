package cl.bice.lab.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import cl.bice.lab.entity.Commodity;




public interface CommodityRepository  extends CrudRepository<Commodity, Long> {
	

	@Transactional(readOnly = true) 
	@Query(value="SELECT * FROM Commodity c WHERE c.rut_mantisa =?1 ",  nativeQuery = true) 
	Commodity findCommodityByRut(int idUsuario); 
}
