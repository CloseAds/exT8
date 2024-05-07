package model.dao;

import datasource.DataSource;

public class DAOFactory {

    public static ProductoDAO createProductoDAO(){
        return new ProductoDAOImpl(DataSource.obtenerConexion());
    }

}
