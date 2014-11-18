/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import vistas.FrmErrorCarga;
import vistas.FrmLeerCodigoBarrasTerminal;
import vistas.FrmLogin;


public class Principal {
    
    public static void main(String[] args) { 
        Configuracion datos = Configuracion.getInstancia();
        Socket socket = null;
        if(datos.getTerminal().equals(Configuracion.CAJA))
            new FrmLogin();
        else{
            FrmErrorCarga frmErrorCarga = new FrmErrorCarga(null,true,null);
          
        }
    }
}
