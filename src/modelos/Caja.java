
package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sistema
 */
public class Caja implements IDBModel {
    int id;
    float monto;
    float fondo;
    float montoAlarma;

    public Caja(int id, float monto,float fondo,float montoAlarma) {
        this.id = id;
        this.monto = monto;
        this.fondo = fondo;
        this.montoAlarma = montoAlarma;
        
    }

     public static Caja getByCaseta(Long id){
        Caja caja = null;
        Conexion conexion = new Conexion(); 
        try {
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT id FROM caja  where id_caseta = ?");
            statement.setLong(1, id);
            ResultSet executeQuery = statement.executeQuery();
            if (executeQuery.next()){
                caja = Caja.getById(executeQuery.getInt("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexion.cerrarConexion();
        }    
        return caja;
     }
    
    
    public static Caja getById(int id){
        Caja caja = null;
        Conexion conexion = new Conexion(); 
        try {
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT * FROM caja  where id = ?");
            statement.setInt(1, id);
            ResultSet executeQuery = statement.executeQuery();
            if (executeQuery.next()){
                caja = new Caja(executeQuery.getInt("id"),executeQuery.getFloat("monto"),
                executeQuery.getFloat("fondo"),executeQuery.getFloat("monto_alarma"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            conexion.cerrarConexion();
        }    
        return caja;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public float getFondo() {
        return fondo;
    }

    public void setFondo(float fondo) {
        this.fondo = fondo;
    }

    public float getMontoAlarma() {
        return montoAlarma;
    }

    public void setMontoAlarma(float montoAlerma) {
        this.montoAlarma = montoAlerma;
    }

    @Override
    public void guardar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar() {
        Conexion conexion = new Conexion(); 
         try {
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("UPDATE caja SET `monto`= ?,`fondo`= ?,`monto_alarma`= ? WHERE `id`= ?");
            statement.setFloat(1, this.monto);
            statement.setFloat(2, this.fondo);
             statement.setFloat(3, this.montoAlarma);
            statement.setLong(4, this.id);
            
            statement.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
          conexion.cerrarConexion();
            }
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
