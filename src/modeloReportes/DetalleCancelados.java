package modeloReportes;

import java.awt.print.PrinterJob;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.*;
import modelos.DetallesMovimiento;
import modelos.Estacionamiento;
import modelos.Turno;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

public class DetalleCancelados implements Runnable  {
    private Turno turno;
   // private ArrayList<DetallesMovimiento> detalleM;
    private Estacionamiento estacionamiento;

    public DetalleCancelados(Turno turno,Estacionamiento estacionamiento) {
        this.turno = turno;
        this.estacionamiento = estacionamiento;
        //this.detalleM =  detalleM;
    }

    public void generarReporte(){
       new Thread(this).start();
    }

    @Override
    public void run() {
        Iterator<Entry<String, Turno>> iterator = turno.getTurnosImprimir().entrySet().iterator();
        while(iterator.hasNext()){
            Turno turno = iterator.next().getValue();
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("fechaTurno", turno.getFechaApertura());
            parametros.put("operador", turno.getOperador().getNombre());
            parametros.put("fecha", turno.getFechaApertura());
            parametros.put("fechaApertura",  turno.getHoraApertura() );
            parametros.put("fechaCierre",    turno.getHoraCierre()  );
            parametros.put("folioInicial", turno.getFolioInicial());
            parametros.put("folioFinal",turno.getFolioFinal());
            parametros.put("numBoletos",turno.getNoBol());
            parametros.put("pendienteTA",turno.getNoBolTurnoA());
            parametros.put("cancelados",turno.getNoBolCancelados());
            parametros.put("perdidos",turno.getNoBolPerdidos());
            parametros.put("cobrados",turno.getNoBolCobrados());
            parametros.put("pendientes",turno.getNoBolTurnoS());
            parametros.put("total",turno.getTotal());
            parametros.put("turno",turno.getTipoTurno());
            //parametros.put("numBoletosCobrados",turno.getNoBolCancelados() +turno.getNoBolCobrados()
             //       + turno.getNoBolPerdidos());
            parametros.put("centroCostos",estacionamiento.getDescripcion());

            if(turno.getDetallesMovimiento().size()<1){
                turno.getDetallesMovimiento().add(new DetallesMovimiento());
            }  
            try {
                JasperReport reporte = (JasperReport) JRLoader.
                loadObject(new File("/home/empleado/pare/reportes/detalleCancelado.jasper"));
                //loadObject(new File("/home/sistema/proyectos/escritorio/src/reportes/corteTurno.jasper"));
                JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(turno.getDetallesMovimiento()));
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
                
                JasperExportManager.exportReportToPdfFile(jasperPrint, "/home/empleado/pare/cortes/reporte_cancelado-"+turno.getFechaApertura()+"-"+turno.getTipoTurno()+".pdf");
                

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
