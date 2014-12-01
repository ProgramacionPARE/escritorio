package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    private  Connection connection;
    //private static int no = 0;
    
    private Conexion() {
    }

    public static Conexion getInstance() {
        //no++;
       
        return  new Conexion();
       
    }

    public  Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String servidor = "jdbc:mysql://" + Configuracion.getInstancia().getIp() + "/paredb";
            String usuarioDB = "root";
            String passwordDB = "#parePROGRAMACIONdb";
            
            this.connection = DriverManager.getConnection(servidor, usuarioDB, passwordDB);

          
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        } 
        return this.connection;
    }

    public  void setConnection(Connection connection) {
        this.connection = connection;
    }
    


//    @Override
//    protected void finalize() throws Throwable {
//        super.finalize(); //To change body of generated methods, choose Tools | Templates.
//         no--;
//    }
    
    public void cerrarConexion(){
        try {
            this.connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
