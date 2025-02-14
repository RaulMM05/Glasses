package gestion.fct.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gestion.fct.model.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Long>{

}
