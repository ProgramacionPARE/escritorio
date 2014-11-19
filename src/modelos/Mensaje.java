/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.Serializable;

/**
 *
 * @author oscar
 */
public class Mensaje implements Serializable{
    int tipo;
    Object mensaje;

    public Mensaje(int tipo, Object mensaje) {
        this.tipo = tipo;
        this.mensaje = mensaje;
        System.out.println( toString()); 
    }
    
  public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Object getMensaje() {
        return mensaje;
    }

    public void setMensaje(Object mensaje) {
        this.mensaje = mensaje;
    }

    

    @Override
    public String toString() {
        return "Mensaje{" + "tipo=" + tipo + ", mensaje=" + mensaje + '}';
    }
    
    
}
