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
public class RepMovsClientes {

    /** Creates a new instance of Reporte */
    public RepMovsClientes(String folio,String nombre,String localidadCliente,String dirCliente,String telCliente) {
        this.folio = folio;
        this.nombre = nombre;
        this.localidadCliente = localidadCliente;
        this.dirCliente = dirCliente;
        this.telCliente = telCliente;
        prepararReporte();
    }

    public boolean crearReporteJRViewer() {
        boolean error = true;
        try {
            jasperPrint = net.sf.jasperreports.engine.JasperFillManager.fillReport(
                    jasperReport, parametros, Reporte.getConnnection());
            JasperViewer jasperviewer = new JasperViewer(jasperPrint, false);
            jasperviewer.setTitle("Movimientos de clientes");
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
        String paramRuta = "/proyectopare/clases/reportes/archivosjasper/reporteMovimientosClientes.jasper";
        rutaJasper = getClass().getResourceAsStream(paramRuta);
        parametros = new HashMap();

        parametros.put("PARAM_FOLIO", new Long(folio));    //Insertar parametro para el query
        parametros.put("RAZON_SOCIAL", new String(PARAMETROS.RAZON_SOCIAL));
        parametros.put("ACTIVIDAD", new String(PARAMETROS.ACTIVIDAD));
        parametros.put("PAGINA_WEB", new String(PARAMETROS.PAGINA_WEB));
        parametros.put("TELEFONO", new String(PARAMETROS.EMAIL));
        parametros.put("NOMBRE_CLIENTE", new String(nombre));
        parametros.put("LOCALIDAD_CLIENTE", new String(localidadCliente));
        parametros.put("DIRECCION_CLIENTE", new String(dirCliente));
        parametros.put("TELEFONO_CLIENTE", new String(telCliente));

        try {
            jasperReport = (JasperReport) JRLoader.loadObject(rutaJasper);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al Preparar el Reporte.",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return;
        }
    }

    private String folio;
    private String nombre;
    private String localidadCliente;
    private String dirCliente;
    private String telCliente;

    private java.io.InputStream rutaJasper;
    private Map parametros;
    private JasperPrint jasperPrint;
    private JasperReport jasperReport;

}
