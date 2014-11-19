
package sockets;
    
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Main;
import vistas.FrmPrincipal;


public class ServerAcept extends Thread {
    public static final int PANTALLA = 0;
    public static final int BOLETO = 1;
    public static final int NUM_SOCKET = 8050;
    
    private int tipo;
    private ServerSocket serverSocket;
    private Socket socket;
    private boolean cerrarHilo;
    public ServerAcept(int tipo) {
        this.tipo = tipo;
        this.cerrarHilo = false;
        try {
            serverSocket = new ServerSocket(NUM_SOCKET + tipo);
        } catch (IOException ex) {
            Logger.getLogger(ServerAcept.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void apagarHilo(){
        cerrarHilo = true;
    }
    
    @Override
    public void run() {         
        while(!cerrarHilo){    
            try {
                 System.out.println("Esperando conexion");
                socket = serverSocket.accept();
                //Main.getInstance().setSocketCliente(acceptSocket);
                System.out.println("Conexion recivida");
            }catch (IOException ex){
                Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(tipo == PANTALLA){
                Main.getInstance().setServerPantalla(new ServerPantalla(socket));
                Main.getInstance().getServerPantalla().start();
            }else if(tipo == BOLETO){
                //new ServerBoleto(socket);
            }
        }
    }
    
}
