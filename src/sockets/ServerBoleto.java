
package sockets;

import ModelosAux.Seguridad;
import ModelosAux.Tiempo;
import java.awt.Frame;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelos.Auto;
import modelos.Empleado;
import modelos.Main;
import modelos.Mensaje;
import modelos.Progresivo;
import modelos.Rest;
import net.sourceforge.barbecue.BarcodeException;
import vistas.FrmCobro;
import vistas.FrmLeerCodigoBarras;
import vistas.formatos.FrmP1BoletoCliente;
import vistas.formatos.FrmP2BoletoLlaves;
import vistas.formatos.FrmP3BoletoParabrisas;


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
    
    public void enviarBoleto(){
        try {
            if(salida!=null)
                salida.writeObject(new Mensaje(Mensaje.NUEVO_BOLETO));
        } catch (IOException ex) {
            Logger.getLogger(ServerPantalla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviarAuto(Auto auto){
        try {
            if(salida!=null)
                salida.writeObject(new Mensaje(Mensaje.AUTO,auto));
        } catch (IOException ex) {
            Logger.getLogger(ServerPantalla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviarEmpleado(Empleado empleado){
        try {
            if(salida!=null)
                salida.writeObject(new Mensaje(Mensaje.EMPLEADO,empleado));
        } catch (IOException ex) {
            Logger.getLogger(ServerPantalla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      private void enviarNombreEstacioneminto(String descripcion) {
        try {
            if(salida!=null)
                salida.writeObject(new Mensaje(Mensaje.NOMBRE_ESTACIONAMIENTO,descripcion));
        } catch (IOException ex) {
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
                if(mensaje.getTipo()==Mensaje.CODIGO_VALET){
                    System.out.println("Resivo nuevo boleto");
                    Empleado empleado = null;
                    empleado = Empleado.getByIdClave((String)mensaje.getMensaje());
                    if (empleado != null){
                        Auto newAuto = new Auto(Progresivo.getUltimoProgresivo(Main.getInstance().getEstacionamiento().getCaseta(),"0"),
                                "",Tiempo.getFecha(),Tiempo.getHora(),"","","",Main.getInstance().getTurnoActual().getId(),"0",
                                "",Seguridad.getClave(5), Main.getInstance().getEstacionamiento().getCaseta().getId());
                        newAuto.setDentro(true);
                        //Aumento en uno los boletos generados
                        Main.getInstance().getTurnoActual().getDetallesTurno().get(newAuto.getSerie()).setNoBol(Main.getInstance().getTurnoActual().getDetallesTurno().get(newAuto.getSerie()).getNoBol()+1);
                        //Actualizo el folio final en el turno
                        Main.getInstance().getTurnoActual().getDetallesTurno().get(newAuto.getSerie()).setFolioFinal (Main.getInstance().getTurnoActual().getDetallesTurno().get(newAuto.getSerie()).getFolioFinal()+1);

                        Progresivo.setProgresivoMasUno(Main.getInstance().getEstacionamiento().getCaseta(),newAuto.getSerie());
                        
                        enviarBoleto();
                        enviarAuto(newAuto);
                        enviarEmpleado(empleado);
                        enviarNombreEstacioneminto(Main.getInstance().getEstacionamiento().getDescripcion());
                        
                        Main.getInstance().getTurnoActual().actualizar();
                        // Guardo entrada y actualizo progresivo
                        newAuto.guardar();
                        Rest.sendAuto(newAuto,Main.getInstance().getEstacionamiento());
                    } 
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