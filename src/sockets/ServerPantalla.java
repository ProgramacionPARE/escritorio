
package sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Mensaje;


public class ServerPantalla extends Thread {
    public static final int TURNO_ABIERTO = 0x0;
    private Socket socket;
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;

    public ServerPantalla(Socket socket) {
        try {
            this.socket = socket;
            salida = new ObjectOutputStream (socket.getOutputStream());
            salida.flush();
            entrada = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ServerPantalla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public  void enviarTurnoAbierto(){
        try {
            salida.writeObject(new Mensaje(TURNO_ABIERTO,true));
            
            
        } catch (IOException ex) {
            Logger.getLogger(ServerPantalla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        try {
            System.out.println("Esperando comando");
            Object readObject = entrada.readObject();
            
            
    
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ServerPantalla.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   
    
}