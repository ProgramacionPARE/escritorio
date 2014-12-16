
package sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Auto;
import modelos.Mensaje;
import modelos.MonitorEstacionamiento;
import modelos.Principal;
import vistas.FrmLeerCodigoBarrasTerminal;
import vistas.FrmMonitor;
import vistas.FrmMonitorDetalle;

/**
 *
 * @author sistema
 */
public class ClienteMonitor extends Thread{
    private Socket socket;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    private boolean cerrarHilo;
    MonitorEstacionamiento estacionamiento;
    FrmMonitor frmMonitor;
    FrmMonitorDetalle frmMonitorDetalle;
    
    public ClienteMonitor(MonitorEstacionamiento estacionamiento,FrmMonitor frmMonitor) {
        this.cerrarHilo = false;
        this.estacionamiento = estacionamiento;
        this.frmMonitor = frmMonitor;    
    }

     public void apagarHilo(){
        cerrarHilo = true;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public MonitorEstacionamiento getEstacionamiento() {
        return estacionamiento;
    }

    public void setEstacionamiento(MonitorEstacionamiento estacionamiento) {
        this.estacionamiento = estacionamiento;
    } 
        
    public void enviarProgresivo(String progresivo) {
         try {
            if(salida != null)
                salida.writeObject(new Mensaje(Mensaje.PROGRESIVO,progresivo));
        } catch (IOException ex) {
            Logger.getLogger(FrmLeerCodigoBarrasTerminal.class.getName()).log(Level.SEVERE, null, ex);
        }
   }

    public void actualizarAuto(Auto auto) {
          try {
            if(salida != null)
                salida.writeObject(new Mensaje(Mensaje.ACTUALIZAR_AUTO,auto));
        } catch (IOException ex) {
            Logger.getLogger(FrmLeerCodigoBarrasTerminal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public void run(){
        while(!cerrarHilo){
            try {
                while(!cerrarHilo){
                    try {
                        socket = new Socket(estacionamiento.getIp(),ServerAcept.NUM_SOCKET+ServerAcept.MONITOR);
                        if(socket!=null){
                            System.out.println("Conectado con exito");
                            frmMonitor.actualizarCentros();
                            entrada = new ObjectInputStream( socket.getInputStream());
                            salida = new ObjectOutputStream(socket.getOutputStream());
                            salida.flush();
                            break;
                        }
                    }catch (IOException ex) {
                        System.out.println("Intentando conectar");
                        try {
                            Thread.sleep(5000);
                        }catch (InterruptedException ex1) {
                            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }        
               }
            while(!cerrarHilo){
                if(socket!=null){
                    Mensaje mensaje = (Mensaje)entrada.readObject();
                    if(mensaje.getTipo()== Mensaje.TURNO_ABIERTO){
                        if((boolean)mensaje.getMensaje()){
                           System.out.println("Turno abierto");
                        }else{
                            System.out.println("Turno cerrado");
                        }
                    }else if(mensaje.getTipo()== Mensaje.AUTO){
                        frmMonitorDetalle = new FrmMonitorDetalle(this.frmMonitor,false,(Auto)mensaje.getMensaje(),this);
                        frmMonitorDetalle.setVisible(true);
                    }else if(mensaje.getTipo()== Mensaje.AUTO_NO_ENCONTRADO){
                        frmMonitor.autoNoEncontrado();
                    }else if(mensaje.getTipo()== Mensaje.ACTUALIZAR_AUTO){
                        frmMonitorDetalle.setAuto((Auto)mensaje.getMensaje());
                    }
                    
                }
            }
        }catch (IOException | ClassNotFoundException ex) {
                try {
                    System.out.println("Cerrando cliente y esperando nueva instancia");
                    if(socket.isConnected())
                        socket.close();
                    socket = null;
                    entrada.close();
                    entrada = null;
                    salida.close();
                    salida=null;
                    if(!cerrarHilo)
                        frmMonitor.actualizarCentros();
                    //new ClienteMonitor().start();
                    //Logger.getLogger(ClientePantalla.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex1) {
                    Logger.getLogger(ClienteMonitor.class.getName()).log(Level.SEVERE, null, ex1);
                }
        }
        } 
        
    }

    

   
    
}