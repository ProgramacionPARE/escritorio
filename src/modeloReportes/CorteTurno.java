package modeloReportes;

import java.awt.print.PrinterJob;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.*;
import modelos.DetalleTurno;
import modelos.DetallesMovimiento;
import modelos.Estacionamiento;
import modelos.Turno;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

public class CorteTurno implements Runnable  {
    private Turno turno;
   // private ArrayList<DetallesMovimiento> detalleM;
    private Estacionamiento estacionamiento;

    public CorteTurno(Turno turno,Estacionamiento estacionamiento) {
        this.turno = turno;
        this.estacionamiento = estacionamiento;
        //this.detalleM =  detalleM;
    }

    public void generarReporte(){
       new Thread(this).start();
    }

    @Override
    public void run() {
        Iterator<Entry<String, DetalleTurno>> iterator = turno.getDetallesTurno().entrySet().iterator();
        while(iterator.hasNext()){
            String serie =  iterator.next().getKey();
            long totalCobrados = turno.getDetallesTurno().get(serie).getNoBolCobrados() +
                                turno.getDetallesTurno().get(serie).getNoBolContra() +
                                turno.getDetallesTurno().get(serie).getNoBolManual();
            Map<String,Object> parametros = new HashMap<String, Object>();
            parametros.put("centroCostos",estacionamiento.getDescripcion());

            parametros.put("fechaTurno", turno.getFechaApertura());
            parametros.put("turno",turno.getTipoTurno());
            
            parametros.put("operador", turno.getEmpleadoEntrada().getNombre());
            parametros.put("fecha", turno.getFechaApertura());
            parametros.put("fechaApertura",  turno.getHoraApertura() );
            parametros.put("fechaCierre",    turno.getHoraCierre()  );
            parametros.put("folioInicial", turno.getDetallesTurno().get(serie).getFolioInicial());
            parametros.put("folioFinal",turno.getDetallesTurno().get(serie).getFolioFinal());
            parametros.put("boletoPromedio",turno.getDetallesTurno().get(serie).getTotal());
            
            parametros.put("numBoletos",turno.getDetallesTurno().get(serie).getNoBol());
            parametros.put("pendienteTA",turno.getDetallesTurno().get(serie).getNoBolTurnoA());
            
            parametros.put("cobrados",totalCobrados);
            parametros.put("cobroNormal",turno.getDetallesTurno().get(serie).getNoBolCobrados());
            parametros.put("cobroContra",turno.getDetallesTurno().get(serie).getNoBolContra());
            parametros.put("cobroManual",turno.getDetallesTurno().get(serie).getNoBolManual());
            
            parametros.put("cancelados",turno.getDetallesTurno().get(serie).getNoBolCancelados());
            parametros.put("perdidos",turno.getDetallesTurno().get(serie).getNoBolPerdidos());
            
            parametros.put("pendientes",turno.getDetallesTurno().get(serie).getNoBolTurnoS());
            
            parametros.put("total",turno.getDetallesTurno().get(serie).getTotal());
            //parametros.put("numBoletosCobrados",turno.getNoBolCancelados() +turno.getNoBolCobrados()
             //       + turno.getNoBolPerdidos());
            
            if(turno.getDetallesTurno().get(serie).getDetalleMovimiento().size()<1){
                turno.getDetallesTurno().get(serie).getDetalleMovimiento().add(new DetallesMovimiento());
            }  
            try {
                JasperReport reporte = (JasperReport) JRLoader.
                loadObject(new File("/home/empleado/pare/reportes/corteTurno.jasper"));
                //loadObject(new File("/home/sistema/proyectos/escritorio/src/reportes/corteTurno.jasper"));
                JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(turno.getDetallesTurno().get(serie).getDetalleMovimiento()));
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
                    String ruta = "/home/empleado/pare/cortes/reporte-"+turno.getFechaApertura()+"-"+turno.getTipoTurno()+".pdf";
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
                    Logger.getLogger(CorteTurno.class.getName()).log(Level.SEVERE, null, ex);
                }

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


}
