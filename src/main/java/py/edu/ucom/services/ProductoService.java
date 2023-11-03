package py.edu.ucom.services;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import py.edu.ucom.config.IDAO;
import py.edu.ucom.entities.Producto;
import py.edu.ucom.repositories.ProductoRepository;

@ApplicationScoped
public class ProductoService implements IDAO<Producto,Integer> {
    @Inject
    private ProductoRepository repository;

    @Override
    public Producto obtener(Integer param) {
        // TODO Auto-generated method stub
        return this.repository.findById(param).orElse(null);
    }

    @Override
    public Producto agregar(Producto param) {
        // TODO Auto-generated method stub
        return this.repository.save(param);
    }

    @Override
    public Producto modificar(Producto param) {
        // TODO Auto-generated method stub
        return this.repository.save(param);
    }

    @Override
    public void eliminar(Integer param) {
        // TODO Auto-generated method stub

        this.repository.deleteById(param);
    }

    @Override
    public List<Producto> listar() {
        return this.repository.findAll();
    }
    
}
