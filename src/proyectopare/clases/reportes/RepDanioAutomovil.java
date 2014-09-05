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

public class RepDanioAutomovil {

    /** Creates a new instance of Reporte */
    public RepDanioAutomovil(String der,String izq,String ade,String atr,int progresivo) {
        this.adelante = new String(ade);
        this.atras = new String(atr);
        this.izquierda = new String(izq);
        this.derecha = new String(der);
        this.progresivo = String.valueOf(progresivo);

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
                    jasperReport, parametros,Reporte.getConnnection());
            JasperViewer jasperviewer = new JasperViewer(jasperPrint, false);
            jasperviewer.setTitle("Reporte de Daños Automovil");
            jasperviewer.setExtendedState(0);
            jasperviewer.setFocusable(true);
            jasperviewer.setFocusableWindowState(true);
            jasperviewer.setVisible(true);
//            jasperviewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
        } catch (JRException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error al generar la vista previa:\n" + ex.getMessage());
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
        String paramRuta = "/proyectopare/clases/reportes/archivosjasper/reporteDanioAutomovil.jasper";
        rutaJasper = getClass().getResourceAsStream(paramRuta);
        parametros = new HashMap();
//        parametros.put("PARAM_FOLIO",new java.lang.Long(param));    //Insertar parametro al reporte
        parametros.put("PAR_ADE",new java.lang.String(adelante));
        parametros.put("PAR_ATR",new java.lang.String(atras));
        parametros.put("PAR_IZQ",new java.lang.String(izquierda));
        parametros.put("PAR_DER",new java.lang.String(derecha));
        parametros.put("PAR_PROGRESIVO",new java.lang.String(progresivo));
        try {
            jasperReport = (JasperReport) JRLoader.loadObject(rutaJasper);
        }catch (JRException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error al Preparar el Reporte.",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    private java.io.InputStream rutaJasper;

    private Map parametros;
    private JasperPrint jasperPrint;
    private JasperReport jasperReport;

    private String progresivo,adelante,atras,izquierda,derecha;
}
