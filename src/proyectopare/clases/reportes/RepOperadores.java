/*
 * To change this template, choose Tools | Templates
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
public class RepOperadores {

    /** Creates a new instance of Reporte */
    public RepOperadores(String fecha) {
        this.fecha_ini = fecha;
        this.titulo = "Reporte de entradas de operadores";
        prepararReporte("ReporteEntradaOperadores.jasper", false);
    }

    public RepOperadores(String fecha1,String fecha2) {
        this.fecha_ini = fecha1;
        this.fecha_fin = fecha2;
        this.titulo = "Reporte de entradas de operadores";
        prepararReporte("ReporteEntradaPeriodo.jasper", true);
    }

    /**
     * Sirve para elaborar reportes y mantenerlos en memoria principal (Vista Previa en memoria RAM
     * y sin guardarlos en disco duro)
     *
     * @exception JRException  si ocurre un error al darle forma al reporte o al leer el classpath
     * @exception Exception si ocurre un error al exportarlo
     * @exception IOException si ocurre un error al ejecutarlo o mandarlo a llamar
     */
    public boolean crearReporteJRViewer() {
        boolean error = true;
        try {
            jasperPrint = net.sf.jasperreports.engine.JasperFillManager.fillReport(
                    jasperReport, parametros, Reporte.getConnnection());
            JasperViewer jasperviewer = new JasperViewer(jasperPrint, false);
            jasperviewer.setTitle(titulo);
            jasperviewer.setExtendedState(0);
            jasperviewer.setFocusable(true);
            jasperviewer.setFocusableWindowState(true);
            jasperviewer.setVisible(true);
//            jasperviewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "Error al generar la vista previa:\n" + ex.getMessage(), "Error al crear el JRViewer ",
		javax.swing.JOptionPane.ERROR_MESSAGE);
            error = false;
        } catch (java.lang.Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error al crear el JRViewer ", javax.swing.JOptionPane.ERROR_MESSAGE);
            error = false;
        } finally {
            Reporte.closeConnnection();
            return error;
        }
    }

    @SuppressWarnings("unchecked")
    private void prepararReporte(String ruta, boolean periodo) {
        String paramRuta = "/proyectopare/clases/reportes/archivosjasper/"+ruta;
        rutaJasper = getClass().getResourceAsStream(paramRuta);
        parametros = new HashMap();

        if(periodo){
            parametros.put("Fecha_Ini", new String(fecha_ini));
            parametros.put("Fecha_Fin", new String(fecha_fin));
        }
        else
            parametros.put("Fecha", new String(fecha_ini));    //Insertar parametro para el query

        try {
            jasperReport = (JasperReport) JRLoader.loadObject(rutaJasper);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al Preparar el Reporte.",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return;
        }
    }
    private String fecha_ini,fecha_fin;
    private String titulo;
    private java.io.InputStream rutaJasper;
    private Map parametros;
    private JasperPrint jasperPrint;
    private JasperReport jasperReport;

}
