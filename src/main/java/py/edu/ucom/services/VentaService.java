package py.edu.ucom.services;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import py.edu.ucom.config.IDAO;
import py.edu.ucom.entities.Cliente;
import py.edu.ucom.entities.Venta;
import py.edu.ucom.entities.VentaDetalle;
import py.edu.ucom.entities.dto.ResumenVentaDTO;
import py.edu.ucom.entities.dto.VentaDetalleDTO;
import py.edu.ucom.repositories.VentaDetalleRepository;
import py.edu.ucom.repositories.VentaRepository;

@ApplicationScoped
public class VentaService implements IDAO<Venta,Integer> {
    private static final Logger LOG = Logger.getLogger(VentaService.class);
    @Inject
    private VentaRepository repository;


    @Inject
    private VentaDetalleRepository repositoryDetalle;
    @Override
    public Venta obtener(Integer param) {
        // TODO Auto-generated method stub
        return this.repository.findById(param).orElse(null);
    }

    @Override
    @Transactional
    public Venta agregar(Venta param) {

        try{
            LOG.info(param);
            
            Venta aux = new Venta();
            aux.setClienteId(param.getClienteId());
            aux.setFecha(param.getFecha());
            aux.setMetodoPagoId(param.getMetodoPagoId());
            aux.setTotal(param.getTotal());

            Venta saved = this.repository.save(aux);
            System.out.println(aux.toString());

            List<VentaDetalle> vdList = param.getVentaDetalleList();
            for(VentaDetalle item: vdList){
                VentaDetalle vdt = new VentaDetalle();
                vdt.setVentaId(saved);
                vdt.setProductoId(item.getProductoId());
                vdt.setSubtotal(item.getSubtotal());

                this.repositoryDetalle.save(vdt);

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return param;
    }

    

    @Override
    public Venta modificar(Venta param) {
        // TODO Auto-generated method stub
        return this.repository.save(param);
    }

    @Override
    public void eliminar(Integer param) {
        // TODO Auto-generated method stub

        this.repository.deleteById(param);
    }

    @Override
    public List<Venta> listar() {
        return this.repository.findAll();
    }
    public ResumenVentaDTO obtenerResumen(Integer ventaId){
        ResumenVentaDTO data = new ResumenVentaDTO();
        Venta v = this.repository.findById(ventaId).orElse(null);
        Cliente clie = v.getClienteId();
        data.setRazonSocial(clie.getNombres()+" "+clie.getApellidos() );
        data.setDocumento(clie.getDocumento());
        data.setFecha(v.getFecha());
        List<VentaDetalleDTO> detalle= new ArrayList<>();
        for(VentaDetalle item : v.getVentaDetalleList()){
                VentaDetalleDTO vdto = new VentaDetalleDTO();
                vdto.setCantidad(item.getCantidad());
                vdto.setSubtotal(item.getSubtotal());
                vdto.setDescripcion( item.getProductoId().getDescripcion());
                detalle.add( vdto);
        }
        data.setDetalle( detalle);

        return data;
    }
    
}



/*
package py.edu.ucom.services;
import org.jboss.logging.Logger;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import py.edu.ucom.config.IDAO;
import py.edu.ucom.entities.Cliente;
import py.edu.ucom.entities.dto.Venta;
import javax.ws.rs.Path;
import py.edu.ucom.entities.VentaDetalle;
import py.edu.ucom.entities.dto.ResumenVentaDTO;
import py.edu.ucom.entities.dto.VentaDetalleDTO;
import py.edu.ucom.repositories.VentaDetalleRepository;
import py.edu.ucom.repositories.VentaRepository;
import py.edu.ucom.entities.Producto; 
import py.edu.ucom.repositories.ProductoRepository; 

@ApplicationScoped
public class VentaService implements IDAO<Venta, Integer> {
    private static final Logger LOG = Logger.getLogger(VentaService.class);
    
    @Inject
    private VentaRepository repository;
    
    @Inject
    private VentaDetalleRepository repositoryDetalle;
    
    @Inject
    private ProductoRepository productoRepository; 

    public Venta obtener(Integer param) {
        return this.repository.findById(param).orElse(null);
    }

    @Transactional
    public Venta agregar(Venta param) {
        try {
            LOG.info(param);
            Venta aux = new Venta();
            aux.setClienteId(param.getClienteId());
            aux.setFecha(param.getFecha());
            aux.setMetodoPagoId(param.getMetodoPagoId());
            aux.setTotal(param.getTotal());

            Venta saved = this.repository.save(aux);
            System.out.println(aux.toString());

            List<VentaDetalle> vdList = param.getVentaDetalleList();
            for (VentaDetalle item : vdList) {
                VentaDetalle vdt = new VentaDetalle();
                vdt.setVentaId(saved);
                vdt.setProductoId(item.getProductoId());
                vdt.setSubtotal(item.getSubtotal());

                this.repositoryDetalle.save(vdt);

                // Actualiza el stock del producto restando la cantidad vendida
                Producto producto = productoRepository.findById(item.getProductoId().getProductoId()).orElse(null);
                if (producto != null) {
                    producto.setStock(producto.getStock() - item.getCantidad());
                    productoRepository.save(producto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return param;
    }

    public Venta modificar(Venta param) {
        return this.repository.save(param);
    }

    public void eliminar(Integer param) {
        this.repository.deleteById(param);
    }

    public List<Venta> listar() {
        return this.repository.findAll();
    }

    public ResumenVentaDTO obtenerResumen(Integer ventaId) {
        ResumenVentaDTO data = new ResumenVentaDTO();
        Venta v = this.repository.findById(ventaId).orElse(null);
        Cliente clie = v.getClienteId();
        data.setRazonSocial(clie.getNombres() + " " + clie.getApellidos());
        data.setDocumento(clie.getDocumento());
        data.setFecha(v.getFecha());
        List<VentaDetalleDTO> detalle = new ArrayList<>();
        for (VentaDetalle item : v.getVentaDetalleList()) {
            VentaDetalleDTO vdto = new VentaDetalleDTO();
            vdto.setCantidad(item.getCantidad());
            vdto.setSubtotal(item.getSubtotal());
            vdto.setDescripcion(item.getProductoId().getDescripcion());
            detalle.add(vdto);
        }
        data.setDetalle(detalle);
        return data;
    }

    @POST
    @Path("generar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Venta generarVenta(Venta parametros) {
        Venta nuevaVenta = new Venta();
        nuevaVenta.setClienteId(parametros.getCliente());
        nuevaVenta.setMetodoPagoId(parametros.getMetodoPago());
        nuevaVenta.setTotal(parametros.getTotal());
        nuevaVenta.setFecha(new Date());

        List<VentaDetalle> detalles = new ArrayList<>();
        for (VentaDetalleDTO detalleDTO : parametros.getDetalles()) {
            VentaDetalle detalle = new VentaDetalle();
            Producto producto = productoRepository.findById(detalleDTO.getProductoId()).orElse(null);

            if (producto != null) {
                detalle.setProductoId(producto);
                detalle.setCantidad(detalleDTO.getCantidad());
                detalle.setSubtotal(detalleDTO.getSubtotal());
                detalles.add(detalle);

                // Actualiza el stock del producto restando la cantidad vendida
                producto.setStock(producto.getStock() - detalleDTO.getCantidad());
                productoRepository.save(producto);
            }
        }

        nuevaVenta.setVentaDetalleList(detalles);
        return this.repository.save(nuevaVenta);
    }
}
*/