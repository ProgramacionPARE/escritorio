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
public class BoletoManual implements IDBModel{

    long id;
    long idAuto;
    String fechaEntradaM;
    String fechaSalidaM;
    String horaEntradaM;
    String horaSalidaM;
    String razonCobroManual;

    public BoletoManual(long id,long idAuto, String fechaEntradaM, String fechaSalidaM, 
            String horaEntradaM, String horaSalidaM,String  razonCobroManual) {
        this.id = id;
        this.idAuto = idAuto;
        this.fechaEntradaM = fechaEntradaM;
        this.fechaSalidaM = fechaSalidaM;
        this.horaEntradaM = horaEntradaM;
        this.horaSalidaM = horaSalidaM;
        this.razonCobroManual = razonCobroManual;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(long idAuto) {
        this.idAuto = idAuto;
    }
   
    
    
    public String getRazonCobroManual() {
        return razonCobroManual;
    }

    public void setRazonCobroManual(String razonCobroManual) {
        this.razonCobroManual = razonCobroManual;
    }

   
    public String getFechaEntradaM() {
        return fechaEntradaM;
    }

    public void setFechaEntradaM(String fechaEntradaM) {
        this.fechaEntradaM = fechaEntradaM;
    }

    public String getFechaSalidaM() {
        return fechaSalidaM;
    }

    public void setFechaSalidaM(String fechaSalidaM) {
        this.fechaSalidaM = fechaSalidaM;
    }

    public String getHoraEntradaM() {
        return horaEntradaM;
    }

    public void setHoraEntradaM(String horaEntradaM) {
        this.horaEntradaM = horaEntradaM;
    }

    public String getHoraSalidaM() {
        return horaSalidaM;
    }

    public void setHoraSalidaM(String horaSalidaM) {
        this.horaSalidaM = horaSalidaM;
    }
    /*
   
    
    , `fecha_entrada_modificado` =? ,`fecha_salida_modificado` =? "+
                     ",`hora_entrada_modificado` =? ,`hora_salida_modificado` =?  " +
                      ",`cobro_manual` =? ,`razon_cobro_manual` =?
    */       

    public static BoletoManual getById(long id) {
        BoletoManual boletoManual = null;
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT * FROM boleto_manual where id = ?");
            statement.setLong(1, id);
            ResultSet executeQuery = statement.executeQuery();
            if (executeQuery.next()){
                boletoManual  = new BoletoManual(executeQuery.getLong("id"),
                        executeQuery.getLong("id_auto"),
                        executeQuery.getString("fecha_entrada"),
                        executeQuery.getString("fecha_salida"),
                        executeQuery.getString("hora_entrada"),
                        executeQuery.getString("hora_salida"),
                        executeQuery.getString("razon") );
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return boletoManual;
    
    }

    @Override
    public void guardar() {
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("INSERT INTO boleto_manual (`id_auto`, `fecha_entrada`,`fecha_salida`,"+
                            " `hora_entrada`,  `hora_salida`,`razon`)"+
                            " VALUES (?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, this.idAuto);
            statement.setString(2,this.fechaEntradaM);
            statement.setString(3, this.fechaSalidaM);
            statement.setString(4, this.horaEntradaM);
            statement.setString(5, this.horaSalidaM);
            statement.setString(6, this.razonCobroManual); 
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
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("UPDATE boleto_manual SET `fecha_entrada_modificado` =? ,"
                     + "`fecha_salida_modificado` =?,`hora_entrada_modificado` =? ,"
                     + "`hora_salida_modificado` =? ,`razon_cobro_manual` =?"+
                     " ,`id_auto` =?  WHERE `id`=?");
            
            statement.setString(1, fechaEntradaM);
            statement.setString(2, fechaSalidaM);
            statement.setString(3, horaEntradaM);
            statement.setString(4, horaSalidaM);
            statement.setString(5, razonCobroManual);
            statement.setLong(6, idAuto);
            statement.setLong(7, id);
  
             statement.executeUpdate();
             conexion.cerrarConexion();
         } catch (SQLException ex) {
             Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
         } 
    
    }

    @Override
    public void eliminar() {
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("DELETE FROM boleto_manual WHERE `id`= ?");
             statement.setLong(1, id);

            statement.executeUpdate();
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
             
}
