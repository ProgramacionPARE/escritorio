
package sockets;

import java.awt.Frame;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Auto;
import modelos.Main;
import modelos.Mensaje;
import vistas.FrmCobro;


public class ServerBoleto extends Thread {
    private Frame parent;
    private Socket socket;
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private boolean cerrarHilo;
    private FrmCobro frmCobro;
    ServerBoleto(Socket socket, Frame parent) {
               try {
            this.cerrarHilo = false;
            this.socket = socket;
            this.parent = parent;
            salida = new ObjectOutputStream (socket.getOutputStream());
            salida.flush();
            entrada = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            if(ex.getMessage().equals("Socket is closed")||ex.getMessage().equals("Socket closed"))
                System.out.println("Se cerro el socket en -ServerPantalla-");
            else   
                Logger.getLogger(ServerPantalla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public  void enviarTurnoAbierto(){
        try {
            if(salida!=null)
                salida.writeObject(new Mensaje(Mensaje.TURNO_ABIERTO,true));
        } catch (IOException ex) {
            Logger.getLogger(ServerPantalla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public  void enviarTurnoCerrado(){
        try {
            if(salida!=null)
                salida.writeObject(new Mensaje(Mensaje.TURNO_ABIERTO,false));
        } catch (IOException ex) {
            Logger.getLogger(ServerPantalla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
         if(Main.getInstance().getTurnoActual()!=null)
            enviarTurnoAbierto();
        else
            enviarTurnoCerrado();
        
        try {
            while(!cerrarHilo){
                System.out.println("Esperando comando");
                Mensaje mensaje;
                mensaje = (Mensaje)entrada.readObject();
                if(mensaje.getTipo()==Mensaje.CODIGO){
                    
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            if(ex.getMessage().equals("Socket closed"))
                System.out.println("Se cerro el socket en -ServerPantalla-");
            else   
                Logger.getLogger(ServerPantalla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
}