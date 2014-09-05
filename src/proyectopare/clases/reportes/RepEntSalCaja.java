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
import proyectopare.clases.PARAMETROS;

/**
 *
 * @author Roman
 */
public class RepEntSalCaja {

    /** Creates a new instance of Reporte */
    public RepEntSalCaja(String fechaInicial, String fechaFinal,int opcion,String query) {
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.opcion = opcion;
        this.query = query;
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
    public boolean crearReporteJRViewer() {
        boolean error = true;
        try {
            jasperPrint = net.sf.jasperreports.engine.JasperFillManager.fillReport(
                    jasperReport, parametros, Reporte.getConnnection());
            JasperViewer jasperviewer = new JasperViewer(jasperPrint, false);
            jasperviewer.setTitle("Reporte de Caja");
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
    private void prepararReporte() {
        String paramRuta = "/proyectopare/clases/reportes/archivosjasper/";
        rutaJasper = getClass().getResourceAsStream(paramRuta + "reporteEntSalCaja.jasper");
        parametros = new HashMap();

//        parametros.put("sql_query", new String(query));
        parametros.put("PAR_F_INI", new String(fechaInicial));
        parametros.put("PAR_F_FIN", new String(fechaFinal));
        parametros.put("CENTRO_COSTOS", new Integer(PARAMETROS.ID_CENTRO_COSTOS));

        if (opcion == 1) {
            parametros.put("SUBREPORT_DIR",
                    this.getClass().getResourceAsStream(paramRuta + "reporteEntSalCaja_subreport0.jasper"));
            parametros.put("SUBREPORT_DIR1",
                    this.getClass().getResourceAsStream(paramRuta + "reporteEntSalCaja_subreport1.jasper"));
        }
        else {
            parametros.put("SUBREPORT_DIR", null);
            parametros.put("SUBREPORT_DIR1", null);
        }

        try {
            jasperReport = (JasperReport) JRLoader.loadObject(rutaJasper);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al Preparar el Reporte.",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return;
        }
    }
    private String fechaInicial;
    private String fechaFinal;
    private int opcion;
    private String query;

    private java.io.InputStream rutaJasper;
    private Map parametros;
    private JasperPrint jasperPrint;
    private JasperReport jasperReport;

}
