
package modelos;

import modelosReportesAux.DetallesMovimiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelosReportesAux.DetallesMovimientoAvanzados;

public class TurnoDetalles implements IDBModel{

    
    static ArrayList<TurnoDetalles> getTurnoDetalleOffline() {
       ArrayList <TurnoDetalles> detalles= new ArrayList();
        try {
           Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT * FROM detalle_turno where id_remoto is NULL");
            ResultSet executeQuery = statement.executeQuery();
            while (executeQuery.next()){
                TurnoDetalles dt = TurnoDetalles.getById(executeQuery.getLong("id"));
                detalles.add(dt);
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detalles;
    }

    private long id;
    private String idRemoto;
    private long idTurno;
    private String serie;
    private long folioInicial;
    private long folioFinal;
    private long noBol;
    private long noBolTurnoA;
    private long noBolCancelados;
    private long noBolPerdidos;
    private long noBolCobrados;
    private long noBolContra;
    private long noBolManual;
    private long noBolTurnoS;
    private float total;
    
    private long noBolCortesiaTotal;
    private long noBolCortesiaParcial;
    private float totalCortesia;
    
    private String tipoTurno;
    
    private ArrayList<DetallesMovimiento> detalleMovimiento;
    private ArrayList<DetallesMovimientoAvanzados> detalleMovimientoAvanzado;
    
    private Turno turno;
    
    private Estacionamiento estacionamiento;
    
    TurnoDetalles(String serie, Estacionamiento estacionamiento,Turno turno) {
        this.idTurno = turno.getId();
        this.serie = serie;
        this.estacionamiento = estacionamiento;
    }

    public TurnoDetalles(long id, long idTurno, String serie, long folioInicial,
            long folioFinal, long noBol, long noBolTurnoA, long noBolCancelados, 
            long noBolPerdidos, long noBolCobrados, long noBolContra, long noBolManual, 
            long noBolTurnoS, float total,long noBolCortesiaTotal, 
            long noBolCortesiaParcial, float totalCortesia, String idRemoto) {
        this.id = id;
        this.idTurno = idTurno;
        this.serie = serie;
        this.folioInicial = folioInicial;
        this.folioFinal = folioFinal;
        this.noBol = noBol;
        this.noBolTurnoA = noBolTurnoA;
        this.noBolCancelados = noBolCancelados;
        this.noBolPerdidos = noBolPerdidos;
        this.noBolCobrados = noBolCobrados;
        this.noBolContra = noBolContra;
        this.noBolManual = noBolManual;
        this.noBolTurnoS = noBolTurnoS;
        this.total = total;
        this.noBolCortesiaTotal = noBolCortesiaTotal;
        this.noBolCortesiaParcial = noBolCortesiaParcial;
        this.totalCortesia = totalCortesia;
        this.idRemoto = idRemoto;
    }

    public String getTipoTurno() {
        return tipoTurno;
    }

    public void setTipoTurno(String tipoTurno) {
        this.tipoTurno = tipoTurno;
    }
    
    
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdRemoto() {
        return idRemoto;
    }

    public void setIdRemoto(String idRemoto) {
        this.idRemoto = idRemoto;
    }

        
    public long getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(long idTurno) {
        this.idTurno = idTurno;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
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

    public long getNoBol() {
        return noBol;
    }

    public void setNoBol(long noBol) {
        this.noBol = noBol;
    }

    public long getNoBolTurnoA() {
        return noBolTurnoA;
    }

    public void setNoBolTurnoA(long noBolTurnoA) {
        this.noBolTurnoA = noBolTurnoA;
    }

    public long getNoBolCancelados() {
        return noBolCancelados;
    }

    public void setNoBolCancelados(long noBolCancelados) {
        this.noBolCancelados = noBolCancelados;
    }

    public long getNoBolPerdidos() {
        return noBolPerdidos;
    }

    public void setNoBolPerdidos(long noBolPerdidos) {
        this.noBolPerdidos = noBolPerdidos;
    }

    public long getNoBolCobrados() {
        return noBolCobrados;
    }

    public void setNoBolCobrados(long noBolCobrados) {
        this.noBolCobrados = noBolCobrados;
    }

    public long getNoBolContra() {
        return noBolContra;
    }

    public void setNoBolContra(long noBolContra) {
        this.noBolContra = noBolContra;
    }

    public long getNoBolManual() {
        return noBolManual;
    }

    public void setNoBolManual(long noBolManual) {
        this.noBolManual = noBolManual;
    }

    public long getNoBolTurnoS() {
        return noBolTurnoS;
    }

    public void setNoBolTurnoS(long noBolTurnoS) {
        this.noBolTurnoS = noBolTurnoS;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public long getNoBolCortesiaTotal() {
        return noBolCortesiaTotal;
    }

    public void setNoBolCortesiaTotal(long noBolCortesiaTotal) {
        this.noBolCortesiaTotal = noBolCortesiaTotal;
    }

    public long getNoBolCortesiaParcial() {
        return noBolCortesiaParcial;
    }

    public void setNoBolCortesiaParcial(long noBolCortesiaParcial) {
        this.noBolCortesiaParcial = noBolCortesiaParcial;
    }

    public float getTotalCortesia() {
        return totalCortesia;
    }

    public void setTotalCortesia(float totalCortesia) {
        this.totalCortesia = totalCortesia;
    }

    public ArrayList<DetallesMovimiento> getDetalleMovimiento() {
        return detalleMovimiento;
    }

    public void setDetalleMovimiento(ArrayList<DetallesMovimiento> detalleMovimiento) {
        this.detalleMovimiento = detalleMovimiento;
    }

    public ArrayList<DetallesMovimientoAvanzados> getDetalleMovimientoAvanzado() {
        return detalleMovimientoAvanzado;
    }

    public void setDetalleMovimientoAvanzado(ArrayList<DetallesMovimientoAvanzados> detalleMovimientoAvanzado) {
        this.detalleMovimientoAvanzado = detalleMovimientoAvanzado;
    }
    

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Estacionamiento getEstacionamiento() {
        return estacionamiento;
    }

    public void setEstacionamiento(Estacionamiento estacionamiento) {
        this.estacionamiento = estacionamiento;
    }
    
    public void inicializarTurno(){
        this.folioInicial  = Long.valueOf(Progresivo.getUltimoProgresivo(estacionamiento.getCaseta(), serie));
        this.folioFinal = folioInicial; 
        this.noBolTurnoA = Auto.getNumAutosPendientesTurnoActual(idTurno, serie);
        this.guardar();
    }
    
    public void cerrarTurno(String tipoCorte){
        ArrayList<Auto> autosPendientesTurnoActual = null;
        ArrayList<Auto> autosPendientesTotal = null;
        if(tipoCorte.equals("corte"))
            this.folioFinal =  Long.valueOf(Progresivo.getUltimoProgresivo(estacionamiento.getCaseta(), serie));
        
        this.noBol = this.folioFinal - this.folioInicial;
        if(tipoCorte.equals("corte")){
            Auto.guardarAutosPendientes(idTurno);
            this.noBolTurnoS = Auto.getNumAutosPendientesByTurno(idTurno, serie);
            autosPendientesTurnoActual = Auto.getAutosPendientesByTurno(idTurno, serie);
        }else if(tipoCorte.equals("precorte")){
            this.noBolTurnoS = Auto.getNumAutosPendientesTurnoActual(idTurno, serie);
            autosPendientesTurnoActual =  Auto.calcularMontoAutosPendientes(Auto.getAutosPendientesTurnoActual(idTurno, serie));
        }else if(tipoCorte.equals("reimpresion")){
            this.noBolTurnoS = Auto.getNumAutosPendientesByTurno(idTurno, serie);
            autosPendientesTurnoActual = Auto.getAutosPendientesByTurno(idTurno, serie);
        }else if(tipoCorte.equals("precorteTotal")){
             this.noBolTurnoS = 0;
            autosPendientesTurnoActual = new ArrayList();
            autosPendientesTotal = Auto.calcularMontoAutosPendientes( Auto.getAutosPendientesTurnoActual(idTurno, serie));
            
        }

        
        
        this.noBolCobrados = Auto.getNumAutosCobradosTurnoActual(idTurno, serie);
        this.noBolCancelados = Auto.getNumAutosBoletoCanceladoTurnoActual(idTurno, serie);
        this.noBolPerdidos = Auto.getNumAutosBoletoPerdidoTurnoActual(idTurno, serie);
        this.noBolManual =  Auto.getNumAutosManualesTurnoActual(idTurno, serie);
        this.noBolContra =  Auto.getNumAutosContraTurnoActual(idTurno, serie);
        
        
        List<Auto> autosBoletoCanceladoTurnoActual = Auto.getAutosBoletoCanceladoTurnoActual(idTurno, serie);
        List<Auto> autosBoletoPerdidoTurnoActual = Auto.getAutosBoletoPerdidoTurnoActual(idTurno, serie);

        List<Auto> autosCobradosTurnoActual = Auto.getAutosCobradosTurnoActual(idTurno, serie);
        ArrayList<Auto> autosManualesTurnoActual = Auto.getAutosManualesTurnoActual(idTurno, serie);
        ArrayList<Auto> autosContraTurnoActual = Auto.getAutosContraTurnoActual(idTurno, serie);
        
         List<Auto> autosCobradosTotal = new ArrayList();
         autosCobradosTotal.addAll(autosCobradosTurnoActual);
         autosCobradosTotal.addAll(autosManualesTurnoActual);
         autosCobradosTotal.addAll(autosContraTurnoActual);
         if(tipoCorte.equals("precorteTotal")){
             autosCobradosTotal.addAll(autosPendientesTotal);
              this.noBolCobrados+= Auto.getNumAutosPendientesTurnoActual(idTurno, serie);
         }
         
        
        this.detalleMovimiento = DetallesMovimiento.generarDetalles(
                autosCobradosTotal, autosBoletoPerdidoTurnoActual);
        
        this.detalleMovimientoAvanzado = DetallesMovimientoAvanzados.generarDetalles(autosPendientesTurnoActual,autosBoletoCanceladoTurnoActual,
                autosBoletoPerdidoTurnoActual,autosManualesTurnoActual,autosContraTurnoActual);
        this.total = DetallesMovimiento.calcularTotal(detalleMovimiento);
        this.actualizar();
    }        
    
    static HashMap<String, TurnoDetalles> getByTurnoId(Long id) {
        HashMap<String, TurnoDetalles> detalles= new HashMap();
        try {
           Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT * FROM detalle_turno where id_turno = ?");
            statement.setLong(1, id );
            ResultSet executeQuery = statement.executeQuery();
            while (executeQuery.next()){
                TurnoDetalles dt = TurnoDetalles.getById(executeQuery.getLong("id"));
                detalles.put(dt.getSerie(), dt);
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detalles;
    }
    
    static TurnoDetalles getById(Long id){
        TurnoDetalles detalleTurno = null;
        try {
           Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT * FROM  detalle_turno where id = ?");
            statement.setLong(1, id);
            ResultSet executeQuery = statement.executeQuery();
            if (executeQuery.next()){
                detalleTurno = new TurnoDetalles(
                    executeQuery.getInt("id"),
                    executeQuery.getLong("id_turno"),
                    executeQuery.getString("serie"),
                    executeQuery.getLong("folio_inicial"),
                    executeQuery.getLong("folio_final"),
                    executeQuery.getLong("no_bol"),
                    executeQuery.getLong("no_bol_turno_a"),
                    executeQuery.getLong("no_bol_cancelados"),
                    executeQuery.getLong("no_bol_perdidos"),
                    executeQuery.getLong("no_bol_cobrados"),
                    executeQuery.getLong("no_bol_contra"),
                    executeQuery.getLong("no_bol_manual"),
                    executeQuery.getLong("no_bol_turno_s"),
                    executeQuery.getFloat("total"),
                    executeQuery.getLong("no_bol_cortesia_total"),
                    executeQuery.getLong("no_bol_cortesia_parcial"),
                    executeQuery.getFloat("total_cortesia"),
                        
                    executeQuery.getString("id_remoto"));
                
            }
            
                    
            detalleTurno.setEstacionamiento(Estacionamiento.getDatos());
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detalleTurno;
    }

    @Override
    public void guardar() {
         try {
           Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement  statement = connectionDB.
            prepareStatement("INSERT INTO detalle_turno (`id_turno`, `serie`,`folio_inicial`,"
                    + " `folio_final`,`no_bol`,`no_bol_turno_a`,`no_bol_cancelados`,"
                    + " `no_bol_perdidos`,`no_bol_cobrados`,"
                    + "`no_bol_contra`,`no_bol_manual`,`no_bol_turno_s`,`total`)"
                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);        
            statement.setLong(1, idTurno);
            statement.setString(2,serie);
            statement.setLong(3, folioInicial);
            statement.setLong(4, folioFinal);
            statement.setLong(5, noBol);
            statement.setLong(6, noBolTurnoA); 
            statement.setLong(7, noBolCancelados);
            statement.setLong(8, noBolPerdidos);
            statement.setLong(9, noBolCobrados);
            statement.setLong(10, noBolContra); 
            statement.setLong(11, noBolManual);
            statement.setLong(12, noBolTurnoS);
            statement.setFloat(13, total);
            
            
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
           Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement  statement = connectionDB.
            prepareStatement("UPDATE detalle_turno SET " 
                    + " `folio_final` = ?,`no_bol` = ?,`no_bol_turno_a` = ?,`no_bol_cancelados` = ?,"
                    + " `no_bol_perdidos` = ?,`no_bol_cobrados` = ?, `no_bol_contra` = ?,"
                    + "`no_bol_manual` = ?,`no_bol_turno_s` = ?,`total`= ? ,`id_remoto`= ?  WHERE `id`=?");
            statement.setLong(1, folioFinal);
            statement.setLong(2, noBol);
            statement.setLong(3, noBolTurnoA);
            statement.setLong(4, noBolCancelados);
            statement.setLong(5, noBolPerdidos);
            statement.setLong(6, noBolCobrados);
            statement.setLong(7, noBolContra);
            statement.setLong(8, noBolManual);
            statement.setLong(9, noBolTurnoS);
            statement.setFloat(10, total);
            statement.setString(11,idRemoto);
            statement.setLong(12, id);
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
