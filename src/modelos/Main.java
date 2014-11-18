
package modelos;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author oscar
 */
public class Main {
    private static Main MAIN = new Main();
    
    private Estacionamiento estacionamiento;
    private Empleado empleadoSesion;
    private Turno turnoActual;
    private Caja caja;
    
    private Socket socket;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    
    private Socket socketCliente;
    private ObjectInputStream entradaCliente;
    private ObjectOutputStream salidaCliente;
    
    
    private Socket socketBoleto;
    private ObjectInputStream entradaBoleto;
    private ObjectOutputStream salidaBoleto;
    
    public static Main getInstance(){
        return MAIN;
    }
    
    private Main() {
    }

    
    public Estacionamiento getEstacionamiento() {
        return estacionamiento;
    }

    public void setEstacionamiento(Estacionamiento estacionamiento) {
        this.estacionamiento = estacionamiento;
    }

    public Empleado getEmpleadoSesion() {
        return empleadoSesion;
    }

    public void setEmpleadoSesion(Empleado empleadoSesion) {
        this.empleadoSesion = empleadoSesion;
    }

    public Turno getTurnoActual() {
        return turnoActual;
    }

    public void setTurnoActual(Turno turnoActual) {
        this.turnoActual = turnoActual;
    }

    public Caja getCaja() {
        return caja;
    }

    public void setCaja(Caja caja) {
        this.caja = caja;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ObjectInputStream getEntrada() {
        return entrada;
    }

    public void setEntrada(ObjectInputStream entrada) {
        this.entrada = entrada;
    }

    public ObjectOutputStream getSalida() {
        return salida;
    }

    public void setSalida(ObjectOutputStream salida) {
        this.salida = salida;
    }

    public Socket getSocketCliente() {
        return socketCliente;
    }

    public void setSocketCliente(Socket socketCliente) {
        this.socketCliente = socketCliente;
    }

    public ObjectInputStream getEntradaCliente() {
        return entradaCliente;
    }

    public void setEntradaCliente(ObjectInputStream entradaCliente) {
        this.entradaCliente = entradaCliente;
    }

    public ObjectOutputStream getSalidaCliente() {
        return salidaCliente;
    }

    public void setSalidaCliente(ObjectOutputStream salidaCliente) {
        this.salidaCliente = salidaCliente;
    }

    public Socket getSocketBoleto() {
        return socketBoleto;
    }

    public void setSocketBoleto(Socket socketBoleto) {
        this.socketBoleto = socketBoleto;
    }

    public ObjectInputStream getEntradaBoleto() {
        return entradaBoleto;
    }

    public void setEntradaBoleto(ObjectInputStream entradaBoleto) {
        this.entradaBoleto = entradaBoleto;
    }

    public ObjectOutputStream getSalidaBoleto() {
        return salidaBoleto;
    }

    public void setSalidaBoleto(ObjectOutputStream salidaBoleto) {
        this.salidaBoleto = salidaBoleto;
    }
    
    
    
    
}
