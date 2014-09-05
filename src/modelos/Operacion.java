package modelos;

import proyectopare.ProyectoPareApp;
import vistas.FrmLoginTemporal;

public class Operacion  {
    boolean acceso;
    String permiso;
    public String getPermiso;
    
    
    public boolean isAcceso() {
        return acceso;
    }

    public void setAcceso(boolean acceso) {
        this.acceso = acceso;
    }

    public String getPermiso() {
        return permiso;
    }

    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }
    
    
    
    public boolean requierePermisos(Empleado empleado, String permiso,boolean permitir){
        acceso = false;
        this.permiso = permiso;
        switch (permiso){
            case "Administrador":
                switch (empleado.getTipoPuesto()){
                    case "Administrador":
                        return true;
                    case "Supervisor":
                        return permitir ? abrirLogin(): false ;
                    case "Auditor":
                        return permitir ? abrirLogin(): false ;
                    case "Encargado":
                        return permitir ? abrirLogin(): false ;
                    case "Cajero":
                        return permitir ? abrirLogin(): false ;
                }
                break;
            case "Supervisor":
                  switch (empleado.getTipoPuesto()){
                    case "Administrador":
                        return true;
                    case "Supervisor":
                        return true;
                    case "Auditor":
                        return true;
                    case "Encargado":
                        return permitir ? abrirLogin(): false ;
                    case "Cajero":
                        return permitir ? abrirLogin(): false ;
                }
                break;
            case "Auditor":
                switch (empleado.getTipoPuesto()){
                    case "Administrador":
                        return true;
                    case "Supervisor":
                        return true;
                    case "Auditor":
                        return true;
                    case "Encargado":
                        return permitir ? abrirLogin(): false ;
                    case "Cajero":
                        return permitir ? abrirLogin(): false ;
                }
                break;     
            case "Encargado":
                switch (empleado.getTipoPuesto()){
                    case "Administrador":
                        return true;
                    case "Supervisor":
                        return true;
                    case "Auditor":
                        return true;
                    case "Encargado":
                        return true;
                    case "Cajero":
                        return permitir ? abrirLogin(): false ;
                }
                break;    
            case "Cajero":
                 switch (empleado.getTipoPuesto()){
                    case "Administrador":
                        return true;
                    case "Supervisor":
                        return true;
                    case "Auditor":
                        return true;
                    case "Encargado":
                        return true;
                    case "Cajero":
                        return true;
                }
                break;
        }
        return false;
    }

    private  boolean abrirLogin() {
        FrmLoginTemporal frmLoginTemporal = new FrmLoginTemporal(ProyectoPareApp.getApplication().getMainView().getFrame(),true,this);
        frmLoginTemporal.setVisible(true);  
        return acceso;
    }
}
