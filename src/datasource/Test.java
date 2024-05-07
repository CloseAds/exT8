package datasource;

import java.sql.Connection;
import java.sql.SQLException;

public class Test {

    public static void main(String[] args) throws SQLException {

       Connection connection = DataSource.obtenerConexion();

       if (!connection.isClosed()){
           System.out.println("Conexión realizada correcatamente");
       }

       connection.close();

       if (connection.isClosed()){
           System.out.println("Conexión cerrada correctamente");
       }

    }

}
