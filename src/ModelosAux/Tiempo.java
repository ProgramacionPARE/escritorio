/*
 * Clase que manipula y proporciona fechas en diferentes formatos
 */

package ModelosAux;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asistente Proyectos2
 */
public class Tiempo {
    static String [] mesesLetra = {"enero","febrero","marzo","abril","mayo","junio","julio","agosto","septiembre","octubre","noviembre","diciembre"};
    public static String getHora(){
        return new SimpleDateFormat("HH:mm").format(new Date()) ;
        }
    
    public static String getFecha(){
        return new SimpleDateFormat("yyyy-MM-dd").format( new Date()) ;
    }

    public static String getMesLetra(int i) {
       return mesesLetra[i];
    }

    public static String getFecha(Calendar calendar) {
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        }

    public static String getFechaLarga() {
        return new SimpleDateFormat("dd-MMMM-yyyy").format( new Date()) ;
    }

    public static String getFechaLarga(Calendar calendar) {
         return new SimpleDateFormat("dd-MMMM-yyyy").format( calendar.getTime()) ;
    }
    
    public static Date getDateFromFecha(String fecha) {
        try {
            return   new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(Tiempo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Date();
    }
    
    public static int getDirenciaHoras(String fechaEntrada, String horaEntrada, String fechaSalida, String horaSalida) {
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd*HH:mm");
        Date entrada,salida,estancia=null;
        try {
            entrada = formatter.parse(fechaEntrada+"*"+horaEntrada);
            salida = formatter.parse(fechaSalida+"*"+horaSalida);
            estancia = new Date(salida.getTime()-entrada.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(Tiempo.class.getName()).log(Level.SEVERE, null, ex);
        }
	return  (int) (estancia.getTime()/(60*60 * 1000));	
    }

    public static int getDirenciaMinutos(String fechaEntrada, String horaEntrada, String fechaSalida, String horaSalida) {
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd*HH:mm");
        Date entrada,salida,estancia=null;
        try {
            entrada = formatter.parse(fechaEntrada+"*"+horaEntrada);
            salida = formatter.parse(fechaSalida+"*"+horaSalida);
            estancia = new Date(salida.getTime()-entrada.getTime());
            
        } catch (ParseException ex) {
            Logger.getLogger(Tiempo.class.getName()).log(Level.SEVERE, null, ex);
        }
	return  ((int) (estancia.getTime()/(60 * 1000)))%60;	
    }

    public static boolean horaVsHoraActual(String fecha,String hora) {
        if(getDirenciaHoras(fecha,hora,getFecha(),getHora())<0)
            return false;
        if(getDirenciaMinutos(fecha,hora,getFecha(),getHora())< 0){
            return false;
        }
        return true;
    }

  
}
