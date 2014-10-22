
package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BoletoPerdido {
    int id;
    long progresivo;
    Auto auto;
    PropietarioPerdido propietario;
    Turno turno;
    
    static BoletoPerdido getById(long id) {
        BoletoPerdido boletoPerdido = null;
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT * FROM boleto_perdido where id = ?");
            statement.setLong(1, id);
            ResultSet executeQuery = statement.executeQuery();
            if (executeQuery.next()){
                boletoPerdido = new BoletoPerdido(executeQuery.getInt("id")
                ,executeQuery.getInt("progresivo") ,null, 
                 PropietarioPerdido.getById(executeQuery.getInt("id_propietario"),null), 
                 Turno.getById(executeQuery.getLong("id_turno")));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return boletoPerdido;
    }
    


    public BoletoPerdido() {
    }

    public BoletoPerdido(int id, long progresivo, Auto auto, PropietarioPerdido propietario, Turno turno) {
        this.id = id;
        this.progresivo = progresivo;
        this.auto = auto;
        this.propietario = propietario;
        this.turno = turno;
    }
    

    public BoletoPerdido( long progresivo, Auto auto, PropietarioPerdido propietario, Turno turno) {
        this.progresivo = progresivo;
        this.auto = auto;
        this.propietario = propietario;
        this.turno = turno;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getProgresivo() {
        return progresivo;
    }

    public void setProgresivo(long progresivo) {
        this.progresivo = progresivo;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public PropietarioPerdido getPropietario() {
        return propietario;
    }

    public void setPropietario(PropietarioPerdido propietario) {
        this.propietario = propietario;
    }

    public void guardar() {
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("INSERT INTO boleto_perdido (`progresivo`, `id_auto`,"+
                            " `id_turno`,`id_propietario`)"
                            + " VALUES (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);

            statement.setLong(1, progresivo);
            statement.setInt(2, auto.getId());
            statement.setLong(3, turno.getId());
            statement.setInt(4, propietario.getId()); 
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next())
                id = generatedKeys.getInt("GENERATED_KEY");
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
