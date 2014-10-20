package modelos;

import ModelosAux.Tiempo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Turno implements IDBModel {

    private long id;
    private Empleado empleado;
    private String tipoTurno;
    private String fechaApertura;
    private String horaApertura;
    private String fechaCierre;
    private String horaCierre;
    private long folioInicial;
    private long folioFinal;
    private int noBol;
    private int noBolTurnoA;
    private int noBolCancelados;
    private int noBolPerdidos;
    private int noBolCobrados;
    private int noBolTurnoS;
    private float total;
    
    private HashMap<String,Turno> turnosImprimir;
    
    private  ArrayList<DetallesMovimiento> detallesMovimiento;
    
    public static  HashMap<Long,Turno> cacheTurnos = new HashMap();
    
    private ArrayList <RetiroParcial> retirosParciales;
    private Estacionamiento estacionamiento;

    public Turno() {
        this.estacionamiento = Estacionamiento.getDatos();
    }
    
    public Turno(Estacionamiento estacionamiento) {
        detallesMovimiento = new  ArrayList<DetallesMovimiento>();
        retirosParciales = new ArrayList <RetiroParcial>();
        this.estacionamiento = estacionamiento;
     }

    public Turno(long id, Empleado empleado, String tipoTurno, String fechaApertura, 
            String horaApertura, String fechaCierre, String horaCierre, long folioInicial, 
            long folioFinal, int noBol, int noBolTurnoA, int noBolCancelados, int noBolPerdidos,
            int noBolCobrados, int noBolTurnoS, float total, ArrayList<DetallesMovimiento> detallesMovimiento,
            ArrayList<RetiroParcial> retirosParciales) {
        this.id = id;
        this.empleado = empleado;
        this.tipoTurno = tipoTurno;
        this.fechaApertura = fechaApertura;
        this.horaApertura = horaApertura;
        this.fechaCierre = fechaCierre;
        this.horaCierre = horaCierre;
        this.folioInicial = folioInicial;
        this.folioFinal = folioFinal;
        this.noBol = noBol;
        this.noBolTurnoA = noBolTurnoA;
        this.noBolCancelados = noBolCancelados;
        this.noBolPerdidos = noBolPerdidos;
        this.noBolCobrados = noBolCobrados;
        this.noBolTurnoS = noBolTurnoS;
        this.total = total;
        this.detallesMovimiento = detallesMovimiento;
        this.retirosParciales = retirosParciales;
    }

    public void inicializarTurno(Empleado empleado,String tipoTurno){
        detallesMovimiento =new  ArrayList<DetallesMovimiento>();
        retirosParciales = new ArrayList <RetiroParcial>();
        fechaApertura = Tiempo.getFecha();
        horaApertura = Tiempo.getHora();
        folioInicial = Long.valueOf(Progresivo.getUltimoProgresivo(estacionamiento.getCaseta() , "BOLETO"));
        folioFinal = folioInicial; 
        noBolTurnoA = Auto.getAutosPendientes(this).size();
        this.empleado = empleado;
        this.tipoTurno = tipoTurno;
    }
    
    public void realizarCorte(Empleado operador){
        
        this.empleado = operador;
        fechaCierre = Tiempo.getFecha();
        horaCierre = Tiempo.getHora();
        folioFinal =  Long.valueOf(Progresivo.getUltimoProgresivo(estacionamiento.getCaseta(), "BOLETO"));
        noBol =  (int)(folioFinal- folioInicial);
        noBolTurnoS = Auto.getAutosPendientes(this).size();
        noBolCobrados = Auto.getAutosCobradosTurnoActual(this).size();
        noBolPerdidos = Auto.getAutosBoletoPerdidoTurnoActual(this).size();
        noBolCancelados = Auto.getAutosBoletoCanceladoTurnoActual(this).size();
        //Obtener Detalles de movimiento del turno
        detallesMovimiento = DetallesMovimiento.generarDetalles(Auto.getAutosCobradosTurnoActual(this),
                Auto.getAutosBoletoPerdidoTurnoActual(this),Auto.getAutosBoletoCanceladoTurnoActual(this),this);
        DetallesMovimiento.ordenarPorPU(detallesMovimiento);
        //DetallesMovimiento.guardar(detallesMovimiento, this.getId());
        total = DetallesMovimiento.calcularTotal(detallesMovimiento);
        detallesParaImprimir();
    }
    
    public void detallesParaImprimir(){
        turnosImprimir = new HashMap();
        for(String s: estacionamiento.getCaseta().getSeries()){
            turnosImprimir.put(s,new Turno());
            
            turnosImprimir.get(s).empleado = this.getEmpleado();
            turnosImprimir.get(s).tipoTurno = this.getTipoTurno();
            
            turnosImprimir.get(s).fechaApertura = this.getFechaApertura();
            turnosImprimir.get(s).horaApertura = this.getHoraApertura();
            
            
            turnosImprimir.get(s).fechaCierre = this.getFechaCierre();
            turnosImprimir.get(s).horaCierre = this.getHoraCierre();
            
            turnosImprimir.get(s).folioInicial =  Auto.getPrimerProgresivoPorSerie(this,s);
            turnosImprimir.get(s).folioFinal =  Auto.getUltimoProgresivoPorSerie(this,s);
            turnosImprimir.get(s).noBol =  (int)(folioFinal- folioInicial);
            
            turnosImprimir.get(s).setFechaApertura(this.getFechaApertura());
            if(estacionamiento.getTipo().equals("Autoservicio"))
                turnosImprimir.get(s).setTipoTurno(this.getTipoTurno()+" - "+ s);
            else 
                turnosImprimir.get(s).setTipoTurno(this.getTipoTurno());
            turnosImprimir.get(s).noBolTurnoA = this.getNoBolTurnoA();
            turnosImprimir.get(s).noBolTurnoS = this.getNoBolTurnoS();
            turnosImprimir.get(s).noBolCobrados = Auto.getAutosCobradosTurnoActual(this,s).size();
            turnosImprimir.get(s).noBolPerdidos = Auto.getAutosBoletoPerdidoTurnoActual(this,s).size();
            turnosImprimir.get(s).noBolCancelados = Auto.getAutosBoletoCanceladoTurnoActual(this,s).size();
            //Obtener Detalles de movimiento del turno
            turnosImprimir.get(s).detallesMovimiento = DetallesMovimiento.generarDetalles(Auto.getAutosCobradosTurnoActual(this,s),
                    Auto.getAutosBoletoPerdidoTurnoActual(this,s),Auto.getAutosBoletoCanceladoTurnoActual(this,s),this);
            DetallesMovimiento.ordenarPorPU(turnosImprimir.get(s).detallesMovimiento);
            //DetallesMovimiento.guardar(turnosImprimir.get(s).detallesMovimiento, this.getId());
            turnosImprimir.get(s).total = DetallesMovimiento.calcularTotal(turnosImprimir.get(s).detallesMovimiento);
        }
    }
    
    public static ArrayList<Turno> getTurnosByFecha(String fecha){
        ArrayList<Turno> turnos = new ArrayList<>();
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT id_turno FROM turnos where fecha_apertura = ? and fecha_cierre is not null");
            statement.setString(1, fecha);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                turnos.add(getById(resultSet.getLong("id_turno")));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return turnos;
    }
    public static ArrayList<Turno> getTurnosByFechaAbiertoAuditoria(String fecha){
        ArrayList<Turno> turnos = new ArrayList<>();
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT id_turno FROM turnos where fecha_apertura = ? ");
            statement.setString(1, fecha);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                turnos.add(getByIdAuditoria(resultSet.getLong("id_turno")));
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
            prepareStatement("SELECT id_turno FROM turnos where fecha_apertura = ? ");
            statement.setString(1, fecha);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                turnos.add(getById(resultSet.getLong("id_turno")));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return turnos;
    }
    
    public static Turno getById(Long id){
        Turno turno = new Turno();
            try {
                Conexion conexion = new Conexion();
                Connection connectionDB = conexion.getConnectionDB();
                PreparedStatement  statement = connectionDB.
                prepareStatement("SELECT * FROM turnos where id_turno = ?");
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    turno = new Turno(resultSet.getLong("id_turno"),
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
                conexion.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
            }
        return turno;
    }
    public static Turno getByIdAuditoria(Long id){
        
        Turno turno = new Turno();
        if(cacheTurnos.containsKey(id)){
            turno = cacheTurnos.get(id);
        }else{
            try {
                Conexion conexion = new Conexion();
                Connection connectionDB = conexion.getConnectionDB();
                PreparedStatement  statement = connectionDB.
                prepareStatement("SELECT * FROM turnos where id_turno = ?");
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    turno = new Turno(resultSet.getLong("id_turno"),
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
    
    public static Turno existeTurnoAbierto() {
        Turno turno = null;
          try {
              Conexion conexion = new Conexion();
              Connection connectionDB = conexion.getConnectionDB();
              PreparedStatement  statement = connectionDB.
              prepareStatement("SELECT id_turno FROM turnos where  fecha_cierre IS NULL");
              ResultSet resultSet = statement.executeQuery();
              if (resultSet.next()){
                    turno = Turno.getById(resultSet.getLong("id_turno"));
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

    public HashMap<String, Turno> getTurnosImprimir() {
        return turnosImprimir;
    }

    public void setTurnosImprimir(HashMap<String, Turno> turnosImprimir) {
        this.turnosImprimir = turnosImprimir;
    }

    public Estacionamiento getEstacionamiento() {
        return estacionamiento;
    }

    public void setEstacionamiento(Estacionamiento estacionamiento) {
        this.estacionamiento = estacionamiento;
    }

    public Empleado getOperador() {
        return empleado;
    }

    public void setOperador(Empleado operador) {
        this.empleado = operador;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
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

    public long getFolioInicial() {
        return folioInicial;
    }

    public void setFolioInicial(long folioInicial) {
        this.folioInicial = folioInicial;
    }

    public long getFolioFinal() {
        return folioFinal;
    }

    public void setFolioFinal(long folioFinal) {
        this.folioFinal = folioFinal;
    }

    public int getNoBol() {
        return noBol;
    }

    public void setNoBol(int noBol) {
        this.noBol = noBol;
    }

    public int getNoBolTurnoA() {
        return noBolTurnoA;
    }

    public void setNoBolTurnoA(int noBolTurnoA) {
        this.noBolTurnoA = noBolTurnoA;
    }

    public int getNoBolCancelados() {
        return noBolCancelados;
    }

    public void setNoBolCancelados(int noBolCancelados) {
        this.noBolCancelados = noBolCancelados;
    }

    public int getNoBolPerdidos() {
        return noBolPerdidos;
    }

    public void setNoBolPerdidos(int noBolPerdidos) {
        this.noBolPerdidos = noBolPerdidos;
    }

    public int getNoBolCobrados() {
        return noBolCobrados;
    }

    public void setNoBolCobrados(int noBolCobrados) {
        this.noBolCobrados = noBolCobrados;
    }

    public int getNoBolTurnoS() {
        return noBolTurnoS;
    }

    public void setNoBolTurnoS(int noBolTurnoS) {
        this.noBolTurnoS = noBolTurnoS;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public ArrayList<DetallesMovimiento> getDetallesMovimiento() {
        return detallesMovimiento;
    }

    public void setDetallesMovimiento(ArrayList<DetallesMovimiento> detallesMovimiento) {
        this.detallesMovimiento = detallesMovimiento;
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
        return retirosParciales;
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
                            " `folio_inicial`,`no_bol_turno_a`,`id_operador`,`tipo_turno`)"
                            + " VALUES (?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, fechaApertura);
            statement.setString(2, horaApertura);
            statement.setLong(3, folioInicial);
            statement.setLong(4, noBolTurnoA); 
            statement.setLong(5, empleado.getId());
            statement.setString(6, tipoTurno);
    
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
            prepareStatement("UPDATE turnos SET `fecha_cierre`=? ,`hora_cierre` =? , `folio_final` =?"
                    +",`no_bol` =? ,`no_bol_turno_s` =? ,`no_bol_cobrados` =?,`no_bol_perdidos` =?,"+
                    "`no_bol_cancelados` =?,`total` =?,`tipo_turno` =?  WHERE `id_turno`=?");
            statement.setString(1, fechaCierre);
            statement.setString(2, horaCierre);
            statement.setLong(3, folioFinal);
            statement.setLong(4, noBol);
            statement.setLong(5, noBolTurnoS);
            statement.setLong(6, noBolCobrados);
            statement.setLong(7, noBolPerdidos);
            statement.setLong(8, noBolCancelados);
            
            statement.setFloat(9, total);
            statement.setString(10, tipoTurno);
            statement.setLong(11, id);
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

}
