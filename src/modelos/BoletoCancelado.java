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

/**
 *
 * @author oscar
 */
public class BoletoCancelado implements IDBModel{
    int id;
    int idAuto;
    long idTurno;
    String razon;
    
    
    
    public BoletoCancelado(int idAuto,long idTurno, String razon) {
        this.idAuto=idAuto ;
        this.idTurno = idTurno;
        this.razon = razon;
    }

    public BoletoCancelado(int id, int idAuto, long idTurno, String razon) {
        this.id = id;
        this.idAuto = idAuto;
        this.idTurno = idTurno;
        this.razon = razon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(int idAuto) {
        this.idAuto = idAuto;
    }

    public long getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }


    @Override
    public void guardar() {
         try {
           Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement  statement = connectionDB.
            prepareStatement("INSERT INTO boleto_cancelado (`id_auto`, `id_turno`,`razon`)"+
                            " VALUES (?,?,?)",Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, idAuto);
            statement.setLong(2, idTurno);
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
    
    static public BoletoCancelado getById(long id){
     BoletoCancelado boletoCancelado = null;
        try {
           Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT * FROM boleto_cancelado where id = ?");
            statement.setLong(1, id);
            ResultSet executeQuery = statement.executeQuery();
            if (executeQuery.next()){
                boletoCancelado = new BoletoCancelado(executeQuery.getInt("id"),
                executeQuery.getInt("id_auto"),executeQuery.getInt("id_turno"),     
                executeQuery.getString("razon") );
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return boletoCancelado;
    }
    
    
}
