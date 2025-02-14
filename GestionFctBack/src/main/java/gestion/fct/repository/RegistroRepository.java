package gestion.fct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gestion.fct.model.Alumno;
import gestion.fct.model.Registro;
import java.util.List;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long>{
	
	public List<Registro> findByAlumno(Alumno alumno);
}
