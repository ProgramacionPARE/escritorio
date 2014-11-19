/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Configuracion;
import modelos.Mensaje;
import modelos.Principal;


public class ClientePantalla extends Thread {
    Socket socket;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    private boolean cerrarHilo;
    
    public ClientePantalla() {
        this.cerrarHilo = false;
         while(!cerrarHilo){
            try {
                socket = new Socket(Configuracion.getInstancia().getIp(),ServerAcept.NUM_SOCKET+ServerAcept.PANTALLA);
                if(socket!=null){
                    System.out.println("Conectado con exito");
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
         
    }
    
    @Override
    public void run() {
        try {
            while(!cerrarHilo){
                if(socket!=null){
                    Mensaje mensaje = (Mensaje)entrada.readObject();
                        if(mensaje.getTipo()== Mensaje.TURNO_ABIERTO){
                            if((boolean)mensaje.getMensaje()){
                                System.out.println("Turno abierto");
                            }
                        }
                }
            }
        }catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(ClientePantalla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
  