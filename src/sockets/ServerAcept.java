
package sockets;
    
import java.awt.Frame;
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
    private Frame parent;
    public ServerAcept(int tipo,Frame parent) {
        this.tipo = tipo;
        this.cerrarHilo = false;
        this.parent = parent;
        try {
            serverSocket = new ServerSocket(NUM_SOCKET + tipo);
        } catch (IOException ex) {
            Logger.getLogger(ServerAcept.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Frame getParent() {
        return parent;
    }

    public void setParent(Frame parent) {
        this.parent = parent;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
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
                if(ex.getMessage().equals("Socket closed"))
                    System.out.println("Se cerro el socket en -ServerAcept-");
                else
                    Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(tipo == PANTALLA){
                Main.getInstance().setServerPantalla(new ServerPantalla(socket,parent));
                Main.getInstance().getServerPantalla().start();
            }else if(tipo == BOLETO){
                Main.getInstance().setServerBoleto(new ServerBoleto(socket,parent));
                Main.getInstance().getServerBoleto().start();
            }
        }
    }
    
}
