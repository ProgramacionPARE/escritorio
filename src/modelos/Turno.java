package modelos;

import ModelosAux.Tiempo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Turno implements IDBModel {
    private long id;
    private long empleadoApertura;
    private long empleadoCierre;
    
    private String tipoTurno;
    private String fechaApertura;
    private String horaApertura;
    private String fechaCierre;
    private String horaCierre;
    private Estacionamiento estacionamiento;
    private HashMap<String,DetalleTurno> detallesTurno;
    private ArrayList <RetiroParcial> retirosParciales;
    
    public static  HashMap<Long,Turno> cacheTurnos = new HashMap();
    
    public Turno(Estacionamiento e){
        estacionamiento = e;
    }

    public Turno(String tipoTurno, String fechaApertura, String horaApertura, String fechaCierre, String horaCierre) {
        this.tipoTurno = tipoTurno;
        this.fechaApertura = fechaApertura;
        this.horaApertura = horaApertura;
        this.fechaCierre = fechaCierre;
        this.horaCierre = horaCierre;
    }

    public Turno(long id, long empleadoEntrada, long empleadoSalida, String tipoTurno, String fechaApertura, String horaApertura, String fechaCierre, String horaCierre) {
        this.id = id;
        this.empleadoApertura = empleadoEntrada;
        this.empleadoCierre = empleadoSalida;
        this.tipoTurno = tipoTurno;
        this.fechaApertura = fechaApertura;
        this.horaApertura = horaApertura;
        this.fechaCierre = fechaCierre;
        this.horaCierre = horaCierre;
    }
    
    
    

    public void inicializarTurno(long empleado,String tipoTurno){
        retirosParciales = new ArrayList <RetiroParcial>();
        fechaApertura = Tiempo.getFecha();
        horaApertura = Tiempo.getHora();
        this.empleadoApertura = empleado;
        this.tipoTurno = tipoTurno;
        this.detallesTurno = new HashMap();
        this.guardar();
        for(String serie: this.estacionamiento.getCaseta().getSeries() ){
            this.detallesTurno.put(serie, new DetalleTurno(serie,estacionamiento,this));
            this.detallesTurno.get(serie).inicializarTurno();
        }
    }
    
    public void realizarCorte(long operador){        
        this.empleadoCierre = operador;
        fechaCierre = Tiempo.getFecha();
        horaCierre = Tiempo.getHora();
        for(String serie: this.estacionamiento.getCaseta().getSeries() ){
            this.detallesTurno.get(serie).cerrarTurno();
        }
    }

   
    
    public static ArrayList<Turno> getTurnosByFecha(String fecha){
        ArrayList<Turno> turnos = new ArrayList<>();
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT id FROM turnos where fecha_apertura = ? and fecha_cierre is not null");
            statement.setString(1, fecha);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                turnos.add(getById(resultSet.getLong("id")));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return turnos;
    }
    
    
    public static ArrayList<Turno> getTurnosByFechaAbierto(String fecha){
        ArrayList<Turno> turnos = new ArrayList<>();
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT id FROM turnos where fecha_apertura = ? ");
            statement.setString(1, fecha);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                turnos.add(getById(resultSet.getLong("id")));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return turnos;
    }
    
    public static Turno getById(Long id){
        Turno turno = null;
            try {
                Conexion conexion = new Conexion();
                Connection connectionDB = conexion.getConnectionDB();
                PreparedStatement  statement = connectionDB.
                prepareStatement("SELECT * FROM turnos where id = ?");
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    turno = new Turno(
                            resultSet.getLong("id"),
                            (resultSet.getLong("id_empleado_apertura")),
                            resultSet.getLong("id_empleado_cierre"),
                            resultSet.getString("tipo_turno"), 
                            resultSet.getString("hora_apertura") ,
                            resultSet.getString("hora_apertura"),
                            resultSet.getString("fecha_cierre"),
                            resultSet.getString("hora_cierre"));
                }
                turno.setEstacionamiento(Estacionamiento.getDatos());
                turno.setDetallesTurno(DetalleTurno.getByTurnoId(turno.getId()));
                conexion.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
            }
        return turno;
    }
  
    
    public static Turno existeTurnoAbierto() {
        Turno turno = null;
          try {
              Conexion conexion = new Conexion();
              Connection connectionDB = conexion.getConnectionDB();
              PreparedStatement  statement = connectionDB.
              prepareStatement("SELECT id FROM turnos where  fecha_cierre IS NULL");
              ResultSet resultSet = statement.executeQuery();
              if (resultSet.next()){
                    turno = Turno.getById(resultSet.getLong("id"));
              }
              conexion.cerrarConexion();
          } catch (SQLException ex) {
              Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
          }
        return turno;
        }
    
       public static Turno existeTurnoAbiertoActivo() {
        Turno turno = null;
          try {
              Conexion conexion = new Conexion();
              Connection connectionDB = conexion.getConnectionDB();
              PreparedStatement  statement = connectionDB.
              prepareStatement("SELECT id FROM turnos where  fecha_cierre IS NULL and activo = 'SI'");
              ResultSet resultSet = statement.executeQuery();
              if (resultSet.next()){
                    turno = Turno.getById(resultSet.getLong("id"));
              }
              conexion.cerrarConexion();
          } catch (SQLException ex) {
              Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
          }
        return turno;
        }
    
   

    public Long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  
    public Estacionamiento getEstacionamiento() {
        return estacionamiento;
    }

    public void setEstacionamiento(Estacionamiento estacionamiento) {
        this.estacionamiento = estacionamiento;
    }

    public Empleado getEmpleadoEntrada() {
        return Empleado.getById(empleadoApertura);
    }

    public void setEmpleadoEntrada(Empleado empleadoEntrada) {
        this.empleadoApertura = empleadoEntrada.getId();
    }

    public Empleado getEmpleadoSalida() {
        return Empleado.getById(empleadoCierre);
    }

    public void setEmpleadoSalida(Empleado empleadoSalida) {
        this.empleadoCierre = empleadoSalida.getId();
    }

    

    public String getTipoTurno() {
        return tipoTurno;
    }

    public void setTipoTurno(String tipoTurno) {
        this.tipoTurno = tipoTurno;
    }

   

    public String getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(String fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public String getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(String horaApertura) {
        this.horaApertura = horaApertura;
    }

    public String getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(String fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(String horaCierre) {
        this.horaCierre = horaCierre;
    }

    public HashMap<String, DetalleTurno> getDetallesTurno() {
        return detallesTurno;
    }

    public void setDetallesTurno(HashMap<String, DetalleTurno> detallesTurno) {
        this.detallesTurno = detallesTurno;
    }

    

    public static HashMap<Long, Turno> getCacheTurnos() {
        return cacheTurnos;
    }

    public static void setCacheTurnos(HashMap<Long, Turno> cacheTurnos) {
        Turno.cacheTurnos = cacheTurnos;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArrayList<RetiroParcial> getRetirosParciales() {
        return RetiroParcial.getRetirosParcialesByTurnoId(id);
    }

    public void setRetirosParciales(ArrayList<RetiroParcial> retirosParciales) {
        this.retirosParciales = retirosParciales;
    }

    @Override
    public void guardar() {
         try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("INSERT INTO turnos (`fecha_apertura`, `hora_apertura`,"+
                            "`id_empleado_apertura`,`id_empleado_cierre`,`tipo_turno`)"
                            + " VALUES (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, fechaApertura);
            statement.setString(2, horaApertura);
            statement.setLong(3, empleadoApertura);
            statement.setLong(4, empleadoCierre);
            statement.setString(5, tipoTurno);
    
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
            prepareStatement("UPDATE turnos SET `fecha_cierre`=? ,`hora_cierre` =?, "
                    + "`tipo_turno` =?  WHERE `id`=?");
            statement.setString(1, fechaCierre);
            statement.setString(2, horaCierre);
            statement.setString(3, tipoTurno);
            statement.setLong(4, id);
            statement.executeUpdate();
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        } 
        Iterator<Map.Entry<String, DetalleTurno>> iterator = this.detallesTurno.entrySet().iterator();
        while(iterator.hasNext()){
            iterator.next().getValue().actualizar();
        }
        
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

/*  public static Turno getByIdAuditoria(Long id){
        Turno turno = new Turno();
        if(cacheTurnos.containsKey(id)){
            turno = cacheTurnos.get(id);
        }else{
            try {
                Conexion conexion = new Conexion();
                Connection connectionDB = conexion.getConnectionDB();
                PreparedStatement  statement = connectionDB.
                prepareStatement("SELECT * FROM turnos where id = ?");
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    turno = new Turno(resultSet.getLong("id"),
                            Empleado.getById(resultSet.getLong("id_operador")),
                            resultSet.getString("tipo_turno"),
                            resultSet.getString("fecha_apertura"), 
                            resultSet.getString("hora_apertura") ,
                            resultSet.getString("fecha_cierre"),
                            resultSet.getString("hora_cierre"),
                            resultSet.getLong("folio_inicial"),
                            resultSet.getLong("folio_final"),
                            resultSet.getInt("no_bol"),
                            resultSet.getInt("no_bol_turno_a"),
                            resultSet.getInt("no_bol_cancelados"),
                            resultSet.getInt("no_bol_perdidos"),
                            resultSet.getInt("no_bol_cobrados"),      
                            resultSet.getInt("no_bol_turno_s"),
                            resultSet.getFloat("Total"),null,
                            RetiroParcial.getRetirosParcialesByTurnoId(id));
                }
                turno.setDetallesMovimiento( DetallesMovimiento.getById(id));
                turno.setEstacionamiento(Estacionamiento.getDatos());
                cacheTurnos.put(turno.getId(), turno);
                conexion.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return turno;
    }

public static ArrayList<Turno> getTurnosByFechaAbiertoAuditoria(String fecha){
        ArrayList<Turno> turnos = new ArrayList<>();
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT id FROM turnos where fecha_apertura = ? ");
            statement.setString(1, fecha);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                turnos.add(getByIdAuditoria(resultSet.getLong("id")));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return turnos;
    }

*/