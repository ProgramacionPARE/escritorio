package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DetallesMovimiento{

    

 
    private String rangoHorario;
    private String tipo;
    private int noBol;
    private float precioUnitario;
    private float importe;

    public static DetallesMovimiento existePrecioUnitario(ArrayList <DetallesMovimiento> dt,float precioUnitario){
        DetallesMovimiento resp=null;
        Iterator<DetallesMovimiento> iterator = dt.iterator(); 
        while(iterator.hasNext()){
            DetallesMovimiento next = iterator.next();
            if(next.getPrecioUnitario()== precioUnitario){
                resp = next;
                break;
            }
        }
       return resp;
    }
    
    private static DetallesMovimiento existeRangoHorario(ArrayList<DetallesMovimiento> dt, String rangoEstadia) {
        DetallesMovimiento resp=null;
        Iterator<DetallesMovimiento> iterator = dt.iterator(); 
        while(iterator.hasNext()){
            DetallesMovimiento next = iterator.next();
            if(next.getRangoHorario().equals(rangoEstadia)){
                resp = next;
                break;
            }
        }
       return resp;
    }
    
    static void guardar(ArrayList<DetallesMovimiento> detallesMovimiento, Long id){
        for(DetallesMovimiento d : detallesMovimiento){
            try {
                Conexion conexion = new Conexion();
                Connection connectionDB = conexion.getConnectionDB();
                PreparedStatement  statement = connectionDB.
                prepareStatement("INSERT INTO detalles_movimiento (`cantidad_boletos`,"+
                        " `precio_unitario`, `importe`,`id_turno`,`tipo`,`rango_horario`)"
                                + " VALUES (?,?,?,?,?,?)");
                statement.setInt(1, d.getNoBol());
                statement.setFloat(2, d.getPrecioUnitario());
                statement.setFloat(3, d.getImporte());
                statement.setLong(4, id);
                statement.setString(5, d.getTipo());
                statement.setString(6, d.getRangoHorario());
                statement.executeUpdate();
                conexion.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
     public static ArrayList<DetallesMovimiento> getByTurnoId(Long turno_id){
        ArrayList<DetallesMovimiento> dm= new ArrayList<DetallesMovimiento>();
         try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT * FROM detalles_movimiento where id_turno = ?");
            statement.setLong(1, turno_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                dm.add(new DetallesMovimiento(resultSet.getString("rango_horario"),
                        resultSet.getString("tipo"),resultSet.getInt("cantidad_boletos") ,
                        resultSet.getFloat("precio_unitario"), resultSet.getFloat("importe")));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
         ordenarPorPU(dm);
         return dm;
     }
    
    public static float calcularTotal(ArrayList<DetallesMovimiento> detallesMovimiento){
        float   total = 0;
        for(DetallesMovimiento d : detallesMovimiento){
            total+= d.getImporte();
        }
        return total;
    }
    
    public static void ordenarPorPU(ArrayList<DetallesMovimiento> detallesMovimiento){
        for(int i =0 ; i< detallesMovimiento.size()-1;i++){
            for(int j =i+1 ; j< detallesMovimiento.size();j++){
                if(detallesMovimiento.get(i).getPrecioUnitario()> detallesMovimiento.get(j).getPrecioUnitario()
                        || detallesMovimiento.get(i).getTipo().equals("Cancelado")
                        || detallesMovimiento.get(i).getTipo().equals("Perdido")){
                   DetallesMovimiento aux = detallesMovimiento.get(i);
                   detallesMovimiento.set(i,detallesMovimiento.get(j));
                   detallesMovimiento.set(j,aux);
                }
            }
       }
       
    }
    
    public static ArrayList<DetallesMovimiento> generarDetalles(List<Auto> autosCobradosTurnoActual,
            List<Auto> autosBoletoPerdidoTurnoActual, List<Auto> autosBoletoCanceladoTurnoActual){
       
        ArrayList<DetallesMovimiento> detallesMovimiento= new ArrayList<DetallesMovimiento>();
        Iterator<Auto> iteratorAutos = autosCobradosTurnoActual.iterator();
        while (iteratorAutos.hasNext()){
            Auto next = iteratorAutos.next();
            //DetallesMovimiento detalles = DetallesMovimiento.existePrecioUnitario(detallesMovimiento, next.getMontoTangible());
            DetallesMovimiento detalles = DetallesMovimiento.existeRangoHorario(detallesMovimiento, Tarifa.getRangoEstadia(next));
            if(detalles==null){
                detallesMovimiento.add(new DetallesMovimiento(Tarifa.getRangoEstadia(next), "Boleto", 1, next.getMontoTangible(), next.getMontoTangible()));
            }else{
                detalles.setNoBol( detalles.getNoBol()+1);
                detalles.setImporte(detalles.getPrecioUnitario()*detalles.getNoBol());
            }
           
        }
        //Boleto perdidos
        if(autosBoletoPerdidoTurnoActual.size()>0){
            detallesMovimiento.add(new DetallesMovimiento("", "Perdido", 
            autosBoletoPerdidoTurnoActual.size(),
            autosBoletoPerdidoTurnoActual.get(0).getTarifa().getPrecioBoletoPerdido(),
            autosBoletoPerdidoTurnoActual.size()* autosBoletoPerdidoTurnoActual.get(0).getTarifa().getPrecioBoletoPerdido()));
        } 
        //Boletos cancelados
        if(autosBoletoCanceladoTurnoActual.size()>0){
            detallesMovimiento.add(new DetallesMovimiento("", "Cancelado", 
            autosBoletoCanceladoTurnoActual.size(),0, 0));
        } 
        return detallesMovimiento;
    }
    public DetallesMovimiento(String rangohorario, String tipo, int noBol, float precioUnitario, float importe) {
        this.rangoHorario = rangohorario;
        this.tipo = tipo;
        this.noBol = noBol;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
    }

    public DetallesMovimiento() {
    }

    public String getRangoHorario() {
        return rangoHorario;
    }

    public void setRangoHorario(String rangoHorario) {
        this.rangoHorario = rangoHorario;
    }

  

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNoBol() {
        return noBol;
    }

    public void setNoBol(int noBol) {
        this.noBol = noBol;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    @Override
    public String toString() {
        return "DetallesMoviemiento{" + "rangohorario=" + rangoHorario + ", tipo=" + tipo + ", noBol=" + noBol + ", precioUnitario=" + precioUnitario + ", importe=" + importe + '}';
    }

    }