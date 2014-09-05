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
public class RepReciboPAgo {

    /** Creates a new instance of Reporte */
    public RepReciboPAgo(String nombre,String rfc,String importeBoleto,String iva,
            String total,String horas,String importeLetra,String idCC,String folio) {
        this.nombre = nombre;
        this.rfc = rfc;
        this.importeBoleto = importeBoleto;
        this.iva = iva;
        this.total = total;
        this.horas = horas;
        this.importeLetra = importeLetra;
        this.idCC = idCC;
        this.folio = folio;
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
            jasperviewer.setTitle("Recibo de Pago");
            jasperviewer.setExtendedState(0);
            jasperviewer.setFocusable(true);

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
        String paramRuta = "/proyectopare/clases/reportes/archivosjasper/reporteReciboPago.jasper";
        rutaJasper = getClass().getResourceAsStream(paramRuta);
        parametros = new HashMap();
        parametros.put("P_NOMBRE", new String(nombre)); //Insertar parametro para el query
        parametros.put("P_IMPORTE_LETRA", new String(importeLetra)); //Insertar parametro para el query
        parametros.put("P_IMPORTE_BOLETO", new String(importeBoleto)); //Insertar parametro para el query
        parametros.put("P_IVA", new String(iva)); //Insertar parametro para el query
        parametros.put("P_TOTAL", new String(total)); //Insertar parametro para el query
        parametros.put("P_HORAS", new String(horas)); //Insertar parametro para el query
        parametros.put("P_RFC", new String(rfc)); //Insertar parametro para el query
        parametros.put("CENTRO_COSTOS", new String(idCC)); //Insertar parametro para el query
        parametros.put("PARAM_PROGRESIVO", new String(folio)); //Insertar parametro para el query
        
        try {
            jasperReport = (JasperReport) JRLoader.loadObject(rutaJasper);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al Preparar el Reporte.",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return;
        }
    }
    private java.io.InputStream rutaJasper;
    private Map parametros;
    private JasperPrint jasperPrint;
    private JasperReport jasperReport;

    private String importeBoleto;
    private String horas;
    private String nombre;
    private String rfc;
    private String iva;
    private String total;
    private String importeLetra;
    private String idCC;
    private String folio;
}
