package model.dao;

import model.entities.Producto;

import java.util.List;

public interface ProductoDAO {

    List<Producto> findAll();
    List<Producto> findByCriteria(String descripcion, double precio);
    Producto findById(String codigo_producto);
    Producto save(Producto Producto);
    void update(Producto Producto);
    void delete(Producto Producto);

}
