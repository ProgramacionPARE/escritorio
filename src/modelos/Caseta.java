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
    private CentroOperativo centroOperativo;
    private ArrayList<Tarifa> tarifas;

    public Caseta(int id, String descripcion, CentroOperativo centroOperativo) {
        this.id = id;
        this.descripcion = descripcion;
        this.centroOperativo = centroOperativo;
        this.tarifas = Tarifa.getAll();
    }

    public ArrayList<Tarifa> getTarifas() {
        return tarifas;
    }

    public void setTarifas(ArrayList<Tarifa> tarifas) {
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

    public CentroOperativo getCentroOperativo() {
        return centroOperativo;
    }

    public void setCentroOperativo(CentroOperativo centroOperativo) {
        this.centroOperativo = centroOperativo;
    }
    
    static Caseta getById(int id) {
       Caseta caseta = null;
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT * FROM caseta where id = ?");
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                caseta = new Caseta(resultSet.getInt("id"),
                        resultSet.getString("descripcion"),
                        CentroOperativo.getById(resultSet.getInt("id_centro_operativo")));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return caseta;
    }
   
    
}
