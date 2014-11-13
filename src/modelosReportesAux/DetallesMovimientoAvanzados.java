/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelosReportesAux;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import modelos.Auto;

/**
 *
 * @author sistema
 */
public class DetallesMovimientoAvanzados {
    private String tipo;
    private String progresivo;
    private String fechaEntrada;
    private String horaEntrada;
    private String fechaSalida;
    private String horaSalida;
    private int horas;
    private int minutos;
    private float monto;
    private String razon;
    private String fechaEntradaM;
    private String horaEntradaM;
    private String fechaSalidaM;
    private String horaSalidaM;


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getProgresivo() {
        return progresivo;
    }

    public void setProgresivo(String progresivo) {
        this.progresivo = progresivo;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public String getFechaEntradaM() {
        return fechaEntradaM;
    }

    public void setFechaEntradaM(String fechaEntradaM) {
        this.fechaEntradaM = fechaEntradaM;
    }

    public String getHoraEntradaM() {
        return horaEntradaM;
    }

    public void setHoraEntradaM(String horaEntradaM) {
        this.horaEntradaM = horaEntradaM;
    }

    public String getFechaSalidaM() {
        return fechaSalidaM;
    }

    public void setFechaSalidaM(String fechaSalidaM) {
        this.fechaSalidaM = fechaSalidaM;
    }

    public String getHoraSalidaM() {
        return horaSalidaM;
    }

    public void setHoraSalidaM(String horaSalidaM) {
        this.horaSalidaM = horaSalidaM;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }


  
    public DetallesMovimientoAvanzados(String tipo) {
        this.tipo = tipo;
    }

    public DetallesMovimientoAvanzados(String tipo, String progresivo, String fechaEntrada, String horaEntrada, String fechaSalida, String horaSalida, int horas, int minutos, float monto, String razon) {
        this.tipo = tipo;
        this.progresivo = progresivo;
        this.fechaEntrada = fechaEntrada;
        this.horaEntrada = horaEntrada;
        this.fechaSalida = fechaSalida;
        this.horaSalida = horaSalida;
        this.horas = horas;
        this.minutos = minutos;
        this.monto = monto;
        this.razon = razon;
    }

    public DetallesMovimientoAvanzados(String tipo, String progresivo, String fechaEntrada, String horaEntrada, String fechaSalida, String horaSalida, int horas, int minutos, float monto, String razon, String fechaEntradaM, String horaEntradaM, String fechaSalidaM, String horaSalidaM) {
        this.tipo = tipo;
        this.progresivo = progresivo;
        this.fechaEntrada = fechaEntrada;
        this.horaEntrada = horaEntrada;
        this.fechaSalida = fechaSalida;
        this.horaSalida = horaSalida;
        this.horas = horas;
        this.minutos = minutos;
        this.monto = monto;
        this.razon = razon;
        this.fechaEntradaM = fechaEntradaM;
        this.horaEntradaM = horaEntradaM;
        this.fechaSalidaM = fechaSalidaM;
        this.horaSalidaM = horaSalidaM;
    }
    
    
    public static ArrayList<DetallesMovimientoAvanzados> generarDetalles(ArrayList<Auto> autosPendientesTurnoActual, List<Auto> autosBoletoCanceladoTurnoActual, 
            List<Auto> autosBoletoPerdidoTurnoActual, ArrayList<Auto> autosManualesTurnoActual, ArrayList<Auto> autosContraTurnoActual) {
        
        ArrayList<DetallesMovimientoAvanzados> detalles = new ArrayList();
        //Detalle de Pendientes
        if(autosPendientesTurnoActual.size()>0)
            detalles.add(new DetallesMovimientoAvanzados("TituloPendiente"));
        Iterator<Auto> iteratorPendiente = autosPendientesTurnoActual.iterator();
        while(iteratorPendiente.hasNext()){
            Auto next = iteratorPendiente.next();
            detalles.add(new DetallesMovimientoAvanzados("Pendiente",next.getProgresivo(),next.getFechaEntrada(),next.getHoraEntrada(),
            "-","-",next.getHorasTangibles(),next.getMinutosTangibles(),next.getMontoTangible(),""));
        }
        //Detalle de Cancelados
        if(autosBoletoCanceladoTurnoActual.size()>0)
            detalles.add(new DetallesMovimientoAvanzados("TituloCancelado"));
        Iterator<Auto> iteratorCancelado = autosBoletoCanceladoTurnoActual.iterator();
        while(iteratorCancelado.hasNext()){
            Auto next = iteratorCancelado.next();
            detalles.add(new DetallesMovimientoAvanzados("Cancelado",next.getProgresivo(),next.getFechaEntrada(),next.getHoraEntrada(),
            next.getFechaSalida(),next.getHoraSalida(),next.getHorasTangibles(),next.getMinutosTangibles(),next.getMontoTangible(),""));
        }
         //Detalle de Perdidos
        if(autosBoletoPerdidoTurnoActual.size()>0)
            detalles.add(new DetallesMovimientoAvanzados("TituloPerdido"));
        Iterator<Auto> iteratorPerdido = autosBoletoPerdidoTurnoActual.iterator();
        while(iteratorPerdido.hasNext()){
            Auto next = iteratorPerdido.next();
            detalles.add(new DetallesMovimientoAvanzados("Perdido",next.getProgresivo(),next.getFechaEntrada(),next.getHoraEntrada(),
            next.getFechaSalida(),next.getHoraSalida(),next.getHorasTangibles(),next.getMinutosTangibles(),next.getMontoTangible(),
            ""));
        }
        
        //Detalle de Manuales
        if(autosManualesTurnoActual.size()>0)
            detalles.add(new DetallesMovimientoAvanzados("TituloManual"));
        Iterator<Auto> iteratorManual = autosManualesTurnoActual.iterator();
        while(iteratorManual.hasNext()){
            Auto next = iteratorManual.next();
            detalles.add(new DetallesMovimientoAvanzados("Manual",next.getProgresivo(),next.getFechaEntrada(),next.getHoraEntrada(),
            next.getFechaSalida(),next.getHoraSalida(),next.getHorasTangibles(),next.getMinutosTangibles(),next.getMontoTangible(),next.getBoletoManual().getRazonCobroManual(),
            next.getBoletoManual().getFechaEntradaM(),next.getBoletoManual().getHoraEntradaM(),next.getBoletoManual().getFechaSalidaM(),next.getBoletoManual().getHoraSalidaM()));
        }
        
        
       return detalles;
    }
}
