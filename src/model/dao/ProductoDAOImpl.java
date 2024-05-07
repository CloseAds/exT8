package model.dao;

import datasource.DataSource;
import model.entities.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImpl extends BaseDao implements ProductoDAO {

    private Connection connection;

    public ProductoDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Producto> findAll() {

        List<Producto> productos = new ArrayList<Producto>();

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from producto order by codigo_producto";
            ps = connection.prepareStatement(sql);

            rs = ps.executeQuery();

            while(rs.next()){
                Producto producto = new Producto();
                producto.setCodigo_producto(rs.getInt("codigo del producto"));
                producto.setDescripcion(rs.getString("descripción"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
                productos.add(producto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSource.cerrarStatement(ps);
            DataSource.cerrarResulSet(rs);
        }
        return productos;
    }

    @Override
    public List<Producto> findByCriteria(String descripcion, double precio) {
        List<Producto> productos = new ArrayList<Producto>();

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from producto where descripcion = ? and precio = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, descripcion);
            ps.setDouble(2, precio);

            rs = ps.executeQuery();


            while(rs.next()){
                Producto producto = new Producto();
                producto.setCodigo_producto(rs.getInt("codigo del producto"));
                producto.setDescripcion(rs.getString("descripción"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
                productos.add(producto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSource.cerrarStatement(ps);
            DataSource.cerrarResulSet(rs);
        }
        return productos;
    }

    @Override
    public Producto findById(String codigo_producto) {

        Producto producto = null;

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from cliente where codigo_producto = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1 , Integer.parseInt(codigo_producto));

            rs = ps.executeQuery();


            if(rs.next()){
                producto = new Producto();
                producto.setCodigo_producto(rs.getInt("codigo del producto"));
                producto.setDescripcion(rs.getString("descripción"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSource.cerrarStatement(ps);
            DataSource.cerrarResulSet(rs);
        }
        return producto;
    }

    @Override
    public Producto save(Producto producto) {

        PreparedStatement ps = null;

        try {
            String sql = "insert into Cliente (codigo_nombre, descripcion, precio, stock) " +
                    " values (?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, producto.getCodigo_producto());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());

            int fila = ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSource.cerrarStatement(ps);
        }
        return producto;
    }

    @Override
    public void update(Producto producto) {

        PreparedStatement ps = null;

        try {
            String sql = "update producto set descripcion = ?, precio = ?, stock = ?" +
                    "where codigo_postal = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, producto.getDescripcion());
            ps.setDouble(2, producto.getPrecio());
            ps.setInt(3, producto.getStock());
            ps.setInt(4, producto.getCodigo_producto());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSource.cerrarStatement(ps);
        }
    }

    @Override
    public void delete(Producto producto) {
        PreparedStatement ps = null;

        try {
            String sql = "delete from producto where codigo_producto = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, producto.getCodigo_producto());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSource.cerrarStatement(ps);
        }
    }
}