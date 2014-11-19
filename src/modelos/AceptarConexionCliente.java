
package modelos;

import java.awt.Frame;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import vistas.FrmCobro;

/**
 *
 * @author oscar
 */
public class AceptarConexionCliente extends Thread {
    private Socket socket;
    private Frame frame;
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private Estacionamiento estacionamiento;
    
    public AceptarConexionCliente(Frame frame){
        this.socket = Main.getInstance().getSocketCliente();
        this.frame = frame;
    }
    @Override
    public void run(){
        try {
            if(Main.getInstance().getEntradaCliente()== null &&Main.getInstance().getSalidaCliente()== null){
            salida = new ObjectOutputStream (socket.getOutputStream());
            salida.flush();
            entrada = new ObjectInputStream(socket.getInputStream());
            Main.getInstance().setEntradaCliente(entrada);
            Main.getInstance().setSalidaCliente(salida);
            }else{
            salida = Main.getInstance().getSalidaCliente();
            entrada = Main.getInstance().getEntradaCliente();
            }    
        } catch (IOException ex) {
            Logger.getLogger(AceptarConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        while (true){
            Mensaje mensaje = null;
            try {
                mensaje = (Mensaje)entrada.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(AceptarConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(mensaje.getTipo().equals("cliente")){
                Auto auto;
                auto = Auto.getByProgresivoClave((String)mensaje.getMensaje());
                if (auto != null){
                    if(auto.isDentro()){
                        new FrmCobro(frame, true,auto);   
                    }else{
                        try {
                            salida.writeObject(new Mensaje("autoCobrado",true));
                        } catch (IOException ex) {
                            Logger.getLogger(AceptarConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }else if(mensaje.getTipo().equals("turnoAbierto")){
                if(Main.getInstance().getTurnoActual()!=null){
                    try {
                        salida.writeObject(new Mensaje("turnoAbierto",true));
                    } catch (IOException ex) {
                        Logger.getLogger(AceptarConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    try {
                        salida.writeObject(new Mensaje("turnoAbierto",false));
                    } catch (IOException ex) {
                        Logger.getLogger(AceptarConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }         
        }
    }
}




/*else if(mensaje.getTipo().equals("entrada")){
                Empleado empleado = null;
                empleado = Empleado.getByIdClave((String)mensaje.getMensaje());
                if (empleado != null){
                    Auto newAuto = new Auto(Progresivo.getUltimoProgresivo(Main.getInstance().getEstacionamiento().getCaseta(),"0"),
                    "",Tiempo.getFecha(),Tiempo.getHora(),"","","",Main.getInstance().getTurnoActual().getId(),"0",
                    "",Seguridad.getClave(5), estacionamiento.getCaseta().getId());    
                    //Aumento en uno los boletos generados
                    Main.getInstance().getTurnoActual().getDetallesTurno().get(newAuto.getSerie()).setNoBol(Main.getInstance().getTurnoActual().getDetallesTurno().get(newAuto.getSerie()).getNoBol()+1);
                    //Actualizo el folio final en el turno
                    Main.getInstance().getTurnoActual().getDetallesTurno().get(newAuto.getSerie()).setFolioFinal (Main.getInstance().getTurnoActual().getDetallesTurno().get(newAuto.getSerie()).getFolioFinal()+1);

                    Progresivo.setProgresivoMasUno(estacionamiento.getCaseta(),newAuto.getSerie());    
                }
            }*/