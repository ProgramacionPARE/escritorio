/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import ModelosAux.Seguridad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sistema
 */
public class Descuento {
    long id;
    long folio;
    float descuento;
    boolean activo;
    String clave;

    public Descuento(long id, long folio, float descuento, boolean activo) {
        this.id = id;
        this.folio = folio;
        this.descuento = descuento;
        this.activo = activo;
    }

    public Descuento(long folio, float descuento, boolean activo) {
        this.folio = folio;
        this.descuento = descuento;
        this.activo = activo;
        this.clave = Seguridad.getClave(6);
    }
    public Descuento(long folio, float descuento, boolean activo,String clave) {
        this.folio = folio;
        this.descuento = descuento;
        this.activo = activo;
        this.clave = clave;
    }
    
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    

    public long getFolio() {
        return folio;
    }

    public void setFolio(long folio) {
        this.folio = folio;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

   

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    
    
     
    public static Descuento getByIdClave(String clave){
        Descuento descuento=null;
        try {
           Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT * FROM descuentos where  clave = ? and folio = ? and activo = 'si' ");
            statement.setString(1, clave.substring(0,6 ));
            statement.setLong(2,Long.valueOf(clave.substring(6,12 )));
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                descuento = new Descuento(resultSet.getLong("id"), resultSet.getLong("folio"),resultSet.getFloat("descuento"),
                        resultSet.getString("activo").equals("si"));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return descuento;
    }

    public void guardar() {
        try {
           Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement  statement = connectionDB.
            prepareStatement("INSERT INTO descuentos (`folio`, `descuento`,"+
                            " `activo`,`clave`)"
                            + " VALUES (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, folio);
            statement.setFloat(2, descuento);
            statement.setString(3, activo?"si":"no");
            statement.setString(4, clave);
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next())
                id = generatedKeys.getInt("GENERATED_KEY");
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void actualizar() {
        try {
            Conexion conexion = Conexion.getInstance();
             Connection connectionDB = conexion.getConnection();
             PreparedStatement  statement = connectionDB.
             prepareStatement("UPDATE descuentos SET `activo` =?  WHERE `id`=?");

             statement.setString(1, activo?"si":"no");
            
             statement.setLong(2, id);
  
             statement.executeUpdate();
             conexion.cerrarConexion();
         } catch (SQLException ex) {
             Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
         } 
    }
    
    
}
