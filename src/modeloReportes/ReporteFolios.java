/*
    Clase donde se preparan los datos para el reporte de jasper
    correspondiente a los folios del turno actual (separa los que salieron, 
    de los que sigen dentro)
*/

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

        parametros.put("fecha", Tiempo.getFecha()+" "+Tiempo.getHora() );


        parametros.put("titulo",estacionamiento.getDescripcion());
        
        if(autos.size()<1){
            autos.add(new Auto());
        }  
        try {
            JasperReport reporte = (JasperReport) JRLoader.
            loadObject(new File(estacionamiento.getUrlReporte()+"/folios.jasper"));
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
      
           
        } catch (JRException ex) {
            ex.printStackTrace();
        }
}




}
