package py.edu.ucom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import py.edu.ucom.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {

}
