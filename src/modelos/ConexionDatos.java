
package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



public  class ConexionDatos {
      private static Connection conexion=null;
    
    
    public static Connection getConnectionDB() {
//        if (conexion != null )
//            return conexion;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String servidor = "jdbc:mysql://localhost/paredatos";
            String usuarioDB="root";
            String passwordDB="#parePROGRAMACIONdb";
            conexion= DriverManager.getConnection(servidor,usuarioDB,passwordDB);
            
        }
        catch(ClassNotFoundException ex)
        {
            JOptionPane.showMessageDialog(null, ex, "Error1 en la Conexión con la BD "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion=null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex, "Error2 en la Conexión con la BD "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion=null;
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex, "Error3 en la Conexión con la BD "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion=null;
        }
        finally
        {
            return conexion;
        }
    }
    
    public  void cerrarConexion() {
         try {
             conexion.close();
         } catch (SQLException ex) {
             Logger.getLogger(ConexionDatos.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    
}

