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


public class Estacionamiento implements IDBModel{
    static Estacionamiento e;
    int id;
    int centroCostos;
    String descripcion;
    String direccion;
    Caseta caseta;
    int numeroCaseta;
    String tipo;
    String token;   

    
    public Estacionamiento(int id, int centroCostos, String descripcion, String direccion,
            Caseta caseta, int numeroCaseta,String tipo) {
        this.id = id;
        this.centroCostos = centroCostos;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.caseta = caseta;
        this.numeroCaseta = numeroCaseta;
        this.tipo = tipo;
    }
    



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public int getCentroCostos() {
        return centroCostos;
    }

    public void setCentroCostos(int centroCostos) {
        this.centroCostos = centroCostos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Caseta getCaseta() {
        return caseta;
    }

    public void setCaseta(Caseta caseta) {
        this.caseta = caseta;
    }

    public int getNumeroCaseta() {
        return numeroCaseta;
    }

    public void setNumeroCaseta(int numeroCaseta) {
        this.numeroCaseta = numeroCaseta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


     
    
    public static Estacionamiento getDatos() {
        Estacionamiento estacionamiento = null;
        if(Estacionamiento.e == null){
            try {
                Conexion conexion = new Conexion();
                Connection connectionDB = conexion.getConnectionDB();
                PreparedStatement  statement = connectionDB.
                prepareStatement("SELECT * FROM estacionamiento");

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()){
                    estacionamiento = Estacionamiento.getById(resultSet.getLong("id"),connectionDB);
                }
                conexion.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
            }
            e = estacionamiento;
        }
        return e;
    
    }
    
    static Estacionamiento getById(long id, Connection cDB) {
       Estacionamiento centro = null;
        try {
           
            Connection connectionDB = cDB;
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT * FROM estacionamiento where id = ?");
            statement.setLong(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                centro = new Estacionamiento(resultSet.getInt("id"),
                resultSet.getInt("centro_costos"), resultSet.getString("descripcion"), resultSet.getString("direccion"),
                Caseta.getById(resultSet.getLong("caseta_actual")),resultSet.getInt("caseta_actual"),
                resultSet.getString("tipo"));
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return centro;
    }

    @Override
    public void guardar() {
       
    }

    @Override
    public void actualizar() {
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("UPDATE estacionamiento SET `centro_costos`=? ,`descripcion` =? , `direccion` =?"
                    +",`caseta_actual` =?  ,`tipo` =?   WHERE `id`=?");
            statement.setInt(1, this.centroCostos);
            statement.setString(2, this.descripcion);
            statement.setString(3, this.direccion);
            statement.setLong(4, this.numeroCaseta);
            statement.setString(5, this.tipo);

            statement.setLong(6, this.id);
            statement.executeUpdate();
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public void eliminar() {
        
    }
    
}
