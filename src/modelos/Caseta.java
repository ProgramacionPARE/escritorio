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

/**
 *
 * @author Asistente Proyectos2
 */
public class Caseta {

    private long id;
    private String descripcion;
    private long idEstacionameinto;
    private Tarifa tarifas;

    public Caseta(long id, String descripcion, long idEstacionameinto,Tarifa tarifa) {
        this.id = id;
        this.descripcion = descripcion;
        this.idEstacionameinto = idEstacionameinto;
        this.tarifas = tarifa;
    }

    public Tarifa getTarifa() {
        return tarifas;
    }

    public void setTarifa(Tarifa tarifas) {
        this.tarifas = tarifas;
    }


    public Long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

            
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getIdEstacionameinto() {
        return idEstacionameinto;
    }

    public void setIdEstacionameinto(long idEstacionameinto) {
        this.idEstacionameinto = idEstacionameinto;
    }


    
    static Caseta getById(long id) {
       Caseta caseta = null;
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT * FROM caseta where id = ?");
            statement.setLong(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                caseta = new Caseta(resultSet.getLong("id"),
                        resultSet.getString("descripcion"),
                       resultSet.getLong("id"),Tarifa.getById(resultSet.getLong("id")));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return caseta;
    }
   
    
}
