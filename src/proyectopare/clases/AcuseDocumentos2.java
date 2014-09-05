/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectopare.clases;

/**
 *
 * @author mandrivalinux
 */
public class AcuseDocumentos2 {
    private String fecha;
    private String documentos;
    private String recibio;

    public AcuseDocumentos2(){
        fecha="";
        documentos="";
        recibio="";

    }

    public AcuseDocumentos2(Object fecha, Object documentos, Object recibio) {
        this.fecha = (String)fecha;
        this.documentos = (String)documentos;
        this.recibio = (String)recibio;
    }

    
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }



    public String getDocumentos() {
        return documentos;
    }
    public void setDocumentos(String documentos) {
        this.documentos = documentos;
    }



    public String getRecibio() {
        return recibio;
    }
    public void setRecibio(String recibio) {
        this.recibio = recibio;
    }


}
