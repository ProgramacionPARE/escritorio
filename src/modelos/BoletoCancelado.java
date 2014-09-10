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
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Conexion;

/**
 *
 * @author oscar
 */
public class BoletoCancelado implements IDBModel{
    int id;
    String razon;
    Turno turno;
    Auto auto;

    
    
    public BoletoCancelado(int id, String razon, Turno turno) {
        this.id = id;
        this.razon = razon;
        this.turno = turno;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }
    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    @Override
    public void guardar() {
         try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("INSERT INTO boleto_cancelado (`id_auto`, `id_turno`,"+
                            " `razon`)"+
                            " VALUES (?,?,?)",Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, auto.getId());
            statement.setLong(2, turno.getId());
            statement.setString(3, razon);
           
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next())
                id = generatedKeys.getInt("GENERATED_KEY");
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actualizar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    static public BoletoCancelado getBoletoCanceladoById(int id){
     BoletoCancelado boletoCancelado = null;
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT * FROM boleto_cancelado where id = ?");
            statement.setInt(1, id);
            ResultSet executeQuery = statement.executeQuery();
            if (executeQuery.next()){
                boletoCancelado = new BoletoCancelado(executeQuery.getInt("id")
                ,executeQuery.getString("razon") , 
                 Turno.getById(executeQuery.getLong("id_turno")));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return boletoCancelado;
    }
    
    
}
