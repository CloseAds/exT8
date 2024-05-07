package model.services;

import model.dao.DAOFactory;
import model.dao.ProductoDAO;
import model.entities.Producto;
import model.services.ProductoService;
import model.services.ProductoServiceImpl;

import java.util.List;

public class TestProductoService {
    public static void main(String[] args) {

        ProductoDAO dao = DAOFactory.createProductoDAO();

        List<Producto> all = dao.findAll();
        for (Producto producto: all) {
            System.out.println(producto);
        }

        System.out.println("*******************************Criteria******************************");
        List<Producto> byCriteria = dao.findByCriteria("Producto 1", Double.parseDouble("3.5"));
        for (Producto producto: byCriteria) {
            System.out.println(producto);
        }

        System.out.println("*******************************findById******************************");
        System.out.println(dao.findById("1"));


        System.out.println("*******************************Insert******************************");
        Producto producto = new Producto();
        producto.setCodigo_producto(5);
        producto.setDescripcion("Producto 5");
        producto.setPrecio(4.5);
        producto.setStock(50);
        dao.save(producto);
        System.out.println(producto);

        System.out.println("*******************************Update******************************");
        Producto producto2 = new Producto();
        producto.setCodigo_producto(5);
        producto.setDescripcion("Producto 5");
        producto.setPrecio(7.5);
        producto.setStock(40);
        dao.save(producto2);
        System.out.println(producto2);

        System.out.println("*******************************Delete******************************");
        dao.delete(producto2);

    }
}