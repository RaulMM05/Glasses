package gestion.fct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gestion.fct.model.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
