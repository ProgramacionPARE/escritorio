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
public class Caseta implements IDBModel {

    private long id;
    private String descripcion;
    private long idEstacionameinto;
    String[] series;
    private ArrayList<Tarifa> tarifas;

    public Caseta(long id, String descripcion, long idEstacionameinto, String[] series, ArrayList<Tarifa> tarifas) {
        this.id = id;
        this.descripcion = descripcion;
        this.idEstacionameinto = idEstacionameinto;
        this.series = series;
        this.tarifas = tarifas;
    }

    static Caseta getById(long id) {
        Caseta caseta = null;
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT * FROM caseta where id = ?");
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                caseta = new Caseta(resultSet.getLong("id"), resultSet.getString("descripcion"), resultSet.getLong("id"),
                        resultSet.getString("series") != null ? resultSet.getString("series").split(" ") : new String[0], Tarifa.getAll());
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return caseta;
    }

    @Override
    public void guardar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar() {
        String ser = "";
        for (String s : series) {
            ser += s + " ";
        }
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("UPDATE caseta SET `descripcion`=? ,`series` =?  WHERE `id`=?");
            statement.setString(1, descripcion);
            statement.setString(2, ser);
            statement.setLong(3, id);

            statement.executeUpdate();
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArrayList<Tarifa> getTarifas() {
        return tarifas;
    }

    public void setTarifas(ArrayList<Tarifa> tarifas) {
        this.tarifas = tarifas;
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

    public String[] getSeries() {
        return series;
    }

    public void setSeries(String[] series) {
        this.series = series;
    }
}
