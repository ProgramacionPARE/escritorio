package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
    
    private static DetallesMovimiento existeRangoHorario(ArrayList<DetallesMovimiento> dt, Auto auto,String tipo) {
        DetallesMovimiento resp=null;
        String rangoEstadia = Tarifa.getRangoEstadia(auto);
        Iterator<DetallesMovimiento> iterator = dt.iterator(); 
        while(iterator.hasNext()){
            DetallesMovimiento next = iterator.next();
            if((next.getRangoHorario().equals(rangoEstadia) || next.getImporte()==auto.getMontoTangible() ) && next.getTipo().equals(tipo)){
                resp = next;
                break;
            }
        }
       return resp;
    }
    
    static void guardar(ArrayList<DetallesMovimiento>  detallesMovimiento, Long id){

            
            for(DetallesMovimiento d : detallesMovimiento){
                try {
                    Conexion conexion = new Conexion();
                    Connection connectionDB = conexion.getConnectionDB();
                    PreparedStatement  statement = connectionDB.
                    prepareStatement("INSERT INTO detalles_movimiento (`cantidad_boletos`,"+
                            " `precio_unitario`, `importe`,`id_turno`,`tipo`,`rango_horario`,`serie`)"
                                    + " VALUES (?,?,?,?,?,?,?)");
                    statement.setInt(1, d.getNoBol());
                    statement.setFloat(2, d.getPrecioUnitario());
                    statement.setFloat(3, d.getImporte());
                    statement.setLong(4, id);
                    statement.setString(5, d.getTipo());
                    statement.setString(6, d.getRangoHorario());
                    //statement.setString(7, next.getKey());
                    statement.executeUpdate();
                    conexion.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }


    }
    
    public static ArrayList<DetallesMovimiento> getById(Long turno_id){
        ArrayList<DetallesMovimiento> mapDetalles = new ArrayList();
         
        //ArrayList<DetallesMovimiento> dm= new ArrayList<DetallesMovimiento>();
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT * FROM detalles_movimiento where id_turno = ?");
            statement.setLong(1, turno_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                mapDetalles.
                        add(new DetallesMovimiento(resultSet.getString("rango_horario"),
                        resultSet.getString("tipo"),resultSet.getInt("cantidad_boletos") ,
                        resultSet.getFloat("precio_unitario"), resultSet.getFloat("importe")));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
       
         ordenarPorPU(mapDetalles);
       
         return mapDetalles;
     }
    
    public static float calcularTotal ( ArrayList<DetallesMovimiento> generarDetalles ){
        float   total = 0;

        for(DetallesMovimiento d : generarDetalles){
            total+= d.getImporte();
        }

        return total;
    }
    
    public static void ordenarPorPU(  ArrayList<DetallesMovimiento> detallesMovimiento){
       
        ArrayList<DetallesMovimiento> value = detallesMovimiento;
        for(int i =0 ; i< value.size()-1;i++){
            for(int j =i+1 ; j< value.size();j++){
                if(value.get(i).getPrecioUnitario()> value.get(j).getPrecioUnitario()
                        || value.get(i).getTipo().equals("Cancelado")
                        || value.get(i).getTipo().equals("Perdido")){
                   DetallesMovimiento aux = value.get(i);
                   value.set(i,value.get(j));
                   value.set(j,aux);
                }
            }
        }
        String text = "";
        for(int i =0 ; i< value.size();i++){
            if(!value.get(i).getTipo().equals(text)){
                text = value.get(i).getTipo();
                value.get(i).setTipo("Titulo"+value.get(i).getTipo());
            }
        }
    }
    
    public static ArrayList<DetallesMovimiento> generarDetalles(List<Auto> autosCobradosTurnoActual,
        List<Auto> autosBoletoPerdidoTurnoActual, List<Auto> autosBoletoCanceladoTurnoActual,Turno turno){
        
        ArrayList<DetallesMovimiento> mapDetalles = new ArrayList();
       
      
        //ArrayList<DetallesMovimiento> detallesMovimiento= new ArrayList<DetallesMovimiento>();
   
            Iterator<Auto> iteratorAutos = autosCobradosTurnoActual.iterator();
            while (iteratorAutos.hasNext()){
                Auto next = iteratorAutos.next();
                
                    DetallesMovimiento detalles = DetallesMovimiento.existePrecioUnitario(mapDetalles, next.getMontoTangible());

                    //DetallesMovimiento detalles = DetallesMovimiento.existeRangoHorario(mapDetalles, next,next.getTarifa().getDescripcion());
                    if(detalles==null){
                        mapDetalles.add(new DetallesMovimiento(Tarifa.getRangoEstadia(next), next.getTarifa().getDescripcion(), 1, next.getMontoTangible(), next.getMontoTangible()));
                    }else{
                        detalles.setNoBol( detalles.getNoBol()+1);
                        detalles.setImporte(detalles.getPrecioUnitario()*detalles.getNoBol());
                    }
                
            }

       /* 
        Iterator<Auto> iteratorAutosPerdidos = autosBoletoPerdidoTurnoActual.iterator();
        while (iteratorAutosPerdidos.hasNext()){
            Auto next = iteratorAutosPerdidos.next();
            
            //DetallesMovimiento detalles = DetallesMovimiento.existePrecioUnitario(detallesMovimiento, next.getMontoTangible());
            DetallesMovimiento detalles = DetallesMovimiento.existeRangoHorario(mapDetalles, next,"Perdido");
            if(detalles==null){
                mapDetalles.add(new DetallesMovimiento("", "Perdido", 1, next.getTarifa().getPrecioBoletoPerdido(), 
                next.getTarifa().getPrecioBoletoPerdido()));
            }else{
                detalles.setNoBol( detalles.getNoBol()+1);
                detalles.setImporte(detalles.getPrecioUnitario()*detalles.getNoBol());
            }
           
        }
        
        Iterator<Auto> iteratorAutosCancelados = autosBoletoCanceladoTurnoActual.iterator();
        while (iteratorAutosCancelados.hasNext()){
            Auto next = iteratorAutosCancelados.next();
            
            //DetallesMovimiento detalles = DetallesMovimiento.existePrecioUnitario(detallesMovimiento, next.getMontoTangible());
            DetallesMovimiento detalles = DetallesMovimiento.existeRangoHorario(mapDetalles, next ,"Cancelado");
            if(detalles==null){
                mapDetalles.add(new DetallesMovimiento("", "Cancelado", 1, 0 , 0));
            }else{
                detalles.setNoBol( detalles.getNoBol()+1);
                detalles.setImporte(detalles.getPrecioUnitario()*detalles.getNoBol());
            }
           
        }*/
        return mapDetalles;
    }
    
//    public static ArrayList<DetallesMovimiento> fusionarDetalles(HashMap<String, ArrayList<DetallesMovimiento>> generarDetalles) {
//        ArrayList<DetallesMovimiento> fusionarDetalles = new ArrayList<>();
//        
//        Iterator<Map.Entry<String, ArrayList<DetallesMovimiento>>> iterator = generarDetalles.entrySet().iterator();
//        while(iterator.hasNext()){
//            Map.Entry<String, ArrayList<DetallesMovimiento>> next = iterator.next();
//            ArrayList<DetallesMovimiento> value = next.getValue();
//            for(DetallesMovimiento dm :value){
//                boolean existe = false;
//                for(DetallesMovimiento fusion :fusionarDetalles){
//                    if( dm.getRangoHorario().equals(fusion.getRangoHorario())  && dm.getTipo().equals(fusion.getTipo())){
//                        dm.getRangoHorario();
//                        
//                        break;
//                    }
//                }
//                if(!existe){
//                    fusionarDetalles.add(dm); 
//                }
//                
//            }
//            for(int i =0 ; i< value.size()-1;i++){
//                for(int j =i+1 ; j< value.size();j++){
//                    if(value.get(i).getPrecioUnitario()> value.get(j).getPrecioUnitario()
//                            || value.get(i).getTipo().equals("Cancelado")
//                            || value.get(i).getTipo().equals("Perdido")){
//                       DetallesMovimiento aux = value.get(i);
//                       value.set(i,value.get(j));
//                       value.set(j,aux);
//                    }
//                }
//           }
//            
//            
//        }
//    
//        return fusionarDetalles;
//    }

    
    
    
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