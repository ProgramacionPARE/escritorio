

package modelos;


public class DetallesArqueo {
    float denominacion;
    int cantidad;
    float importe;
    String tipo;

    public DetallesArqueo(float denominacion, int cantidad, float importe, String tipo) {
        this.denominacion = denominacion;
        this.cantidad = cantidad;
        this.importe = importe;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

   

    public float getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(float denominacion) {
        this.denominacion = denominacion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }
    
    
    
    
}
