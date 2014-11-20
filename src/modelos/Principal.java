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
            FrmLogin frmLogin = new FrmLogin();
            Main.getInstance().getServerAcept().add( new ServerAcept(ServerAcept.BOLETO,frmLogin));
            Main.getInstance().getServerAcept().add( new ServerAcept(ServerAcept.PANTALLA,frmLogin));
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
        }
    }
}
