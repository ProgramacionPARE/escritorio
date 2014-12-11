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

public class Configuracion{
    private static Configuracion DATOS = new Configuracion();
    public static final String CAJA = "caja";
    public static final String EXPEDIDOR = "expedidor";
    public static final String CLIENTE = "cliente";
    public static final String MONITOR = "monitor";
    private Configuracion(){
        try {
            ConexionDatos conexion = ConexionDatos.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement  statement = connectionDB.prepareStatement("SELECT * FROM configuracion");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                this.ip = resultSet.getString("ip");
                this.terminal = resultSet.getString("terminal");
                this.url = resultSet.getString("url");
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private  String ip;
    private String terminal;
    private String url;

    public Configuracion(String ip) {
        this.ip = ip;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

   
    public static Configuracion getInstancia() {
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
//            Connection connectionDB = conexion.getConnectionDB().getConnection();
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
