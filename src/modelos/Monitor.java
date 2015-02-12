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

public class Monitor {

    private static Monitor DATOS = new Monitor();
    public static final String CAJA = "caja";
    public static final String EXPEDIDOR = "expedidor";
    public static final String CLIENTE = "cliente";
    public static final String MONITOR = "monitor";
    private ArrayList<MonitorEstacionamiento> estacionamientos;

    private Monitor() {
        try {
            estacionamientos = new ArrayList();
            ConexionDatos conexion = ConexionDatos.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.prepareStatement("SELECT * FROM estacionamientos");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                this.estacionamientos.add(new MonitorEstacionamiento(resultSet.getString("ip"), resultSet.getString("nombre")));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<MonitorEstacionamiento> getEstacionamientos() {
        return estacionamientos;
    }

    public void setEstacionamientos(ArrayList<MonitorEstacionamiento> estacionamientos) {
        this.estacionamientos = estacionamientos;
    }

   

    public static Monitor getInstancia() {
        return DATOS;
    }

}
