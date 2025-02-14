package gestion.fct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gestion.fct.model.Fecha;
import java.time.LocalDate;

@Repository
public interface FechasRepository extends JpaRepository<Fecha, Long>{
	
	public List<Fecha> findByFechaBetween(LocalDate inicio, LocalDate fin);
}
