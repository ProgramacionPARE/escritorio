
package modelos;

import java.util.ArrayList;
import sockets.*;

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

    
    
    private ArrayList<ServerAcept> serverAcept;
    private ServerBoleto serverBoleto;
    private ServerPantalla serverPantalla;
    private ServerMonitor serverMonitor;

    private ArrayList<ClienteMonitor> clienteMonitor;
    private ClienteBoleto clienteBoleto;
    private ClientePantalla clientePantalla;
    
    
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

    public ArrayList<ServerAcept> getServerAcept() {
        return serverAcept;
    }

    public void setServerAcept(ArrayList<ServerAcept> serverAcept) {
        this.serverAcept = serverAcept;
    }

    public ServerBoleto getServerBoleto() {
        return serverBoleto;
    }

    public void setServerBoleto(ServerBoleto serverBoleto) {
        this.serverBoleto = serverBoleto;
    }

    public ServerPantalla getServerPantalla() {
        return serverPantalla;
    }

    public void setServerPantalla(ServerPantalla serverPantalla) {
        this.serverPantalla = serverPantalla;
    }

    public ServerMonitor getServerMonitor() {
        return serverMonitor;
    }

    public void setServerMonitor(ServerMonitor serverMonitor) {
        this.serverMonitor = serverMonitor;
    }

    public ClienteBoleto getClienteBoleto() {
        return clienteBoleto;
    }

    public void setClienteBoleto(ClienteBoleto clienteBoleto) {
        this.clienteBoleto = clienteBoleto;
    }

    public ClientePantalla getClientePantalla() {
        return clientePantalla;
    }

    public void setClientePantalla(ClientePantalla clientePantalla) {
        this.clientePantalla = clientePantalla;
    }

    public ArrayList<ClienteMonitor> getClienteMonitor() {
        return clienteMonitor;
    }

    public void setClienteMonitor(ArrayList<ClienteMonitor> clienteMonitor) {
        this.clienteMonitor = clienteMonitor;
    }
    
    
    
    
}
