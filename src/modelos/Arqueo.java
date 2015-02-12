/*
    Modelo de un arqueo
    
*/
package modelos;

import modelosReportesAux.DetallesArqueo;
import java.util.ArrayList;


public class Arqueo {
    long id;
    long folioInicial;
    long folioFinal;
    float subTotalM;
    float subTotalB;
    float subTotalR;
    float total;
    String fecha;
    ArrayList <DetallesArqueo> detallesArqueo;

    public Arqueo(long folioInicial, long folioFinal, float subTotalM, float subTotalB,float subTotalR,float total, String fecha) {
        this.folioInicial = folioInicial;
        this.folioFinal = folioFinal;
        this.subTotalM = subTotalM;
        this.subTotalB = subTotalB;
        this.subTotalR = subTotalR;
        this.total = total;
        this.fecha = fecha;
        this.detallesArqueo = new ArrayList <DetallesArqueo>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getSubTotalR() {
        return subTotalR;
    }

    public void setSubTotalR(float subTotalR) {
        this.subTotalR = subTotalR;
    }

    public long getFolioInicial() {
        return folioInicial;
    }

    public void setFolioInicial(long folioInicial) {
        this.folioInicial = folioInicial;
    }

    public long getFolioFinal() {
        return folioFinal;
    }

    public void setFolioFinal(long folioFinal) {
        this.folioFinal = folioFinal;
    }

    public float getSubTotalM() {
        return subTotalM;
    }

    public void setSubTotalM(float subTotalM) {
        this.subTotalM = subTotalM;
    }

    public float getSubTotalB() {
        return subTotalB;
    }

    public void setSubTotalB(float subTotalB) {
        this.subTotalB = subTotalB;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ArrayList<DetallesArqueo> getDetallesArqueo() {
        return detallesArqueo;
    }

    public void setDetallesArqueo(ArrayList<DetallesArqueo> detallesArqueo) {
        this.detallesArqueo = detallesArqueo;
    }
    
    
 }
