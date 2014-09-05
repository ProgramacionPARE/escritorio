/*
 * Reporte.java
 *
 * Created on 5 de junio de 2007, 13:05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package proyectopare.clases.reportes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

public class Reporte {
    /** Creates a new instance of Reporte */
    public Reporte(String titulo, String folio, String nombreArcivoJasper) {
        this.titulo = titulo;
        this.param = folio;
        prepararReporte(nombreArcivoJasper);
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
                    jasperReport, parametros, getConnnection());
            JasperViewer jasperviewer = new JasperViewer(jasperPrint, false);
            jasperviewer.setTitle(titulo);
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
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error al crear el JRViewer ", javax.swing.JOptionPane.ERROR_MESSAGE);
            error = false;
        } finally {
            closeConnnection();
            return error;
        }
    }
    
    /**
     * Realiza una conexion con la Base de Datos y devuelve una nueva instancia
     *
     * @return una nueva instancia del objeto <Strong>java.sql.Connection</Strong>
     * 
     * @exception IllegalAccessException si la clase no es accesible
     * @exception ClassNotFoundException si no se puede acceder a la clase o al classpath
     * @exception InstantiationException si ocurre un error al instanciar alguna clase
     * @exception SQLException si ocurre un error al acceder a la Base de Datos
     */
    public static Connection getConnnection(){
        String controlador = "com.mysql.jdbc.Driver";
//        String URL_bd = "jdbc:mysql://192.168.1.64:3306/paredb";
//        String URL_bd = "jdbc:mysql://192.168.1.1:3306/paredb";
        String URL_bd = PARAMETROS.URL_BD;
//        String URL_bd = "jdbc:mysql://192.168.1.1:3306/paredb";

        String usuario = PARAMETROS.USUARIO_BD;
//          String contrasenia = "edldysjpl";
        String contrasenia = PARAMETROS.CONTRASENIA_BD;
        try {
            Class.forName(controlador).newInstance();
        } catch (InstantiationException ex) {
            System.out.println("Reporte...");
            ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                System.out.println("Reporte...");
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                System.out.println("Reporte...");
                ex.printStackTrace();
            }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL_bd, usuario, contrasenia);
        } catch (SQLException ex) {
            System.out.println("Reporte...");
            ex.printStackTrace();
        }
        return conn;
    }

    public static void closeConnnection(){
        try {
            getConnnection().close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    private void prepararReporte(String nomArchJasper) {
        String paramRuta = "/proyectopare/clases/reportes/archivosjasper/";
        rutaJasper = this.getClass().getResourceAsStream(paramRuta + nomArchJasper + ".jasper");
        parametros = new HashMap();
        parametros.put("PARAM_PROGRESIVO",new java.lang.String(param));    //Insertar parametro al reporte
        try {
            jasperReport = (JasperReport) JRLoader.loadObject(rutaJasper);
        }catch (JRException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error al Preparar el Reporte.",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    private String param;
    private String titulo;
    private java.io.InputStream rutaJasper;

    private Map parametros;
    private JasperPrint jasperPrint;
    private JasperReport jasperReport;
}
