

package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RetiroParcial implements IDBModel {



    private long id;
    private long progresivo;
    private String fecha;
    private String hora;
    private float monto;
    private float montoReal;
    private Long idTurno;
    private Long idCaseta; 

    public RetiroParcial() {
    }

    public RetiroParcial(long progresivo, String fecha, String hora, float monto, float montoReal, Long idTurno, Long idCaseta) {
        this.progresivo = progresivo;
        this.fecha = fecha;
        this.hora = hora;
        this.monto = monto;
        this.montoReal = montoReal;
        this.idTurno = idTurno;
        this.idCaseta = idCaseta;
    }

    public RetiroParcial(long id, long progresivo, String fecha, String hora, float monto, float montoReal, Long idTurno, Long idCaseta) {
        this.id = id;
        this.progresivo = progresivo;
        this.fecha = fecha;
        this.hora = hora;
        this.monto = monto;
        this.montoReal = montoReal;
        this.idTurno = idTurno;
        this.idCaseta = idCaseta;
    }


    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProgresivo() {
        return progresivo;
    }

    public void setProgresivo(long progresivo) {
        this.progresivo = progresivo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public Long getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(Long idTurno) {
        this.idTurno = idTurno;
    }

    public Long getIdCaseta() {
        return idCaseta;
    }

    public void setIdCaseta(Long idCaseta) {
        this.idCaseta = idCaseta;
    }

    public float getMontoReal() {
        return montoReal;
    }

    public void setMontoReal(float montoReal) {
        this.montoReal = montoReal;
    }
    

    @Override
    public void guardar() {
       try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("INSERT INTO retiro_parcial (`progresivo`, `fecha`,"+
                            " `hora`,`id_caseta`,`id_turno`,`monto`,`monto_real`)"
                            + " VALUES (?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, this.progresivo);
            statement.setString(2, this.fecha);
            statement.setString(3, this.hora);
            statement.setLong(4, this.idCaseta); 
            statement.setLong(5, this.idTurno);
            statement.setFloat(6, this.monto);
            statement.setFloat(7, this.montoReal);
            
    
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next())
                id = generatedKeys.getLong("GENERATED_KEY");
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
    
    
    private static RetiroParcial getById(long id) {
        RetiroParcial retiroParcial = new RetiroParcial();
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT * FROM retiro_parcial where id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                retiroParcial = new RetiroParcial(resultSet.getLong("id"),
                        resultSet.getLong("progresivo"),resultSet.getString("fecha"),
                        resultSet.getString("hora"),resultSet.getFloat("monto"),
                        resultSet.getFloat("monto_real"),resultSet.getLong("id_turno"),
                        resultSet.getLong("id_caseta"));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retiroParcial;
    }
    
    static ArrayList<RetiroParcial> getRetirosParcialesByTurnoId(Long turno_id) {
       ArrayList<RetiroParcial> rp= new ArrayList<RetiroParcial>();
         try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT id FROM retiro_parcial where id_turno = ?");
            statement.setLong(1, turno_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                rp.add(RetiroParcial.getById(resultSet.getLong("id")));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rp;
    }
    
}
