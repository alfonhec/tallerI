package py.edu.ucom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import py.edu.ucom.entities.MetodoPago;

public interface MetodoPagoRepository extends JpaRepository<MetodoPago,Integer> {
     List<MetodoPago> findByCodigo(String codigo);

     @Query("SELECT SUM(m.metodoPagoId) FROM MetodoPago m")
     Long sumId();
 
     /**
     * @return
     */
        //@Query(nativeQuery = true, value = "select sum(metodo_pago_id) from metodo_pago mp ")
     //Long sumIdNative();
}
