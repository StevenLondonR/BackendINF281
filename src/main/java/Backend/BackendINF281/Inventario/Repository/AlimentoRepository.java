package Backend.BackendINF281.Inventario.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Backend.BackendINF281.Inventario.Models.Alimento;

public interface AlimentoRepository extends JpaRepository<Alimento,Integer>{
    Optional<Alimento> findByIdalimento(Integer idAlimento);
}
