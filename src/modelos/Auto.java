package modelos;

import ModelosAux.Tiempo;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Auto implements Serializable{

    private int id;
    private String idRemoto;
    private String progresivo;
    private String matricula;
    private String fechaEntrada;
    private String fechaSalida;
    private String horaEntrada;
    private String horaSalida;
    private String marca;
    private String modelo;
    private String color;

    private long boletoPerdido;
    private long boletoCancelado;
    private long boletoManual;
    private long boletoContra;

    private boolean isDentro;
    private boolean isReciboImpreso;
    private boolean isBoletoPerdido;
    private boolean isBoletoCancelado;
    private boolean isBoletoManual;
    private boolean isBoletoContra;
    private boolean isBoletoPendiente;
    private int horas;
    private int minutos;
    private float monto;

    long turnoEntrada;
    long turnoSalida;

    private String serie;
    private String nota;
    long tarifa;
    private float descuento;

    private String clave;
    private float montoReciboPago;
    private long caseta;
    private int estadoServidor;

    public Auto() {
    }

    //Constructor para nuevo auto
    public Auto(String progresivo, String matricula, String fechaEntrada,
            String horaEntrada, String marca, String modelo, String color,
            long turnoEntrada, String serie, String nota,
            String clave, long caseta) {
        this.progresivo = progresivo;
        this.matricula = matricula;
        this.fechaEntrada = fechaEntrada;
        this.horaEntrada = horaEntrada;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.turnoEntrada = turnoEntrada;
        this.serie = serie;
        this.nota = nota;
        this.clave = clave;
        this.caseta = caseta;
        this.tarifa = Tarifa.getAll().get(0).getId();
    }

    //Constructor para recuperar auto
    public Auto(int id, String progresivo, String matricula, String fechaEntrada,
            String fechaSalida, String horaEntrada, String horaSalida, String marca,
            String modelo, String color, long boletoPerdido, long boletoCancelado,
            long boletoManual, long boletoContra, boolean isDentro, boolean isReciboImpreso,
            boolean isBoletoPerdido, boolean isBoletoCancelado, boolean isBoletoManual,
            boolean isBoletoContra, boolean isBoletoPendiente, int horas, int minutos,
            float monto, long turnoEntrada, long turnoSalida, String serie, String nota,
            long tarifa, float descuento, String clave, long caseta, String idRemoto,int estadoServidor ) {
        this.id = id;
        this.progresivo = progresivo;
        this.matricula = matricula;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.boletoPerdido = boletoPerdido;
        this.boletoCancelado = boletoCancelado;
        this.boletoManual = boletoManual;
        this.boletoContra = boletoContra;
        this.isDentro = isDentro;
        this.isReciboImpreso = isReciboImpreso;
        this.isBoletoPerdido = isBoletoPerdido;
        this.isBoletoCancelado = isBoletoCancelado;
        this.isBoletoManual = isBoletoManual;
        this.isBoletoContra = isBoletoContra;
        this.isBoletoPendiente = isBoletoPendiente;
        this.horas = horas;
        this.minutos = minutos;
        this.monto = monto;
        this.turnoEntrada = turnoEntrada;
        this.turnoSalida = turnoSalida;
        this.serie = serie;
        this.nota = nota;
        this.tarifa = tarifa;
        this.descuento = descuento;
        this.clave = clave;
        this.caseta = caseta;
        this.idRemoto = idRemoto;
        this.estadoServidor = estadoServidor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerie() {
        return serie != null ? serie : "0";
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getEstadoServidor() {
        return estadoServidor;
    }

    public void setEstadoServidor(int estadoServidor) {
        this.estadoServidor = estadoServidor;
    }

   

    public float getMontoReciboPago() {
        return montoReciboPago;
    }

    public void setMontoReciboPago(float montoPago) {
        this.montoReciboPago = montoPago;
    }

    public boolean isDentro() {
        return isDentro;
    }

    public void setDentro(boolean n) {
        isDentro = n;
    }

    public boolean isReciboImpreso() {
        return isReciboImpreso;
    }

    public void setReciboImpreso(boolean reciboImpreso) {
        this.isReciboImpreso = reciboImpreso;
    }

    public boolean isBoletoPerdido() {
        return isBoletoPerdido;
    }

    public void setIsBoletoPerdido(boolean isBoletoPerdido) {
        this.isBoletoPerdido = isBoletoPerdido;
    }

    public boolean isBoletoCancelado() {
        return isBoletoCancelado;
    }

    public void setIsBoletoCancelado(boolean isBoletoCancelado) {
        this.isBoletoCancelado = isBoletoCancelado;
    }

    public boolean isBoletoManual() {
        return isBoletoManual;
    }

    public void setIsBoletoManual(boolean isBoletoManual) {
        this.isBoletoManual = isBoletoManual;
    }

    public boolean isBoletoContra() {
        return isBoletoContra;
    }

    public void setIsBoletoContra(boolean isBoletoContra) {
        this.isBoletoContra = isBoletoContra;
    }

    public boolean isBoletoPendiente() {
        return isBoletoPendiente;
    }

    public void setIsBoletoPendiente(boolean isBoletoPendiente) {
        this.isBoletoPendiente = isBoletoPendiente;
    }

//    public BoletoContra getBoletoContra() {
//        return BoletoContra.getById(boletoContra);
//    }
//
//    public void setBoletoContra(BoletoContra boletoContra) {
//        this.boletoContra = boletoContra.getId();
//    }
    public BoletoManual getBoletoManual() {
        return BoletoManual.getById(boletoManual);
    }

    public void setBoletoManual(BoletoManual boletoManual) {
        this.boletoManual = boletoManual.getId();
    }

    public BoletoCancelado getBoletoCancelado() {
        return BoletoCancelado.getById(boletoCancelado);
    }

    public void setBoletoCancelado(BoletoCancelado boletoCancelado) {
        this.boletoCancelado = boletoCancelado.getId();
    }

    public int getHorasTangibles() {
        return horas;
    }

    public void setHorasTangibles(int horasTangibles) {
        this.horas = horasTangibles;
    }

    public int getMinutosTangibles() {
        return minutos;
    }

    public void setMinutosTangibles(int minutosTangibles) {
        this.minutos = minutosTangibles;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public BoletoPerdido getBoletoPerdido() {
        return BoletoPerdido.getById(boletoPerdido);
    }

    public void setBoletoPerdido(BoletoPerdido boletoPerdido) {
        this.boletoPerdido = boletoPerdido.getId();
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getMontoTangible() {
        return monto;
    }

    public void setMontoTangible(float montoTangible) {
        this.monto = montoTangible;
    }

    public String getProgresivo() {
        return progresivo;
    }

    public void setProgresivo(String progresivo) {
        this.progresivo = progresivo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Tarifa getTarifa() {
        return Tarifa.getById(tarifa);
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa.getId();
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Turno getTurnoEntrada() {
        return Turno.getById(turnoEntrada);
    }

    public void setTurnoEntrada(Turno turnoEntrada) {
        this.turnoEntrada = turnoEntrada.getId();
    }

    public Turno getTurnoSalida() {
        return Turno.getById(turnoSalida);
    }

    public void setTurnoSalida(Turno turnoSalida) {
        this.turnoSalida = turnoSalida.getId();
    }

    public Caseta getCaseta() {
        return Caseta.getById(caseta);
    }

    public void setCaseta(Caseta caseta) {
        this.caseta = caseta.getId();
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getIdRemoto() {
        return idRemoto;
    }

    public void setIdRemoto(String idRemoto) {
        this.idRemoto = idRemoto;
    }
    
    

    public static Auto getById(int id) {
        Auto auto = null;
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT * FROM autos where id = ?");
            statement.setInt(1, id);
            ResultSet executeQuery = statement.executeQuery();
            if (executeQuery.next()) {
                auto = new Auto(executeQuery.getInt("id"), executeQuery.getString("progresivo"),
                        executeQuery.getString("matricula"), executeQuery.getString("fecha_entrada"),
                        executeQuery.getString("fecha_salida"), executeQuery.getString("hora_entrada"),
                        executeQuery.getString("hora_salida"), executeQuery.getString("marca"),
                        executeQuery.getString("modelo"), executeQuery.getString("color"),
                        executeQuery.getLong("id_boleto_perdido"), executeQuery.getLong("id_boleto_cancelado"),
                        executeQuery.getLong("id_boleto_manual"), executeQuery.getLong("id_boleto_contra"),
                        executeQuery.getString("entrada_salida").equals("E"),
                        executeQuery.getString("recibo").equals("SI"),
                        executeQuery.getString("boleto_perdido").equals("SI"),
                        executeQuery.getString("boleto_cancelado").equals("SI"),
                        executeQuery.getString("boleto_manual").equals("SI"),
                        executeQuery.getString("boleto_contra").equals("SI"),
                        executeQuery.getString("boleto_pendiente").equals("SI"),
                        executeQuery.getInt("horas_estadia"), executeQuery.getInt("minutos_estadia"),
                        executeQuery.getFloat("monto"), executeQuery.getLong("turno_entrada_id"),
                        executeQuery.getLong("turno_salida_id"), executeQuery.getString("serie"),
                        executeQuery.getString("notas"), executeQuery.getLong("id_tarifa"),
                        executeQuery.getFloat("descuento"), executeQuery.getString("clave"),
                        executeQuery.getLong("id_caseta"),
                        executeQuery.getString("id_remoto"),
                        executeQuery.getInt("estado_servidor")
                );
                if (auto.getBoletoPerdido() != null) {
                    auto.getBoletoPerdido().setAuto(auto);
                }
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return auto;
    }

    //////////////////////////////////////////////////////////////////////////
    //                                                                      //
    //                GENERAR BOLETOS PENDIENTES POR TURNO                  //
    //                                                                      //
    //////////////////////////////////////////////////////////////////////////

    public static List<Auto> guardarAutosPendientes(long idTurno) {
        ArrayList<Auto> autos = new ArrayList<>();
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT id,serie FROM autos where entrada_salida = 'E'");
            ResultSet executeQuery = statement.executeQuery();
            while (executeQuery.next()) {
                PreparedStatement statement2 = connectionDB.prepareStatement("INSERT INTO boleto_pendiente (`id_auto`,`id_turno_pendiente`,`serie`)"
                        + " VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
                statement2.setLong(1, executeQuery.getInt("id"));
                statement2.setLong(2, idTurno);
                statement2.setString(3, executeQuery.getString("serie"));

                statement2.executeUpdate();

            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return autos;
    }

    //////////////////////////////////////////////////////////////////////////
    //                                                                      //
    //                COBRAR VIRTUALEMTE LOS BOLETOS PENDIENTES             //
    //                                                                      //
    //////////////////////////////////////////////////////////////////////////

    public static ArrayList<Auto> calcularMontoAutosPendientes(ArrayList<Auto> autosPendientes) {
        Iterator<Auto> iterator = autosPendientes.iterator();
        ArrayList<Auto> autosCobradoVirtual = new ArrayList();
        while (iterator.hasNext()) {
            Auto auto = iterator.next();
            auto.setFechaSalida(Tiempo.getFecha());
            auto.setHoraSalida(Tiempo.getHora());
            auto.setHorasTangibles(Tiempo.getDirenciaHoras(auto.getFechaEntrada(), auto.getHoraEntrada(), auto.getFechaSalida(), auto.getHoraSalida()));
            auto.setMinutosTangibles(Tiempo.getDirenciaMinutos(auto.getFechaEntrada(), auto.getHoraEntrada(), auto.getFechaSalida(), auto.getHoraSalida()));
            auto.setMontoTangible(Tarifa.getImporteEstadia(auto));
            autosCobradoVirtual.add(auto);
        }
        return autosCobradoVirtual;
    }

     //////////////////////////////////////////////////////////////////////////
    //                                                                      //
    //        CONSULTAS PARA BOLETOS PENDIENTES TURNO ANTERIOR              //
    //                                                                      //
    //////////////////////////////////////////////////////////////////////////
    public static int getNumAutosPendientesTurnoActual(long idTurno, String key) {
        int nAutos = 0;
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT count(id) as id FROM autos where entrada_salida = 'E'  and serie = ?");
            statement.setString(1, key);
            ResultSet executeQuery = statement.executeQuery();
            if (executeQuery.next()) {
                nAutos = executeQuery.getInt("id");
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nAutos;
    }

    public static ArrayList<Auto> getAutosPendientesTurnoActual(long idTurno, String key) {
        ArrayList<Auto> autos = new ArrayList<>();
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT * FROM autos where entrada_salida = 'E' and serie = ?");
            statement.setString(1, key);
            ResultSet executeQuery = statement.executeQuery();
            while (executeQuery.next()) {
                autos.add(new Auto(executeQuery.getInt("id"), executeQuery.getString("progresivo"),
                        executeQuery.getString("matricula"), executeQuery.getString("fecha_entrada"),
                        executeQuery.getString("fecha_salida"), executeQuery.getString("hora_entrada"),
                        executeQuery.getString("hora_salida"), executeQuery.getString("marca"),
                        executeQuery.getString("modelo"), executeQuery.getString("color"),
                        executeQuery.getLong("id_boleto_perdido"), executeQuery.getLong("id_boleto_cancelado"),
                        executeQuery.getLong("id_boleto_manual"), executeQuery.getLong("id_boleto_contra"),
                        executeQuery.getString("entrada_salida").equals("E"),
                        executeQuery.getString("recibo").equals("SI"),
                        executeQuery.getString("boleto_perdido").equals("SI"),
                        executeQuery.getString("boleto_cancelado").equals("SI"),
                        executeQuery.getString("boleto_manual").equals("SI"),
                        executeQuery.getString("boleto_contra").equals("SI"),
                        executeQuery.getString("boleto_pendiente").equals("SI"),
                        executeQuery.getInt("horas_estadia"), executeQuery.getInt("minutos_estadia"),
                        executeQuery.getFloat("monto"), executeQuery.getLong("turno_entrada_id"),
                        executeQuery.getLong("turno_salida_id"), executeQuery.getString("serie"),
                        executeQuery.getString("notas"), executeQuery.getLong("id_tarifa"),
                        executeQuery.getFloat("descuento"), executeQuery.getString("clave"),
                        executeQuery.getLong("id_caseta"),
                        executeQuery.getString("id_remoto"),
                        executeQuery.getInt("estado_servidor")));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return autos;
    }

    //////////////////////////////////////////////////////////////////////////
    //                                                                      //
    //            CONSULTAS PARA BOLETOS PENDIENTES TURNO ACTUAL            //
    //                                                                      //
    //////////////////////////////////////////////////////////////////////////

    static long getNumAutosPendientesByTurno(long idTurno, String serie) {
        long nAutos = 0;
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT count(autos.id) as id  FROM autos,boleto_pendiente where boleto_pendiente.id_turno_pendiente = ?"
                            + " and boleto_pendiente.id_auto = autos.id  and boleto_pendiente.serie = autos.serie and  autos.serie = ?");
            statement.setLong(1, idTurno);
            statement.setString(2, serie);
            ResultSet executeQuery = statement.executeQuery();
            if (executeQuery.next()) {
                nAutos = executeQuery.getInt("id");
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nAutos;
    }

    public static ArrayList<Auto> getAutosPendientesByTurno(long idTurno, String serie) {
        ArrayList<Auto> autos = new ArrayList<>();
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT * FROM autos,boleto_pendiente where boleto_pendiente.id_turno_pendiente = ?"
                            + " and boleto_pendiente.id_auto = autos.id  and boleto_pendiente.serie = autos.serie and  autos.serie = ?");
            statement.setLong(1, idTurno);
            statement.setString(2, serie);
            ResultSet executeQuery = statement.executeQuery();
            while (executeQuery.next()) {
                
                autos.add(new Auto(executeQuery.getInt("id"), executeQuery.getString("progresivo"),
                        executeQuery.getString("matricula"), executeQuery.getString("fecha_entrada"),
                        executeQuery.getString("fecha_salida"), executeQuery.getString("hora_entrada"),
                        executeQuery.getString("hora_salida"), executeQuery.getString("marca"),
                        executeQuery.getString("modelo"), executeQuery.getString("color"),
                        executeQuery.getLong("id_boleto_perdido"), executeQuery.getLong("id_boleto_cancelado"),
                        executeQuery.getLong("id_boleto_manual"), executeQuery.getLong("id_boleto_contra"),
                        executeQuery.getString("entrada_salida").equals("E"),
                        executeQuery.getString("recibo").equals("SI"),
                        executeQuery.getString("boleto_perdido").equals("SI"),
                        executeQuery.getString("boleto_cancelado").equals("SI"),
                        executeQuery.getString("boleto_manual").equals("SI"),
                        executeQuery.getString("boleto_contra").equals("SI"),
                        executeQuery.getString("boleto_pendiente").equals("SI"),
                        executeQuery.getInt("horas_estadia"), executeQuery.getInt("minutos_estadia"),
                        executeQuery.getFloat("monto"), executeQuery.getLong("turno_entrada_id"),
                        executeQuery.getLong("turno_salida_id"), executeQuery.getString("serie"),
                        executeQuery.getString("notas"), executeQuery.getLong("id_tarifa"),
                        executeQuery.getFloat("descuento"), executeQuery.getString("clave"),
                        executeQuery.getLong("id_caseta"),
                        executeQuery.getString("id_remoto"),
                        executeQuery.getInt("estado_servidor")));
            
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return autos;
    }

    //////////////////////////////////////////////////////////////////////////
    //                                                                      //
    //            CONSULTAS PARA BOLETOS MANUALES TURNO ACTUAL            //
    //                                                                      //
    //////////////////////////////////////////////////////////////////////////
    static long getNumAutosManualesTurnoActual(long idTurno, String serie) {
        long nAutos = 0;
        try {
           Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT count(id) as id  FROM autos where turno_salida_id = ? and boleto_manual = 'SI' and serie = ?");
            statement.setLong(1, idTurno);
            statement.setString(2, serie);
            ResultSet executeQuery = statement.executeQuery();
            if (executeQuery.next()) {
                nAutos = executeQuery.getInt("id");
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nAutos;
    }

    public static ArrayList<Auto> getAutosManualesTurnoActual(long idTurno, String serie) {
        ArrayList<Auto> autos = new ArrayList<>();
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT * FROM autos where turno_salida_id = ? and boleto_manual = 'SI' and serie = ?");
            statement.setLong(1, idTurno);
            statement.setString(2, serie);
            ResultSet executeQuery = statement.executeQuery();
            while (executeQuery.next()) {
               
                autos.add(new Auto(executeQuery.getInt("id"), executeQuery.getString("progresivo"),
                        executeQuery.getString("matricula"), executeQuery.getString("fecha_entrada"),
                        executeQuery.getString("fecha_salida"), executeQuery.getString("hora_entrada"),
                        executeQuery.getString("hora_salida"), executeQuery.getString("marca"),
                        executeQuery.getString("modelo"), executeQuery.getString("color"),
                        executeQuery.getLong("id_boleto_perdido"), executeQuery.getLong("id_boleto_cancelado"),
                        executeQuery.getLong("id_boleto_manual"), executeQuery.getLong("id_boleto_contra"),
                        executeQuery.getString("entrada_salida").equals("E"),
                        executeQuery.getString("recibo").equals("SI"),
                        executeQuery.getString("boleto_perdido").equals("SI"),
                        executeQuery.getString("boleto_cancelado").equals("SI"),
                        executeQuery.getString("boleto_manual").equals("SI"),
                        executeQuery.getString("boleto_contra").equals("SI"),
                        executeQuery.getString("boleto_pendiente").equals("SI"),
                        executeQuery.getInt("horas_estadia"), executeQuery.getInt("minutos_estadia"),
                        executeQuery.getFloat("monto"), executeQuery.getLong("turno_entrada_id"),
                        executeQuery.getLong("turno_salida_id"), executeQuery.getString("serie"),
                        executeQuery.getString("notas"), executeQuery.getLong("id_tarifa"),
                        executeQuery.getFloat("descuento"), executeQuery.getString("clave"),
                        executeQuery.getLong("id_caseta"), 
                        executeQuery.getString("id_remoto"),
                        executeQuery.getInt("estado_servidor")));
            
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return autos;
    }

     //////////////////////////////////////////////////////////////////////////
    //                                                                      //
    //            CONSULTAS PARA BOLETOS CONTRA TURNO ACTUAL                //
    //                                                                      //
    //////////////////////////////////////////////////////////////////////////
    static long getNumAutosContraTurnoActual(long idTurno, String serie) {
        long nAutos = 0;
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT count(id) as id  FROM autos where turno_salida_id = ? and boleto_contra = 'SI' and serie = ?");
            statement.setLong(1, idTurno);
            statement.setString(2, serie);
            ResultSet executeQuery = statement.executeQuery();
            if (executeQuery.next()) {
                nAutos = executeQuery.getInt("id");
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nAutos;
    }

    public static ArrayList<Auto> getAutosContraTurnoActual(long idTurno, String serie) {
        ArrayList<Auto> autos = new ArrayList<>();
        try {
           Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT * FROM autos where turno_salida_id = ? and boleto_contra = 'SI' and serie = ?");
            statement.setLong(1, idTurno);
            statement.setString(2, serie);
            ResultSet executeQuery = statement.executeQuery();
            while (executeQuery.next()) {
                
                autos.add(new Auto(executeQuery.getInt("id"), executeQuery.getString("progresivo"),
                        executeQuery.getString("matricula"), executeQuery.getString("fecha_entrada"),
                        executeQuery.getString("fecha_salida"), executeQuery.getString("hora_entrada"),
                        executeQuery.getString("hora_salida"), executeQuery.getString("marca"),
                        executeQuery.getString("modelo"), executeQuery.getString("color"),
                        executeQuery.getLong("id_boleto_perdido"), executeQuery.getLong("id_boleto_cancelado"),
                        executeQuery.getLong("id_boleto_manual"), executeQuery.getLong("id_boleto_contra"),
                        executeQuery.getString("entrada_salida").equals("E"),
                        executeQuery.getString("recibo").equals("SI"),
                        executeQuery.getString("boleto_perdido").equals("SI"),
                        executeQuery.getString("boleto_cancelado").equals("SI"),
                        executeQuery.getString("boleto_manual").equals("SI"),
                        executeQuery.getString("boleto_contra").equals("SI"),
                        executeQuery.getString("boleto_pendiente").equals("SI"),
                        executeQuery.getInt("horas_estadia"), executeQuery.getInt("minutos_estadia"),
                        executeQuery.getFloat("monto"), executeQuery.getLong("turno_entrada_id"),
                        executeQuery.getLong("turno_salida_id"), executeQuery.getString("serie"),
                        executeQuery.getString("notas"), executeQuery.getLong("id_tarifa"),
                        executeQuery.getFloat("descuento"), executeQuery.getString("clave"),
                        executeQuery.getLong("id_caseta"), 
                        executeQuery.getString("id_remoto"),
                        executeQuery.getInt("estado_servidor")));
            
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return autos;
    }

    //////////////////////////////////////////////////////////////////////////
    //                                                                      //
    //            CONSULTAS PARA BOLETOS COBRADOS TURNO ACTUAL              //
    //                                                                      //
    //////////////////////////////////////////////////////////////////////////
    public static int getNumAutosCobradosTurnoActual(long idTurno, String key) {
        int nAutos = 0;
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT count(id) as id FROM autos where turno_salida_id = ? and "
                            + "boleto_perdido = 'NO' and boleto_cancelado = 'NO' "
                            + "and boleto_manual = 'NO' and boleto_contra = 'NO' and serie = ?");
            statement.setLong(1, idTurno);
            statement.setString(2, key);
            ResultSet executeQuery = statement.executeQuery();
            if (executeQuery.next()) {
                nAutos = executeQuery.getInt("id");
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nAutos;
    }

    public static ArrayList<Auto> getAutosCobradosTurnoActual(long idTurno, String key) {
        ArrayList<Auto> autos = new ArrayList<>();
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT * FROM autos where turno_salida_id = ? and "
                            + "boleto_perdido = 'NO' and boleto_cancelado = 'NO' "
                            + "and boleto_manual = 'NO' and boleto_contra = 'NO' and serie = ?");
            statement.setLong(1, idTurno);
            statement.setString(2, key);
            ResultSet executeQuery = statement.executeQuery();
            while (executeQuery.next()) {
                
                autos.add(new Auto(executeQuery.getInt("id"), executeQuery.getString("progresivo"),
                        executeQuery.getString("matricula"), executeQuery.getString("fecha_entrada"),
                        executeQuery.getString("fecha_salida"), executeQuery.getString("hora_entrada"),
                        executeQuery.getString("hora_salida"), executeQuery.getString("marca"),
                        executeQuery.getString("modelo"), executeQuery.getString("color"),
                        executeQuery.getLong("id_boleto_perdido"), executeQuery.getLong("id_boleto_cancelado"),
                        executeQuery.getLong("id_boleto_manual"), executeQuery.getLong("id_boleto_contra"),
                        executeQuery.getString("entrada_salida").equals("E"),
                        executeQuery.getString("recibo").equals("SI"),
                        executeQuery.getString("boleto_perdido").equals("SI"),
                        executeQuery.getString("boleto_cancelado").equals("SI"),
                        executeQuery.getString("boleto_manual").equals("SI"),
                        executeQuery.getString("boleto_contra").equals("SI"),
                        executeQuery.getString("boleto_pendiente").equals("SI"),
                        executeQuery.getInt("horas_estadia"), executeQuery.getInt("minutos_estadia"),
                        executeQuery.getFloat("monto"), executeQuery.getLong("turno_entrada_id"),
                        executeQuery.getLong("turno_salida_id"), executeQuery.getString("serie"),
                        executeQuery.getString("notas"), executeQuery.getLong("id_tarifa"),
                        executeQuery.getFloat("descuento"), executeQuery.getString("clave"),
                        executeQuery.getLong("id_caseta"),
                        executeQuery.getString("id_remoto"),
                        executeQuery.getInt("estado_servidor")));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return autos;
    }

    //////////////////////////////////////////////////////////////////////////
    //                                                                      //
    //            CONSULTAS PARA BOLETOS CANCELADOS TURNO ACTUAL            //
    //                                                                      //
    //////////////////////////////////////////////////////////////////////////
    public static long getNumAutosBoletoCanceladoTurnoActual(long idTurno, String key) {
        long nAutos = 0;
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT count(id) as id  FROM autos where turno_salida_id = ? and boleto_cancelado = 'SI' and serie = ?");
            statement.setLong(1, idTurno);
            statement.setString(2, key);
            ResultSet executeQuery = statement.executeQuery();
            if (executeQuery.next()) {
                nAutos = executeQuery.getInt("id");
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nAutos;
    }

    public static List<Auto> getAutosBoletoCanceladoTurnoActual(long idTurno, String key) {
        ArrayList<Auto> autos = new ArrayList<>();
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT * FROM autos where turno_salida_id = ? and boleto_cancelado = 'SI' and serie = ?");
            statement.setLong(1, idTurno);
            statement.setString(2, key);
            ResultSet executeQuery = statement.executeQuery();
            while (executeQuery.next()) {
              
                autos.add(new Auto(executeQuery.getInt("id"), executeQuery.getString("progresivo"),
                        executeQuery.getString("matricula"), executeQuery.getString("fecha_entrada"),
                        executeQuery.getString("fecha_salida"), executeQuery.getString("hora_entrada"),
                        executeQuery.getString("hora_salida"), executeQuery.getString("marca"),
                        executeQuery.getString("modelo"), executeQuery.getString("color"),
                        executeQuery.getLong("id_boleto_perdido"), executeQuery.getLong("id_boleto_cancelado"),
                        executeQuery.getLong("id_boleto_manual"), executeQuery.getLong("id_boleto_contra"),
                        executeQuery.getString("entrada_salida").equals("E"),
                        executeQuery.getString("recibo").equals("SI"),
                        executeQuery.getString("boleto_perdido").equals("SI"),
                        executeQuery.getString("boleto_cancelado").equals("SI"),
                        executeQuery.getString("boleto_manual").equals("SI"),
                        executeQuery.getString("boleto_contra").equals("SI"),
                        executeQuery.getString("boleto_pendiente").equals("SI"),
                        executeQuery.getInt("horas_estadia"), executeQuery.getInt("minutos_estadia"),
                        executeQuery.getFloat("monto"), executeQuery.getLong("turno_entrada_id"),
                        executeQuery.getLong("turno_salida_id"), executeQuery.getString("serie"),
                        executeQuery.getString("notas"), executeQuery.getLong("id_tarifa"),
                        executeQuery.getFloat("descuento"), executeQuery.getString("clave"),
                        executeQuery.getLong("id_caseta"), 
                        executeQuery.getString("id_remoto"),
                        executeQuery.getInt("estado_servidor")));
            
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return autos;
    }

    //////////////////////////////////////////////////////////////////////////
    //                                                                      //
    //            CONSULTAS PARA BOLETOS PERDIDOS TURNO ACTUAL              //
    //                                                                      //
    //////////////////////////////////////////////////////////////////////////
    public static long getNumAutosBoletoPerdidoTurnoActual(long idTurno, String key) {
        long nAutos = 0;
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT count(id) as id  FROM autos where turno_salida_id = ? and  boleto_perdido = 'SI' and serie = ?");
            statement.setLong(1, idTurno);
            statement.setString(2, key);
            ResultSet executeQuery = statement.executeQuery();
            if (executeQuery.next()) {
                nAutos = executeQuery.getInt("id");
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nAutos;
    }

    public static List<Auto> getAutosBoletoPerdidoTurnoActual(long idTurno, String key) {
        ArrayList<Auto> autos = new ArrayList<>();
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT * FROM autos where turno_salida_id = ? and boleto_perdido = 'SI' and serie = ?");
            statement.setLong(1, idTurno);
            statement.setString(2, key);
            ResultSet executeQuery = statement.executeQuery();
            while (executeQuery.next()) {
               
                autos.add(new Auto(executeQuery.getInt("id"), executeQuery.getString("progresivo"),
                        executeQuery.getString("matricula"), executeQuery.getString("fecha_entrada"),
                        executeQuery.getString("fecha_salida"), executeQuery.getString("hora_entrada"),
                        executeQuery.getString("hora_salida"), executeQuery.getString("marca"),
                        executeQuery.getString("modelo"), executeQuery.getString("color"),
                        executeQuery.getLong("id_boleto_perdido"), executeQuery.getLong("id_boleto_cancelado"),
                        executeQuery.getLong("id_boleto_manual"), executeQuery.getLong("id_boleto_contra"),
                        executeQuery.getString("entrada_salida").equals("E"),
                        executeQuery.getString("recibo").equals("SI"),
                        executeQuery.getString("boleto_perdido").equals("SI"),
                        executeQuery.getString("boleto_cancelado").equals("SI"),
                        executeQuery.getString("boleto_manual").equals("SI"),
                        executeQuery.getString("boleto_contra").equals("SI"),
                        executeQuery.getString("boleto_pendiente").equals("SI"),
                        executeQuery.getInt("horas_estadia"), executeQuery.getInt("minutos_estadia"),
                        executeQuery.getFloat("monto"), executeQuery.getLong("turno_entrada_id"),
                        executeQuery.getLong("turno_salida_id"), executeQuery.getString("serie"),
                        executeQuery.getString("notas"), executeQuery.getLong("id_tarifa"),
                        executeQuery.getFloat("descuento"), executeQuery.getString("clave"),
                        executeQuery.getLong("id_caseta"), 
                        executeQuery.getString("id_remoto"),
                        executeQuery.getInt("estado_servidor")));
            
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return autos;
    }

    //////////////////////////////////////////////////////////////////////////
    //                                                                      //
    //                                                                      //
    //////////////////////////////////////////////////////////////////////////
    public static ArrayList<Auto> getAutosPorSerie(Turno turno, String key) {
        ArrayList<Auto> autos = new ArrayList<>();
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT * FROM autos where turno_entrada_id = ? and serie = ?");
            statement.setLong(1, turno.getId());
            statement.setString(2, key);
            ResultSet executeQuery = statement.executeQuery();
            while (executeQuery.next()) {
                
                autos.add(new Auto(executeQuery.getInt("id"), executeQuery.getString("progresivo"),
                        executeQuery.getString("matricula"), executeQuery.getString("fecha_entrada"),
                        executeQuery.getString("fecha_salida"), executeQuery.getString("hora_entrada"),
                        executeQuery.getString("hora_salida"), executeQuery.getString("marca"),
                        executeQuery.getString("modelo"), executeQuery.getString("color"),
                        executeQuery.getLong("id_boleto_perdido"), executeQuery.getLong("id_boleto_cancelado"),
                        executeQuery.getLong("id_boleto_manual"), executeQuery.getLong("id_boleto_contra"),
                        executeQuery.getString("entrada_salida").equals("E"),
                        executeQuery.getString("recibo").equals("SI"),
                        executeQuery.getString("boleto_perdido").equals("SI"),
                        executeQuery.getString("boleto_cancelado").equals("SI"),
                        executeQuery.getString("boleto_manual").equals("SI"),
                        executeQuery.getString("boleto_contra").equals("SI"),
                        executeQuery.getString("boleto_pendiente").equals("SI"),
                        executeQuery.getInt("horas_estadia"), executeQuery.getInt("minutos_estadia"),
                        executeQuery.getFloat("monto"), executeQuery.getLong("turno_entrada_id"),
                        executeQuery.getLong("turno_salida_id"), executeQuery.getString("serie"),
                        executeQuery.getString("notas"), executeQuery.getLong("id_tarifa"),
                        executeQuery.getFloat("descuento"), executeQuery.getString("clave"),
                        executeQuery.getLong("id_caseta"), 
                        executeQuery.getString("id_remoto"),
                        executeQuery.getInt("estado_servidor")));
            
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return autos;
    }

    
    public static ArrayList<Auto> getAutosOffline() {
        ArrayList<Auto> autos = new ArrayList<>();
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT * FROM autos where (id_remoto is null) or (id_remoto = 1 and entrada_salida = 'S') ");

            ResultSet executeQuery = statement.executeQuery();
            while (executeQuery.next()) {
                
                autos.add(new Auto(executeQuery.getInt("id"), executeQuery.getString("progresivo"),
                        executeQuery.getString("matricula"), executeQuery.getString("fecha_entrada"),
                        executeQuery.getString("fecha_salida"), executeQuery.getString("hora_entrada"),
                        executeQuery.getString("hora_salida"), executeQuery.getString("marca"),
                        executeQuery.getString("modelo"), executeQuery.getString("color"),
                        executeQuery.getLong("id_boleto_perdido"), executeQuery.getLong("id_boleto_cancelado"),
                        executeQuery.getLong("id_boleto_manual"), executeQuery.getLong("id_boleto_contra"),
                        executeQuery.getString("entrada_salida").equals("E"),
                        executeQuery.getString("recibo").equals("SI"),
                        executeQuery.getString("boleto_perdido").equals("SI"),
                        executeQuery.getString("boleto_cancelado").equals("SI"),
                        executeQuery.getString("boleto_manual").equals("SI"),
                        executeQuery.getString("boleto_contra").equals("SI"),
                        executeQuery.getString("boleto_pendiente").equals("SI"),
                        executeQuery.getInt("horas_estadia"), executeQuery.getInt("minutos_estadia"),
                        executeQuery.getFloat("monto"), executeQuery.getLong("turno_entrada_id"),
                        executeQuery.getLong("turno_salida_id"), executeQuery.getString("serie"),
                        executeQuery.getString("notas"), executeQuery.getLong("id_tarifa"),
                        executeQuery.getFloat("descuento"), executeQuery.getString("clave"),
                        executeQuery.getLong("id_caseta"), 
                        executeQuery.getString("id_remoto"),
                        executeQuery.getInt("estado_servidor")));
            
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return autos;
    }
    
    public static List<String> getSeries() {
        ArrayList<String> series = new ArrayList();
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT DISTINCT serie  FROM autos ");
            ResultSet executeQuery = statement.executeQuery();
            while (executeQuery.next()) {
                series.add(executeQuery.getString("serie"));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return series;
    }

    public static List<Auto> ordenarByProgresivo(List<Auto> autos) {
        for (int i = 0; i < autos.size() - 1; i++) {
            for (int j = i + 1; j < autos.size(); j++) {
                if (Long.valueOf(autos.get(i).getProgresivo()) > Long.valueOf(autos.get(j).getProgresivo())) {
                    Auto aux = autos.get(i);
                    autos.set(i, autos.get(j));
                    autos.set(j, aux);
                }
            }
        }
        return autos;
    }

    public static Auto getAutoByMatricula(String matricula) {
        Auto auto = null;
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT id FROM autos where matricula = ? and entrada_salida = 'E'");
            statement.setString(1, matricula);

            ResultSet executeQuery = statement.executeQuery();
            if (executeQuery.next()) {
                auto = Auto.getById(executeQuery.getInt("id"));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return auto;
    }
    
    

    public static Auto getAutoByProgresivo(int id) {
        Auto auto = null;
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT id FROM autos where progresivo = ?");
            statement.setInt(1, id);
            ResultSet executeQuery = statement.executeQuery();
            if (executeQuery.next()) {
                auto = Auto.getById(executeQuery.getInt("id"));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return auto;
    }

    public static Auto getByProgresivoClave(String clave) {
        Auto auto = null;
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT id FROM autos where clave = ? and serie = ? and progresivo = ? ");
            statement.setString(1, clave.substring(0, 5));
            statement.setString(2, clave.substring(5, 6));
            statement.setString(3, clave.substring(6, 12));

            ResultSet executeQuery = statement.executeQuery();
            if (executeQuery.next()) {
                auto = Auto.getById(executeQuery.getInt("id"));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return auto;
    }

    public static Auto getByProgresivoClaveContra(String clave) {
        Auto auto = null;
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT id FROM autos where serie = ? and progresivo = ? ");
            statement.setString(1, clave.substring(5, 6));
            statement.setString(2, clave.substring(6, 12));

            ResultSet executeQuery = statement.executeQuery();
            if (executeQuery.next()) {
                auto = Auto.getById(executeQuery.getInt("id"));
                if (!(auto.getClave().substring(0, 4).equals(clave.substring(0, 4)) && clave.substring(4, 5).equals("."))) {
                    auto = null;
                }
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return auto;
    }

    static long getPrimerProgresivoPorSerie(Turno turno, String key) {
        ArrayList<Auto> autos = new ArrayList<>();
        Long progresivo = 0L;
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT min(progresivo) as progresivo FROM autos where turno_entrada_id = ? and  serie = ?");
            statement.setLong(1, turno.getId());
            statement.setString(2, key);
            ResultSet executeQuery = statement.executeQuery();
            while (executeQuery.next()) {
                progresivo = executeQuery.getLong("progresivo");
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return progresivo;
    }

    static long getUltimoProgresivoPorSerie(Turno turno, String key) {
        ArrayList<Auto> autos = new ArrayList<>();
        Long progresivo = 0L;
        try {
           Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT max(progresivo)  as progresivo FROM autos where turno_entrada_id = ? and serie = ?");
            statement.setLong(1, turno.getId());
            statement.setString(2, key);
            ResultSet executeQuery = statement.executeQuery();
            while (executeQuery.next()) {
                progresivo = executeQuery.getLong("progresivo");
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return progresivo;
    }

    public static long getUltimoProgresivo() {
        long ultimoProgresivo = 0;
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            ResultSet resulSet
                    = connectionDB.prepareStatement("SELECT MAX(progresivo) AS progresivo FROM autos").executeQuery();
            if (resulSet.first()) {
                ultimoProgresivo = resulSet.getLong("progresivo");
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ultimoProgresivo;
    }

    public static Auto getCambioEstadoServidor() {
        Auto auto = null;
        try {
           Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT id FROM autos where estado_servidor > 0");
            ResultSet executeQuery = statement.executeQuery();
            if (executeQuery.next()) {
                auto = Auto.getById(executeQuery.getInt("id"));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return auto;
    }

    public void actualizar() {
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("UPDATE autos SET `progresivo`=? ,`matricula` =? "
                            + ", `fecha_entrada` =? ,`fecha_salida` =? ,`hora_entrada` =? "
                            + ",`hora_salida` =?,`marca` =? ,`modelo` =? ,`color` =?"
                            + ",`id_boleto_perdido` =? ,`id_boleto_cancelado` =?"
                            + ",`id_boleto_manual` =? ,`id_boleto_contra` =? "
                            + ",`entrada_salida` =? ,`recibo` =?"
                            + ",`boleto_perdido` =? ,`boleto_cancelado` =?"
                            + ",`boleto_manual` =? ,`boleto_contra` =? ,`boleto_pendiente` =? "
                            + ",`horas_estadia` =? ,`minutos_estadia` =?, `monto` =? "
                            + ",`turno_salida_id` =? ,`serie` =? ,notas = ?, id_tarifa = ?,"
                            + "descuento = ? , id_remoto = ?  , estado_servidor = ?  WHERE `id`=?");
            statement.setString(1, progresivo);
            statement.setString(2, matricula);
            statement.setString(3, fechaEntrada);
            statement.setString(4, fechaSalida);
            statement.setString(5, horaEntrada);
            statement.setString(6, horaSalida);
            statement.setString(7, marca);
            statement.setString(8, modelo);
            statement.setString(9, color);
            statement.setLong(10, boletoPerdido);
            statement.setLong(11, boletoCancelado);
            statement.setLong(12, boletoManual);
            statement.setLong(13, boletoContra);
            statement.setString(14, isDentro ? "E" : "S");
            statement.setString(15, isReciboImpreso ? "SI" : "NO");
            statement.setString(16, isBoletoPerdido ? "SI" : "NO");
            statement.setString(17, isBoletoCancelado ? "SI" : "NO");
            statement.setString(18, isBoletoManual ? "SI" : "NO");
            statement.setString(19, isBoletoContra ? "SI" : "NO");
            statement.setString(20, isBoletoPendiente ? "SI" : "NO");
            statement.setFloat(21, horas);
            statement.setFloat(22, minutos);
            statement.setFloat(23, monto);
            statement.setLong(24, turnoSalida);
            statement.setString(25, serie);
            statement.setString(26, nota);
            statement.setLong(27, tarifa);
            statement.setFloat(28, descuento);
            statement.setString(29, idRemoto);
            statement.setInt(30, estadoServidor);
            
            
            statement.setInt(31, id);

            statement.executeUpdate();
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizarEstado() {
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("UPDATE autos SET `boleto_perdido` =? ,`boleto_cancelado` =?"
                            + ",`boleto_manual` =? ,`boleto_contra` =? ,`boleto_pendiente` = ? WHERE `id` =?");
            statement.setString(1, isBoletoPerdido ? "SI" : "NO");
            statement.setString(2, isBoletoCancelado ? "SI" : "NO");
            statement.setString(3, isBoletoManual ? "SI" : "NO");
            statement.setString(4, isBoletoContra ? "SI" : "NO");
            statement.setString(5, isBoletoPendiente ? "SI" : "NO");
            statement.setInt(6, id);
            statement.executeUpdate();
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    public void actualizarTarifa() {
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("UPDATE autos SET `id_tarifa` = ? WHERE `id` =?");
            statement.setLong(1, tarifa);
            statement.setInt(2, id);
            statement.executeUpdate();
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardar() {
        try {
           Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("INSERT INTO autos (`progresivo`, `notas`,`clave`, `matricula`,"
                            + " `id_tarifa`,`fecha_entrada`,`hora_entrada`,"
                            + " `operador_entrada`,`id_caseta`,"
                            + " `turno_entrada_id`,`marca`,`serie` )"
                            + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, progresivo);
            statement.setString(2, nota);
            statement.setString(3, clave);
            statement.setString(4, matricula);
            statement.setLong(5, tarifa);
            statement.setString(6, fechaEntrada);
            statement.setString(7, horaEntrada);
            statement.setLong(8, Turno.getById(turnoEntrada).getEmpleadoEntrada().getId());
            statement.setLong(9, caseta);
            statement.setLong(10, turnoEntrada);
            statement.setString(11, marca);
            statement.setString(12, serie);

            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt("GENERATED_KEY");
            }
            conexion.cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
