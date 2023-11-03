package py.edu.ucom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import py.edu.ucom.entities.Venta;
import py.edu.ucom.entities.VentaDetalle;

public interface VentaDetalleRepository extends JpaRepository<VentaDetalle,Integer> {

}
