package modeloReportes;

import modelos.Turno;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.*;
import modelos.TurnoDetalles;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReporteCorteDiario  {
    private ArrayList <Turno> turnos;
    String fecha;

    public ReporteCorteDiario(ArrayList <Turno> turnos,String fecha) {
        this.turnos = turnos;
        this.fecha = fecha;
    }

    public void generarReporte(){
        Map<String, Object> parametros = new HashMap<String, Object>();
        
        long tFolioInicial=0;
        long tFolioFinal = 0;
        int tNoBol = 0;
        int tNoBolTurnoA = 0;
        int tNoBolCancelados = 0;
        int tNoBolPerdidos = 0;
        int tNoBolCobrados = 0;
        int tNoBolTurnoS = 0;
        float tTotal=0;
        ArrayList<TurnoDetalles> turnoDetalles = new ArrayList();
        Iterator<Turno> turnosTemp = turnos.iterator();
        while(turnosTemp.hasNext()){
            Turno turnoTemporal = turnosTemp.next();
            Iterator<Map.Entry<String, TurnoDetalles>> detalleTurnoTemp = turnoTemporal.getDetallesTurno().entrySet().iterator();
            while(detalleTurnoTemp.hasNext()){
                Map.Entry<String, TurnoDetalles> next = detalleTurnoTemp.next();
                next.getValue().setTipoTurno(turnoTemporal.getTipoTurno());
                turnoDetalles.add( next.getValue());
                tNoBol += next.getValue().getNoBol();
                tNoBolCancelados += next.getValue().getNoBolCancelados();
                tNoBolPerdidos += next.getValue().getNoBolPerdidos();
                tNoBolCobrados += next.getValue().getNoBolCobrados();
                tTotal += next.getValue().getTotal();
            }
            
        }
        //tFolioInicial = turnos.get(0).getFolioInicial();
        //tFolioFinal = turnos.get(turnos.size()-1).getFolioFinal();
         
           
        parametros.put("fecha",fecha); 
        parametros.put("tFolioInicial",tFolioInicial);
        parametros.put("tFolioFinal",tFolioFinal); 
        parametros.put("tNumBol",tNoBol);
        parametros.put("tBoletosTA",tNoBolTurnoA); 
        parametros.put("tBoletosCancelados",tNoBolCancelados);
        parametros.put("tBoletosPerdidos",tNoBolPerdidos);
        parametros.put("tBoletosCobrados",tNoBolCobrados);
        parametros.put("tBoletosTS",tNoBolTurnoS);
        parametros.put("tSubTotal",tTotal); 
         

        try {
            JasperReport reporte = (JasperReport) JRLoader.
            //loadObject(new File("/home/empleado/pare/reportes/corteDiario.jasper"));
            loadObject(new File("/home/sistemas/proyectos/escritorio/src/reportes/corteDiario.jasper"));
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(turnoDetalles));
            PrinterJob job = PrinterJob.getPrinterJob();
            PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
            int selectedService = 0;
            for(int i = 0; i < services.length;i++){
                if(services[i].getName().contains("Star")){
                    selectedService = i;
                    }
                }
            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
            printRequestAttributeSet.add(MediaSize.ISO.A7.getMediaSizeName());
            printRequestAttributeSet.add(new Copies(1));
            JRPrintServiceExporter exporter;
            exporter = new JRPrintServiceExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, services[selectedService]);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, services[selectedService].getAttributes());
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
            exporter.exportReport();
        } catch (JRException ex) {
            ex.printStackTrace();
        }
}




}
