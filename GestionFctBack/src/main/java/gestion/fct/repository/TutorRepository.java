package gestion.fct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gestion.fct.model.Tutor;
import java.util.List;


@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long>{
	List<Tutor> findByNombreCompletoContaining(String nombreCompleto);
	
}
