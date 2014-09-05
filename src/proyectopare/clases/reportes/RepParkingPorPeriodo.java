/*
 * Reporte.java
 *
 * Created on 5 de junio de 2007, 13:05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package proyectopare.clases.reportes;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Roman
 */

public class RepParkingPorPeriodo {

    public RepParkingPorPeriodo(String fechaInicial,String fechaFinal,
            String horaInicial,String horaFinal,String nombreJasper) {
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.horaInicial = horaInicial;
        this.horaFinal = horaFinal;
        this.nombreJasper = nombreJasper;
        prepararReporte();
    }

    /**
     * Sirve para elaborar reportes y mantenerlos en memoria principal (Vista Previa en memoria RAM
     * y sin guardarlos en disco duro)
     *
     * @exception JRException  si ocurre un error al darle forma al reporte o al leer el classpath
     * @exception Exception si ocurre un error al exportarlo
     * @exception IOException si ocurre un error al ejecutarlo o mandarlo a llamar
     */
    public boolean crearReporteJRViewer(){
        boolean error = true;
        try {
            jasperPrint = net.sf.jasperreports.engine.JasperFillManager.fillReport(
                    jasperReport, parametros, Reporte.getConnnection());
            JasperViewer jasperviewer = new JasperViewer(jasperPrint, false);
            jasperviewer.setTitle("Entradas y Salidas del Estacionamiento.");
            jasperviewer.setExtendedState(0);
            jasperviewer.setFocusable(true);
            jasperviewer.setFocusableWindowState(true);
            jasperviewer.setVisible(true);
        } catch (JRException ex) {
            ex.printStackTrace();
            error = false;
        } catch (java.lang.Exception e) {
            e.printStackTrace();
            error = false;
        } finally {
            Reporte.closeConnnection();
            return error;
        }
    }

    @SuppressWarnings("unchecked")
    private void prepararReporte() {
        String paramRuta = "/proyectopare/clases/reportes/archivosjasper/";
        rutaJasper = this.getClass().getResourceAsStream(paramRuta + nombreJasper + ".jasper");
        parametros = new HashMap();
        parametros.put("PAR_F_INI",new java.lang.String(fechaInicial));    //Insertar parametro al reporte
        parametros.put("PAR_F_FIN",new java.lang.String(fechaFinal));    //Insertar parametro al reporte
        parametros.put("PAR_HORA_INI",new java.lang.String(horaInicial));    //Insertar parametro al reporte
        parametros.put("PAR_HORA_FIN",new java.lang.String(horaFinal));    //Insertar parametro al reporte

        try {
            jasperReport = (JasperReport) JRLoader.loadObject(rutaJasper);
        }
        catch (JRException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error al Preparar el Reporte.",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return;
        }
    }

    private java.io.InputStream rutaJasper;
    private Map parametros;
    private JasperPrint jasperPrint;
    private JasperReport jasperReport;

    private String fechaInicial;
    private String fechaFinal;
    private String horaFinal;
    private String nombreJasper;
    private String horaInicial;

}
