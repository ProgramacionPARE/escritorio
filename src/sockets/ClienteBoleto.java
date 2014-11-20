/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Auto;
import modelos.Configuracion;
import modelos.Empleado;
import modelos.Mensaje;
import modelos.Principal;
import net.sourceforge.barbecue.BarcodeException;
import vistas.FrmCobroCliente;
import vistas.FrmErrorCarga;
import vistas.FrmLeerCodigoBarrasTerminal;
import vistas.FrmMensajeCliente;
import vistas.formatos.FrmP1BoletoCliente;
import vistas.formatos.FrmP2BoletoLlaves;
import vistas.formatos.FrmP3BoletoParabrisas;

/**
 *
 * @author sistema
 */
public class ClienteBoleto extends Thread{

    Socket socket;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    private boolean cerrarHilo;
    FrmErrorCarga frmErrorCarga;
    FrmLeerCodigoBarrasTerminal frmCodigoBarras;
    FrmCobroCliente frmCobroCliente;
    
    public ClienteBoleto() {
        frmErrorCarga = new FrmErrorCarga();
        this.cerrarHilo = false;
         while(!cerrarHilo){
            try {
                socket = new Socket(Configuracion.getInstancia().getIp(),ServerAcept.NUM_SOCKET+ServerAcept.PANTALLA);
                if(socket!=null){
                    System.out.println("Conectado con exito");
                    frmErrorCarga.setLabel1Text("Error turno cerrado");
                    frmErrorCarga.setLabel2Text("Por favor abre un turno en caja para continuar");   
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

     public void apagarHilo(){
        cerrarHilo = true;
    }
     
     
    public void enviarCodigo(String id) {
        try {
            if(salida != null)
                salida.writeObject(new Mensaje(Mensaje.CODIGO_VALET,id));
        } catch (IOException ex) {
            Logger.getLogger(FrmLeerCodigoBarrasTerminal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public void run(){
        try {
            while(!cerrarHilo){
                if(socket!=null){
                    Mensaje mensaje = (Mensaje)entrada.readObject();
                        if(mensaje.getTipo()== Mensaje.TURNO_ABIERTO){
                            if((boolean)mensaje.getMensaje()){
                                frmErrorCarga.dispose();
                                frmCodigoBarras = new FrmLeerCodigoBarrasTerminal(Configuracion.EXPEDIDOR);
                                System.out.println("Turno abierto");
                            }else{
                                if(frmCodigoBarras!=null){
                                    frmCodigoBarras.dispose();
                                    frmErrorCarga = new FrmErrorCarga();
                                    frmErrorCarga.setLabel1Text("Error turno cerrado");
                                    frmErrorCarga.setLabel2Text("Por favor abre un turno en caja para continuar"); 
                                }
                                System.out.println("Turno cerrado");
                            }
                        }else if(mensaje.getTipo()== Mensaje.NUEVO_BOLETO){
                            Auto auto = null; Empleado empleado = null;
                            mensaje = (Mensaje)entrada.readObject();
                            if(mensaje.getTipo()== Mensaje.AUTO){
                                auto = (Auto)mensaje.getMensaje();
                            }
                            mensaje = (Mensaje)entrada.readObject();
                            if(mensaje.getTipo()== Mensaje.EMPLEADO){
                                empleado = (Empleado)mensaje.getMensaje();
                            }
                             PrinterJob job = PrinterJob.getPrinterJob();
                            // Boleto al cliente
                        try {                            
                            new FrmP1BoletoCliente(null, false,job,auto,empleado);
                             //Boleto llaves
                            new FrmP2BoletoLlaves(null, false,job,auto,empleado);
                            //Boleto Parabrisas
                            new FrmP3BoletoParabrisas(null, false ,job,auto);
                        } catch (PrinterException | BarcodeException ex) {
                            Logger.getLogger(ClienteBoleto.class.getName()).log(Level.SEVERE, null, ex);
                        }
                           
                        }
                }
            }
        }catch (IOException | ClassNotFoundException ex) {
            apagarHilo();
            System.out.println("Cerrando cliente y esperando nueva instancia");
            frmErrorCarga.dispose();
            new ClientePantalla().start();
            //Logger.getLogger(ClientePantalla.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


   
    
}