package gestion.fct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gestion.fct.model.Registro;
@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long>{

}
