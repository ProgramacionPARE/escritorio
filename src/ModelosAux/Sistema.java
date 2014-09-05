/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ModelosAux;

import modelos.Caja;


public class Sistema {
    public static boolean requiereRetitroParcial(Caja caja){
        return caja.getMonto()>=caja.getMontoAlarma();
    }
    
}
