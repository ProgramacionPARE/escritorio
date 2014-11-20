
package sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Main;
import modelos.Mensaje;

public class ServerPantalla extends Thread {
    public static final int TURNO_ABIERTO = 0x0;

    private Socket socket;
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private boolean cerrarHilo;

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
    
    public  void enviarTurnoCerrado(){
        try {
            salida.writeObject(new Mensaje(TURNO_ABIERTO,true));
            
            
        } catch (IOException ex) {
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
    
    @Override
    public void run() {
        if(Main.getInstance().getTurnoActual()!=null)
            enviarTurnoAbierto();
        else
            enviarTurnoCerrado();
        try {
            while(cerrarHilo){
            System.out.println("Esperando comando");
            Object readObject = entrada.readObject();
            
            
            
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ServerPantalla.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   
    
}