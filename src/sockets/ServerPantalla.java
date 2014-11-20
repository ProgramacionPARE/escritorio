
package sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Main;
import modelos.Mensaje;
import vistas.FrmPrincipal;

public class ServerPantalla extends Thread {
    public static final int TURNO_ABIERTO = 0x0;

    private Socket socket;
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private boolean cerrarHilo;

    public ServerPantalla(Socket socket) {
        try {
            this.cerrarHilo = false;
            this.socket = socket;
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
                salida.writeObject(new Mensaje(TURNO_ABIERTO,true));
        } catch (IOException ex) {
            Logger.getLogger(ServerPantalla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public  void enviarTurnoCerrado(){
        try {
            if(salida!=null)
                salida.writeObject(new Mensaje(TURNO_ABIERTO,false));
        } catch (IOException ex) {
            Logger.getLogger(ServerPantalla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   public void apagarHilo(){
        cerrarHilo = true;
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
            Object readObject;
            readObject = entrada.readObject();
            
            
            
            }
        } catch (IOException | ClassNotFoundException ex) {
            if(ex.getMessage().equals("Socket closed"))
                System.out.println("Se cerro el socket en -ServerPantalla-");
            else   
                Logger.getLogger(ServerPantalla.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
     public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ObjectOutputStream getSalida() {
        return salida;
    }

    public void setSalida(ObjectOutputStream salida) {
        this.salida = salida;
    }

    public ObjectInputStream getEntrada() {
        return entrada;
    }

    public void setEntrada(ObjectInputStream entrada) {
        this.entrada = entrada;
    }
    
   
    
}