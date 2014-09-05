/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectopare.clases;
/**
 *
 * @author Administrador
 */
public class ProgramacionSemanalPersonal2 {
    private String numempleado;
    private String puesto;
    private String nomempleado;
    private String lunes;
    private String martes;
    private String miercoles;
    private String jueves;
    private String viernes;
    private String sabado;
    private String domingo;

    public ProgramacionSemanalPersonal2(){
        numempleado="";
        puesto="";
        nomempleado="";
        lunes="";
        martes="";
        miercoles="";
        jueves="";
        viernes="";
        sabado="";
        domingo="";
    }

    public ProgramacionSemanalPersonal2(Object numempleado, Object puesto, Object nomempleado, Object lunes, Object martes, Object miercoles, Object jueves, Object viernes, Object sabado, Object domingo) {
        this.numempleado = (String)numempleado;
        this.puesto = (String)puesto;
        this.nomempleado = (String)nomempleado;
        this.lunes = (String)lunes;
        this.martes = (String)martes;
        this.miercoles = (String)miercoles;
        this.jueves = (String)jueves;
        this.viernes = (String)viernes;
        this.sabado = (String)sabado;
        this.domingo = (String)domingo;
    }

    public String getNumempleado() {
        return numempleado;
    }
    public void setNumempleado(String numempleado) {
        this.numempleado = numempleado;
    }



    public String getPuesto() {
        return puesto;
    }
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }



    public String getNomempleado() {
        return nomempleado;
    }
    public void setNomempleado(String nomempleado) {
        this.nomempleado = nomempleado;
    }



    public String getLunes() {
        return lunes;
    }
    public void setLunes(String lunes) {
        this.lunes = lunes;
    }



    public String getMartes() {
        return martes;
    }
    public void setMartes(String martes) {
        this.martes = martes;
    }



    public String getMiercoles() {
        return miercoles;
    }
    public void setMiercoles(String miercoles) {
        this.miercoles = miercoles;
    }



    public String getJueves() {
        return jueves;
    }
    public void setJueves(String jueves) {
        this.jueves = jueves;
    }



    public String getViernes() {
        return viernes;
    }
    public void setViernes(String viernes) {
        this.viernes = viernes;
    }



    public String getSabado() {
        return sabado;
    }
    public void setSabado(String sabado) {
        this.sabado = sabado;
    }



    public String getDomingo() {
        return domingo;
    }
    public void setDomingo(String domingo) {
        this.domingo = domingo;
    }
}
