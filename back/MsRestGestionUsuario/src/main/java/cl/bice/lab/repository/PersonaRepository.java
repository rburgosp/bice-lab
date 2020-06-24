package cl.bice.lab.repository;




import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import cl.bice.lab.entity.Persona;


public interface PersonaRepository extends CrudRepository<Persona, Long>{
	@Transactional(readOnly = true) 
	@Query(value="SELECT * FROM Persona p WHERE p.rut_mantisa =?1 ",  nativeQuery = true) 
	Persona findByRut(int idUsuario); 
	
	


}
