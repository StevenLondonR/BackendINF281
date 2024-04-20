package Backend.BackendINF281.Inventario.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Backend.BackendINF281.Inventario.Models.Producto;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {

    Optional<Producto> findByIdproducto(Integer idproducto);
    
}
