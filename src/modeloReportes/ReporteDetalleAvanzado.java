/*
    Clase donde se preparan los datos para el reporte de jasper
    correspondiente al detalle del turno actual (Desglosa los boletos pendientes,
    perdidos,cancelado y manuales)
 */
package modeloReportes;

import java.awt.print.PrinterJob;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import modelos.Estacionamiento;
import modelos.Main;
import modelos.Turno;
import modelos.TurnoDetalles;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author sistema
 */
public class ReporteDetalleAvanzado implements Runnable {
    private Turno turno;
    private Estacionamiento estacionamiento;
    public ReporteDetalleAvanzado(){
        this.turno = Main.getInstance().getTurnoActual();
        this.estacionamiento = Main.getInstance().getEstacionamiento();
    }
    
     public ReporteDetalleAvanzado(Turno turno){
        this.turno = turno;
        this.estacionamiento = Main.getInstance().getEstacionamiento();
    }
    
    public void generarReporte(){
        new Thread(this).start();
    }

    @Override
    public void run() {
         Iterator<Map.Entry<String, TurnoDetalles>> iterator = turno.getDetallesTurno().entrySet().iterator();
        while(iterator.hasNext()){
            String serie =  iterator.next().getKey();
          
            Map<String,Object> parametros = new HashMap<String, Object>();
            parametros.put("centroCostos",estacionamiento.getDescripcion());

            parametros.put("fechaTurno", turno.getFechaApertura());
            parametros.put("turno",turno.getTipoTurno());
            
    
            try {
                JasperReport reporte = (JasperReport) JRLoader.
                loadObject(new File(estacionamiento.getUrlReporte()+"/detalleAvanzado.jasper"));
//                loadObject(new File("/home/sistemas/proyectos/escritorio/src/reportes/detalleAvanzado.jasper"));
                JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(turno.getDetallesTurno().get(serie).getDetalleMovimientoAvanzado()));
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
                try {
                    String ruta = "/home/empleado/pare/cortes/reporteAvanzado-"+turno.getFechaApertura()+"-"+turno.getTipoTurno()+".pdf";
                    File archivo = new File(ruta);
                    BufferedWriter bw;
                    if(archivo.exists()) {
                        bw = new BufferedWriter(new FileWriter(archivo));
                     } else {
                        bw = new BufferedWriter(new FileWriter(archivo));
                      }
                    bw.close();

                    JasperExportManager.exportReportToPdfFile(jasperPrint,ruta );
                     } catch (IOException ex) {
                        Logger.getLogger(ReporteCorteTurno.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (JRException ex) {
                    ex.printStackTrace();
                }
            }
        
    }
    
}
