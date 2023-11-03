/*package py.edu.ucom.services;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import py.edu.ucom.config.IDAO;
import py.edu.ucom.entities.Cliente;

@ApplicationScoped
public class ClienteService implements IDAO<Cliente, Integer>{

    @Override
    public Cliente obtener(Integer param) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtener'");
    }

    @Override
    public Cliente agregar(Cliente param) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'agregar'");
    }

    @Override
    public Cliente modificar(Cliente param) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public void eliminar(Integer param) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

    @Override
    public List<Cliente> listar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listar'");
    }
    
}
*/
package py.edu.ucom.services;

import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import py.edu.ucom.config.IDAO;
import py.edu.ucom.entities.Cliente;
import py.edu.ucom.repositories.ClienteRepository;

@ApplicationScoped
public class ClienteService implements IDAO<Cliente, Integer> {

    @Inject
    private ClienteRepository repository;

    @Override
    public Cliente obtener(Integer clienteId) {
        return repository.findById(clienteId).orElse(null);
    }

    @Override
    public Cliente agregar(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public Cliente modificar(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public void eliminar(Integer clienteId) {
        repository.deleteById(clienteId);
    }

    @Override
    public List<Cliente> listar() {
        return repository.findAll();
    }

    // Puedes agregar métodos adicionales aquí según tus necesidades, como buscar por nombre, apellido, etc.
}
