/*
    Clase donde se preparan los datos para el reporte de jasper
    correspondiente al los retiros parciales (Sumatoria de los retiros del turno
    actual)
*/

package modeloReportes;

import ModelosAux.Tiempo;
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
import modelos.Estacionamiento;
import modelos.Main;
import modelos.RetiroParcial;
import modelos.Turno;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

public class RetirosParciales  {
    private Turno turno;
   // private ArrayList<DetallesMovimiento> detalleM;
    private Estacionamiento estacionamiento;

    public RetirosParciales() {
        this.turno = Main.getInstance().getTurnoActual();
        this.estacionamiento = Main.getInstance().getEstacionamiento();
    }
    public RetirosParciales(Turno turno) {
        this.turno = turno;
        this.estacionamiento = Main.getInstance().getEstacionamiento();
    }

    public void generarReporte(){
        ArrayList<RetiroParcial> retirosParciales = turno.getRetirosParciales();
        Map<String, Object> parametros = new HashMap<String, Object>();
        Iterator<RetiroParcial> iterator = turno.getRetirosParciales().iterator();
        
        float total = 0;
        while(iterator.hasNext()){
            total += iterator.next().getMonto();
        }
        parametros.put("fechaTurno", turno.getFechaApertura());
        parametros.put("operador", turno.getEmpleadoEntrada().getNombre());
        parametros.put("fecha", Tiempo.getFecha()+" "+Tiempo.getHora() );
        parametros.put("turno",turno.getTipoTurno());
        parametros.put("centroCostos",estacionamiento.getDescripcion());
        parametros.put("total", total);

            try {
                JasperReport reporte = (JasperReport) JRLoader.
                loadObject(new File(estacionamiento.getUrlReporte()+"/retirosParciales.jasper"));
                //loadObject(new File("/home/sistemas/proyectos/escritorio/src/reportes/retirosParciales.jasper"));
                JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(turno.getRetirosParciales()));
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
