package modeloReportes;

import ModelosAux.Tiempo;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.*;
import modelos.Auto;
import modelos.Estacionamiento;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReporteFolios  {
       List<Auto> autos;
       Estacionamiento estacionamiento;

    public ReporteFolios(List<Auto> autos,Estacionamiento estacionamiento,boolean isOrdenado) {
        this.autos = autos;
        this.estacionamiento = estacionamiento;
        if(isOrdenado)
            autos = Auto.ordenarByProgresivo(autos);
    }

    public void generarReporte(){
        Map<String, Object> parametros = new HashMap<String, Object>();
//        parametros.put("fechaTurno", turno.getFechaApertura());
//        parametros.put("operador", turno.getOperador().getNombre());
        parametros.put("fecha", Tiempo.getFecha()+" "+Tiempo.getHora() );

//        parametros.put("numBoletos",turno.getNoBol());
//        parametros.put("pendienteTA",turno.getNoBolTurnoA());
//        parametros.put("cancelados",turno.getNoBolCancelados());
//        parametros.put("perdidos",turno.getNoBolPerdidos());
//        parametros.put("cobrados",turno.getNoBolCobrados());
//        parametros.put("boletosTotales",(turno.getNoBolCobrados()+turno.getNoBolPerdidos()));
//        parametros.put("pendientes",turno.getNoBolTurnoS());
//        parametros.put("total",turno.getTotal());
//        parametros.put("turno",turno.getTipoTurno());
        parametros.put("titulo",estacionamiento.getDescripcion());
        
        if(autos.size()<1){
            autos.add(new Auto());
        }  
        try {
            JasperReport reporte = (JasperReport) JRLoader.
            loadObject(new File("/home/empleado/pare/reportes/folios.jasper"));
            //loadObject(new File("/home/sistema/proyectos/escritorio/src/reportes/folios.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(autos));
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
      
//            exporter = new JRPrintServiceExporter();
//            SimplePrintServiceExporterConfiguration configuration = new SimplePrintServiceExporterConfiguration();
//            configuration.setPrintService((PrintService) jasperPrint);
//            configuration.setPrintRequestAttributeSet(printRequestAttributeSet);
//            configuration.setPrintServiceAttributeSet(services[selectedService].getAttributes());
//            configuration.setDisplayPageDialog(false);
//            configuration.setDisplayPrintDialog(false);
//            exporter.setConfiguration(configuration);
//            exporter.exportReport();      
           
        } catch (JRException ex) {
            ex.printStackTrace();
        }
}




}
