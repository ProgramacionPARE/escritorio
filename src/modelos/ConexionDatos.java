
package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public  class ConexionDatos {
    private static ConexionDatos CONEXION_DATOS = new ConexionDatos();
    private  Connection connection ;
    private ConexionDatos(){
       

    }

    public static ConexionDatos getInstance(){
        return CONEXION_DATOS;
    }
    
    public Connection getConnection() {
         try {
            Class.forName("com.mysql.jdbc.Driver");
            String servidor = "jdbc:mysql://localhost/paredatos";
            String usuarioDB="root";
            String passwordDB="#parePROGRAMACIONdb";
            this.connection = DriverManager.getConnection(servidor,usuarioDB,passwordDB);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConexionDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    void cerrarConexion() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
}

