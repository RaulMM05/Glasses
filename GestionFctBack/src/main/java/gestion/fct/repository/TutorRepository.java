package gestion.fct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gestion.fct.model.Tutor;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long>{

}
