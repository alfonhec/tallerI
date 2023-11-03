package py.edu.ucom.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import java.util.List;
import jakarta.ws.rs.core.Response;
import py.edu.ucom.entities.Cliente;
import py.edu.ucom.services.ClienteService;

@Path("/cliente")
public class ClienteResource {

    @Inject
    private ClienteService clienteService;

    @GET
    @Path("/{clienteId}")
    public Response obtenerClientePorId(@PathParam("clienteId") Integer clienteId) {
        Cliente cliente = clienteService.obtener(clienteId);

        if (cliente != null) {
            return Response.ok(cliente).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Cliente no encontrado").build();
        }
    }

    @GET
    public Response obtenerTodosLosClientes() {
        List<Cliente> clientes = clienteService.listar();

        if (!clientes.isEmpty()) {
            return Response.ok(clientes).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("No se encontraron clientes").build();
        }
    }

    @POST
    public Response agregarCliente(Cliente cliente) {
        Cliente nuevoCliente = clienteService.agregar(cliente);
        return Response.status(Response.Status.CREATED).entity(nuevoCliente).build();
    }

    @DELETE
    @Path("/{clienteId}")
    public Response eliminarCliente(@PathParam("clienteId") Integer clienteId) {
        clienteService.eliminar(clienteId);
        return Response.noContent().build();
    }
}
