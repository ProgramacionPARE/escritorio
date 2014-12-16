/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import sockets.ClienteBoleto;
import sockets.ClienteMonitor;
import sockets.ClientePantalla;
import sockets.ServerAcept;
import vistas.FrmLogin;
import vistas.FrmMonitor;


public class Principal {
    
    public static void main(String[] args) {
        //new Principal().cargarDescuentos();
        Configuracion datos = Configuracion.getInstancia();
        Socket socket = null;
        if(datos.getTerminal().equals(Configuracion.CAJA)){
            Main.getInstance().setServerAcept(new ArrayList());
            FrmLogin frmLogin = new FrmLogin();
            Main.getInstance().getServerAcept().add( new ServerAcept(ServerAcept.BOLETO,frmLogin));
            Main.getInstance().getServerAcept().add( new ServerAcept(ServerAcept.PANTALLA,frmLogin));
            Main.getInstance().getServerAcept().add( new ServerAcept(ServerAcept.MONITOR,frmLogin));
            
            Iterator<ServerAcept> iterator = Main.getInstance().getServerAcept().iterator();
            while(iterator.hasNext())iterator.next().start();
        }else  if(datos.getTerminal().equals(Configuracion.CLIENTE)){
            Main.getInstance().setClientePantalla(new ClientePantalla());
            Main.getInstance().getClientePantalla().start();
            //FrmErrorCarga frmErrorCarga = new FrmErrorCarga(null,true,null);
        } else  if(datos.getTerminal().equals(Configuracion.EXPEDIDOR)){
            Main.getInstance().setClienteBoleto(new ClienteBoleto());
            Main.getInstance().getClienteBoleto().start();
            
            //FrmErrorCarga frmErrorCarga = new FrmErrorCarga(null,true,null);
        }else if(datos.getTerminal().equals(Configuracion.MONITOR)){
            Monitor monitor = Monitor.getInstancia();
            Main.getInstance().setClienteMonitor(new ArrayList());
            FrmMonitor frmMonitor = new FrmMonitor();
            Iterator<MonitorEstacionamiento> iterator = monitor.getEstacionamientos().iterator();
            while(iterator.hasNext()){
                MonitorEstacionamiento next = iterator.next();
                ClienteMonitor clienteMonitor = new ClienteMonitor(next,frmMonitor);
                Main.getInstance().getClienteMonitor().add(clienteMonitor );
                clienteMonitor.start();
            }
            frmMonitor.setVisible(true);
            frmMonitor.actualizarCentros();
        }
    }
    
//    public void cargarDescuentos(){
//         File archivo = null;
//      FileReader fr = null;
//      BufferedReader br = null;
// 
//      try {
//         // Apertura del fichero y creacion de BufferedReader para poder
//         // hacer una lectura comoda (disponer del metodo readLine()).
//         archivo = new File ("/home/sistema/Escritorio/codigos");
//         fr = new FileReader (archivo);
//         br = new BufferedReader(fr);
// 
//         // Lectura del fichero
//         String linea;
//         while((linea=br.readLine())!=null){
//           // public Descuento(long folio, float descuento, boolean activo,String clave) {
//             System.out.println(linea);
//             System.out.println(linea.substring(6, 12));
//              System.out.println(linea.substring(0, 6));
//             new Descuento(Long.valueOf(linea.substring(6, 12)) ,16L,true,linea.substring(0,6)).guardar();
//         }
//      }
//      catch(Exception e){
//         e.printStackTrace();
//      }finally{
//         // En el finally cerramos el fichero, para asegurarnos
//         // que se cierra tanto si todo va bien como si salta
//         // una excepcion.
//         try{                   
//            if( null != fr ){  
//               fr.close();    
//            }                 
//         }catch (Exception e2){
//            e2.printStackTrace();
//         }
//      }
//    }
}
