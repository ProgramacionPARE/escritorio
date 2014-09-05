/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectopare.clases;

/**
 *
 * @author mandrivalinux
 */
public class SolicitudPapeleria2 {
    private String articulos;
    private String cantidad;
    private String delfolio;
    private String alfolio;

    public SolicitudPapeleria2(){
        articulos="";
        cantidad="";
        delfolio="";
        alfolio="";

    }

    public SolicitudPapeleria2(Object articulos, Object cantidad, Object delfolio, Object alfolio) {
        this.articulos = (String)articulos;
        this.cantidad = (String)cantidad;
        this.delfolio = (String)delfolio;
        this.alfolio = (String)alfolio;
    }


    public String getArticulos() {
        return articulos;
    }
    public void setArticulos(String articulos) {
        this.articulos = articulos;
    }



    public String getCantidad() {
        return cantidad;
    }
    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }



    public String getDelfolio() {
        return delfolio;
    }
    public void setDelfolio(String delfolio) {
        this.delfolio = delfolio;
    }



    public String getAlfolio() {
        return alfolio;
    }
    public void setAlfolio(String alfolio) {
        this.alfolio = alfolio;
    }


}
