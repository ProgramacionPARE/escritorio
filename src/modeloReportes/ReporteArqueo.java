/*
    Clase donde se preparan los datos para el reporte de jasper
    correspondiente a la emision de arqueo
*/


package modeloReportes;

import ModelosAux.Tiempo;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.*;
import modelos.Arqueo;
import modelos.Estacionamiento;
import modelos.Turno;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReporteArqueo  {
    private Turno turno;
    private Arqueo arqueo;
    private Estacionamiento estacionamiento;
    private String serie;
    
    public ReporteArqueo(Turno turno, Arqueo arqueo, Estacionamiento estacionamiento,String serie) {
        this.turno = turno;
        this.arqueo = arqueo;
        this.estacionamiento =  estacionamiento;

    }

    public void generarReporte(){
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("fechaTurno", turno.getFechaApertura());
        parametros.put("operador", turno.getEmpleadoEntrada().getNombre());
        parametros.put("fecha", Tiempo.getFecha()+" "+Tiempo.getHora() );
        parametros.put("folioInicial", turno.getDetallesTurno().get(serie).getFolioInicial());
        parametros.put("folioFinal",turno.getDetallesTurno().get(serie).getFolioFinal());
        parametros.put("numBoletos",turno.getDetallesTurno().get(serie).getNoBol());
        parametros.put("pendienteTA",turno.getDetallesTurno().get(serie).getNoBolTurnoA());
        parametros.put("cancelados",turno.getDetallesTurno().get(serie).getNoBolCancelados());
        parametros.put("perdidos",turno.getDetallesTurno().get(serie).getNoBolPerdidos());
        parametros.put("cobrados",turno.getDetallesTurno().get(serie).getNoBolCobrados());
        parametros.put("boletosTotales",(turno.getDetallesTurno().get(serie).getNoBolCobrados()+
                                        turno.getDetallesTurno().get(serie).getNoBolPerdidos()));
        parametros.put("pendientes",turno.getDetallesTurno().get(serie).getNoBolTurnoS());
        parametros.put("turno",turno.getTipoTurno());
        parametros.put("centroCostos",estacionamiento.getDescripcion());
        //Arqueo
        parametros.put("subTotalB",arqueo.getSubTotalB());
        parametros.put("subTotalM",arqueo.getSubTotalM());
        parametros.put("subTotalR",arqueo.getSubTotalR());
        parametros.put("total",arqueo.getTotal());
        parametros.put("diferencia",arqueo.getTotal()-turno.getDetallesTurno().get(serie).getTotal());
        
        try {
            JasperReport reporte = (JasperReport) JRLoader.
            loadObject(new File(estacionamiento.getUrlReporte()+"/arqueo.jasper"));
            //loadObject(new File("/home/sistema/proyectos/escritorio/src/reportes/arqueo.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(arqueo.getDetallesArqueo()));
            PrinterJob job = PrinterJob.getPrinterJob();
            PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
            int selectedService = 0;
            for(int i = 0; i < services.length;i++){
                if(services[i].getName().contains("Star")){
                    selectedService = i;
                    }
                }
            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
            printRequestAttributeSet.add(new Copies(1));
            JRPrintServiceExporter exporter;
            exporter = new JRPrintServiceExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, services[selectedService]);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, services[selectedService].getAttributes());
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
            exporter.exportReport();
      
           
        } catch (JRException ex) {
            ex.printStackTrace();
        }
}




}
