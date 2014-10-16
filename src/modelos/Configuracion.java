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
import static modelos.Estacionamiento.e;

class Configuracion{
    public static Configuracion DATOS = new Configuracion();
    
    Configuracion(){
        try {
            ConexionDatos conexion = new ConexionDatos();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.prepareStatement("SELECT * FROM configuracion");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                this.setIp(resultSet.getString("ip"));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public  String ip;

    public Configuracion(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

   
    public static Configuracion getDatos() {
        return DATOS;
    }
    
    
  
//    @Override
//    public void guardar() {
//       
//    }
//
//    @Override
//    public void actualizar() {
//          try {
//            ConexionDatos conexion = new ConexionDatos();
//            Connection connectionDB = conexion.getConnectionDB();
//            PreparedStatement  statement = connectionDB.
//            prepareStatement("UPDATE estacionamiento SET `centro_costos`=? ,`descripcion` =? , `direccion` =?"
//                    +",`caseta_actual` =?  ,`tipo` =?   WHERE `id`=?");
//            statement.setInt(1, this.centroCostos);
//            statement.setString(2, this.descripcion);
//            statement.setString(3, this.direccion);
//            statement.setLong(4, this.numeroCaseta);
//            statement.setString(5, this.tipo);
//
//            statement.setLong(6, this.id);
//            statement.executeUpdate();
//            conexion.cerrarConexion();
//        } catch (SQLException ex) {
//            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
//        } 
//    }
//
//    @Override
//    public void eliminar() {
//        
//    }
    
}
