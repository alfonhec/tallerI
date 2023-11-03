package py.edu.ucom.controllers;

import java.util.List;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import py.edu.ucom.entities.Venta;
import py.edu.ucom.entities.dto.ResumenVentaDTO;
import py.edu.ucom.services.VentaService;

@Path("/venta")
public class VentaResource {
    
    @Inject
    public VentaService service;

    @GET
    public List<Venta> listar(){
        return this.service.listar();
    }
    @DELETE
    @Path("{id}")
    public void eliminar(Integer id) {
        this.service.eliminar(id);
    }
    @POST
    public Venta agregar (Venta param){
        return this.service.agregar(param);
    }
    @PUT
    public Venta modificar (Venta param){
        return this.service.modificar(param);
    }
    @GET
    @Path("{id}")
    public Venta obtener(@PathParam("id")Integer param){
        return this.service.obtener(param);     
    }
    @GET
    @Path("resumen/{id}")
    public ResumenVentaDTO obtenerResumen(@PathParam("id")Integer param){
        return this.service.obtenerResumen(param);     
    }
}

/*
package py.edu.ucom.controllers;

import java.util.List;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import py.edu.ucom.entities.Venta;
import py.edu.ucom.entities.dto.ResumenVentaDTO;
import py.edu.ucom.services.VentaService;
import jakarta.ws.rs.core.MediaType;
import py.edu.ucom.entities.dto.Venta; // Asegúrate de importar Venta
import javax.ws.rs.Path;

@Path("/venta")
public class VentaResource {
    
    @Inject
    public VentaService service;

    @GET
    public List<Venta> listar() {
        return this.service.listar();
    }
    
    @DELETE
    @Path("{id}")
    public void eliminar(@PathParam("id") Integer id) {
        this.service.eliminar(id);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Venta agregar(Venta param) {
        return this.service.agregar(param);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Venta modificar(Venta param) {
        return this.service.modificar(param);
    }
    
    @GET
    @Path("{id}")
    public Venta obtener(@PathParam("id") Integer param) {
        return this.service.obtener(param);
    }
    
    @GET
    @Path("resumen/{id}")
    public ResumenVentaDTO obtenerResumen(@PathParam("id") Integer param) {
        return this.service.obtenerResumen(param);
    }

    @POST
    @Path("generar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Venta generarVenta(Venta parametros) {
        return this.service.generarVenta(parametros);
    }
}
*/