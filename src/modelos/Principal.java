/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import sockets.ClienteBoleto;
import sockets.ClientePantalla;
import sockets.ServerAcept;
import vistas.FrmLogin;


public class Principal {
    
    public static void main(String[] args) { 
        Configuracion datos = Configuracion.getInstancia();
        Socket socket = null;
        if(datos.getTerminal().equals(Configuracion.CAJA)){
            Main.getInstance().setServerAcept(new ArrayList());
   
            Main.getInstance().getServerAcept().add( new ServerAcept(ServerAcept.BOLETO));
            Main.getInstance().getServerAcept().add( new ServerAcept(ServerAcept.PANTALLA));
            Iterator<ServerAcept> iterator = Main.getInstance().getServerAcept().iterator();
            while(iterator.hasNext())iterator.next().start();
            new FrmLogin();
        }else  if(datos.getTerminal().equals(Configuracion.CLIENTE)){
            new ClientePantalla().start();
            //FrmErrorCarga frmErrorCarga = new FrmErrorCarga(null,true,null);
        } else  if(datos.getTerminal().equals(Configuracion.EXPEDIDOR)){
            new ClienteBoleto();
            //FrmErrorCarga frmErrorCarga = new FrmErrorCarga(null,true,null);
        }
    }
}
