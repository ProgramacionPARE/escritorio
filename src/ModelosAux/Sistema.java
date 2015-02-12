/*
 * Clase con funciones especificas del sistema.
 */

package ModelosAux;

import modelos.Caja;


public class Sistema {
    //Valida el monto en caja, contra el valor configuradod de retiro parcial.
    public static boolean requiereRetitroParcial(Caja caja){
        return caja.getMonto()>=caja.getMontoAlarma();
    }
    
}
