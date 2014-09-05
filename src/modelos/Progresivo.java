/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asistente Proyectos2
 */
public class Progresivo {

    public static String getUltimoProgresivo(Caseta caseta,String tipo) {
         String progresivo = null;
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT LPAD(  MAX(ultimo_progresivo),6, '0') AS ultimo_progresivo " +
            "FROM progresivos where id_cajero = ?  and tipo = ? ");
            statement.setLong(1, caseta.getId());
            statement.setString(2, tipo);
            
            ResultSet executeQuery = statement.executeQuery();
            if (executeQuery.next()){
               progresivo = executeQuery.getString("ultimo_progresivo");
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return progresivo;
    }
    
    public static void setProgresivoMasUno(Caseta caseta,String tipo) {
        String ultimoProgresivo = Progresivo.getUltimoProgresivo(caseta,tipo);
        Integer progresivo = Integer.valueOf(ultimoProgresivo)+1;
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("UPDATE progresivos SET `ultimo_progresivo`=? where id_cajero = ? "
                    + "and tipo = ?" );
            statement.setInt(1,progresivo);
            statement.setLong(2, caseta.getId());
            statement.setString(3, tipo);
            statement.executeUpdate();
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public static void setProgresivo(Caseta caseta,String tipo,int newProgresivo) {
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("UPDATE progresivos SET `ultimo_progresivo`=? where id_cajero = ? "
                    + "and tipo = ?" );
            statement.setInt(1,newProgresivo);
            statement.setLong(2, caseta.getId());
            statement.setString(3, tipo);
            statement.executeUpdate();
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
