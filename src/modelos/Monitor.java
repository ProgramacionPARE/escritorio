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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Monitor{
    private static Monitor DATOS = new Monitor();
    public static final String CAJA = "caja";
    public static final String EXPEDIDOR = "expedidor";
    public static final String CLIENTE = "cliente";
    public static final String MONITOR = "monitor";
    private ArrayList<MonitorEstacionameinto> estacionamientos;
    
    private Monitor(){
        try {
            estacionamientos = new ArrayList();
            ConexionDatos conexion = ConexionDatos.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement  statement = connectionDB.prepareStatement("SELECT * FROM configuracion");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                this.estacionamientos.add(new  MonitorEstacionameinto(resultSet.getString("ip"),resultSet.getString("nombre")));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<MonitorEstacionameinto> getEstacionamientos() {
        return estacionamientos;
    }

    public void setEstacionamientos(ArrayList<MonitorEstacionameinto> estacionamientos) {
        this.estacionamientos = estacionamientos;
    }
    
           
    class MonitorEstacionameinto{
        private  String ip;
        private String nombre;

        public MonitorEstacionameinto(String ip, String nombre) {
            this.ip = ip;
            this.nombre = nombre;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
        
        
    }        

    
   
    public static Monitor getInstancia() {
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
