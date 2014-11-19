/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.net.Socket;

import java.util.logging.Level;
import java.util.logging.Logger;
import sockets.ClienteBoleto;
import sockets.ClientePantalla;
import sockets.ServerAcept;
import vistas.FrmErrorCarga;
import vistas.FrmLogin;


public class Principal {
    
    public static void main(String[] args) { 
        Configuracion datos = Configuracion.getInstancia();
        Socket socket = null;
        if(datos.getTerminal().equals(Configuracion.CAJA)){
            new ServerAcept(ServerAcept.BOLETO);
            new ServerAcept(ServerAcept.PANTALLA);
            new FrmLogin();
        }else  if(datos.getTerminal().equals(Configuracion.CLIENTE)){
            new ClientePantalla();
            //FrmErrorCarga frmErrorCarga = new FrmErrorCarga(null,true,null);
        } else  if(datos.getTerminal().equals(Configuracion.EXPEDIDOR)){
            new ClienteBoleto();
            //FrmErrorCarga frmErrorCarga = new FrmErrorCarga(null,true,null);
        }
    }
}
