
package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Auto {

  
    int id;
    boolean dentro;
    String progresivo;
    String clave;
    String matricula;
    Tarifa tarifa;
    String fechaEntrada;
    String fechaSalida;
    String horaEntrada;
    String horaSalida;
    Turno turnoEntrada;
    Turno turnoSalida;
    int horasTangibles;
    int minutosTangibles;
    Caseta caseta;
    float montoTangible;
    String marca;
    String modelo;
    String color;
    BoletoPerdido boletoPerdido;
    BoletoCancelado boletoCancelado;

    public Auto(int id , String progresivo, String clave, String matricula, Tarifa tarifa, 
            String fechaEntrada, String fechaSalida, String horaEntrada, String horaSalida, 
            Turno turnoEntrada, Turno turnoSalida,int horasTangibles,int minutosTangibles, 
            Caseta caseta, float montoTangible, String marca, String modelo, String color,
            BoletoPerdido boletoPerdido, BoletoCancelado boletoCancelado) {
        this.id = id;
        this.clave = clave;
        this.progresivo = progresivo;
        this.matricula = matricula;
        this.tarifa = tarifa;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.turnoEntrada = turnoEntrada;
        this.turnoSalida = turnoSalida;
        this.horasTangibles = horasTangibles;
        this.minutosTangibles = minutosTangibles;
        this.caseta = caseta;
        this.montoTangible = montoTangible;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.boletoPerdido = boletoPerdido;
        this.boletoCancelado = boletoCancelado;
    }
    

    public boolean isDentro() {
        return dentro;
    }
    public void setDentro(boolean n) {
        dentro = n;
    }

    public BoletoCancelado getBoletoCancelado() {
        return boletoCancelado;
    }

    public void setBoletoCancelado(BoletoCancelado boletoCancelado) {
        this.boletoCancelado = boletoCancelado;
    }
    
    
    public int getHorasTangibles() {
        return horasTangibles;
    }

    public void setHorasTangibles(int horasTangibles) {
        this.horasTangibles = horasTangibles;
    }

    public int getMinutosTangibles() {
        return minutosTangibles;
    }

    public void setMinutosTangibles(int minutosTangibles) {
        this.minutosTangibles = minutosTangibles;
    }


   
    
    public Auto() {
    }

    public BoletoPerdido getBoletoPerdido() {
        return boletoPerdido;
    }

    public void setBoletoPerdido(BoletoPerdido boletoPerdido) {
        this.boletoPerdido = boletoPerdido;
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
        return montoTangible;
    }

    public void setMontoTangible(float montoTangible) {
        this.montoTangible = montoTangible;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
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
        return turnoEntrada;
    }

    public void setTurnoEntrada(Turno turnoEntrada) {
        this.turnoEntrada = turnoEntrada;
    }

    public Turno getTurnoSalida() {
        return turnoSalida;
    }

    public void setTurnoSalida(Turno turnoSalida) {
        this.turnoSalida = turnoSalida;
    }

    public Caseta getCaseta() {
        return caseta;
    }

    public void setCaseta(Caseta caseta) {
        this.caseta = caseta;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    
      public static Auto getAutoByMatricula(String matricula) {
       Auto auto = null;
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT id FROM tbl_entradas_parking where matricula = ?");
            statement.setString(1, matricula);
            ResultSet executeQuery = statement.executeQuery();
            if (executeQuery.next()){
                auto = Auto.getById(executeQuery.getInt("id"));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return auto;
    }
    public static Auto getAutoByProgresivo(int id){
        Auto auto = null;
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT id FROM tbl_entradas_parking where progresivo = ?");
            statement.setInt(1, id);
            ResultSet executeQuery = statement.executeQuery();
            if (executeQuery.next()){
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
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT id FROM tbl_entradas_parking where clave = ? and progresivo = ?");
            statement.setString(1, clave.substring(0,6));
            statement.setString(2, clave.substring(6,12));
            
            ResultSet executeQuery = statement.executeQuery();
            if (executeQuery.next()){
                auto = Auto.getById(executeQuery.getInt("id"));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return auto;
    }

  

    public static Auto getById(int id){
        Auto auto = null;
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT * FROM tbl_entradas_parking where id = ?");
            statement.setInt(1, id);
            ResultSet executeQuery = statement.executeQuery();
            if (executeQuery.next()){
                auto = new Auto(executeQuery.getInt("id"),executeQuery.getString("progresivo"),
                executeQuery.getString("clave"),executeQuery.getString("matricula"),
                Tarifa.getById(executeQuery.getInt("id_tarifa")),executeQuery.getString("fecha_entrada"),
                executeQuery.getString("fecha_salida"),executeQuery.getString("hora_entrada"),
                executeQuery.getString("hora_salida"),Turno.getById(executeQuery.getLong("turno_entrada_id")),
                Turno.getById(executeQuery.getLong("turno_salida_id")),executeQuery.getInt("horas_estadia"),
                executeQuery.getInt("minutos_estadia"),Caseta.getById(executeQuery.getInt("id_caseta"))
                ,executeQuery.getFloat("monto_tangible"),executeQuery.getString("marca"),
                executeQuery.getString("modelo"),executeQuery.getString("color"),
                BoletoPerdido.getById(executeQuery.getInt("id_boleto_perdido")),
                BoletoCancelado.getBoletoCanceladoById(executeQuery.getInt("id_boleto_cancelado"))
                );
                if(auto.getBoletoPerdido()!=null)
                    auto.getBoletoPerdido().setAuto(auto);
            }
           
            if (executeQuery.getString("entrada_salida").equals("E")){
                auto.dentro = true;
            }else
                auto.dentro = false;
            
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return auto;
    }
    public static List<Auto> getAutosPendientes(){
        ArrayList <Auto> autos = new ArrayList<Auto>();
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT id FROM tbl_entradas_parking where entrada_salida = 'E'");
            ResultSet executeQuery = statement.executeQuery();
            while (executeQuery.next()){
                autos.add(Auto.getById(executeQuery.getInt("id")));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return autos;
    }
    public static List<Auto> getAutosPendientes(Turno turno){
        ArrayList <Auto> autos = new ArrayList<Auto>();
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT id FROM tbl_entradas_parking where entrada_salida = 'E' and turno_entrada_id = ?");
            statement.setLong(1, turno.getId());
            ResultSet executeQuery = statement.executeQuery();
            while (executeQuery.next()){
                autos.add(Auto.getById(executeQuery.getInt("id")));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return autos;
    }
    public static List<Auto>  getAutosBoletoPerdidoTurnoActual(Turno turno) {
        ArrayList <Auto> autos = new ArrayList<Auto>();
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT id FROM tbl_entradas_parking where turno_salida_id = ? and id_boleto_perdido > 0");
            statement.setLong(1, turno.getId());
            
            ResultSet executeQuery = statement.executeQuery();
            while (executeQuery.next()){
                   autos.add(Auto.getById(executeQuery.getInt("id")));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return autos;
    }
    public static List<Auto>  getAutosBoletoCanceladoTurnoActual(Turno turno) {
        ArrayList <Auto> autos = new ArrayList<Auto>();
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT id FROM tbl_entradas_parking where turno_salida_id = ? and id_boleto_cancelado > 0");
            statement.setLong(1, turno.getId());
            
            ResultSet executeQuery = statement.executeQuery();
            while (executeQuery.next()){
                   autos.add(Auto.getById(executeQuery.getInt("id")));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return autos;
    }
    
    public static List<Auto> getAutosCobradosTurnoActual(Turno turno){
        ArrayList <Auto> autos = new ArrayList<Auto>();
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT id FROM tbl_entradas_parking where turno_salida_id = ? and id_boleto_perdido = 0 and id_boleto_cancelado = 0");
            statement.setLong(1, turno.getId());
            
            ResultSet executeQuery = statement.executeQuery();
            while (executeQuery.next()){
                   autos.add(Auto.getById(executeQuery.getInt("id")));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return autos;
    }
    
    public static long getUltimoProgresivo(){
        long ultimoProgresivo = 0;
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            ResultSet resulSet = 
                    connectionDB.prepareStatement("SELECT MAX(progresivo) AS progresivo FROM tbl_entradas_parking").executeQuery();
            if (resulSet.first())
                ultimoProgresivo = resulSet.getLong("progresivo");
          
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ultimoProgresivo;
    } 

    public void actualizar() {
        try {
             Conexion conexion = new Conexion();
             Connection connectionDB = conexion.getConnectionDB();
             PreparedStatement  statement = connectionDB.
             prepareStatement("UPDATE tbl_entradas_parking SET `progresivo`=? "+
                     ",`matricula` =? , `fecha_entrada` =? ,`fecha_salida` =? "+
                     ",`hora_entrada` =? ,`hora_salida` =?,`marca` =?  "+
                     ",`modelo` =? ,`color` =?,`id_boleto_perdido` =?  "+
                      ",`horas_estadia` =? ,`minutos_estadia` =?,`monto_tangible` =?  "+
                      ",`turno_salida_id` =? ,`entrada_salida` =? ,`id_boleto_cancelado` =? "+
                     "WHERE `id`=?");
             statement.setString(1, progresivo);
             statement.setString(2, matricula);
             statement.setString(3, fechaEntrada);
             statement.setString(4, fechaSalida);
             statement.setString(5, horaEntrada);
             statement.setString(6, horaSalida);
             statement.setString(7, marca);
             statement.setString(8, modelo);
             statement.setString(9, color);
             statement.setInt(10, boletoPerdido != null ? boletoPerdido.getId() : 0 );
             statement.setFloat(11, horasTangibles);
             statement.setFloat(12, minutosTangibles);
             statement.setFloat(13, montoTangible);
             statement.setLong(14, turnoSalida.getId());
             statement.setString(15, dentro ? "E" : "S" );
             statement.setInt(16, boletoCancelado != null ? boletoCancelado.getId() : 0 );
             statement.setInt(17, id);
  
             statement.executeUpdate();
             conexion.cerrarConexion();
         } catch (SQLException ex) {
             Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
         } 
    }

    public void guardar() {
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("INSERT INTO tbl_entradas_parking (`progresivo`,`clave`, `matricula`,"+
                            " `id_tarifa`,`fecha_entrada`,`hora_entrada`,"+
                            " `operador_entrada`,`id_caseta`,"+
                            " `turno_entrada_id`,`marca`)"+
                            " VALUES (?,?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, progresivo);
            statement.setString(2, clave);
            statement.setString(3, matricula);
            statement.setLong(4, tarifa.getId());
            statement.setString(5, fechaEntrada); 
            statement.setString(6, horaEntrada);
            statement.setLong(7, turnoEntrada.getEmpleado().getId());
            statement.setLong(8, caseta.getId()); 
            statement.setLong(9, turnoEntrada.getId());
            statement.setString(10, marca);
            
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
