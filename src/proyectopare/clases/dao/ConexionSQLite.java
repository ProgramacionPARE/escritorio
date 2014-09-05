/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectopare.clases.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import proyectopare.clases.PARAMETROS;

/**
 *
 * @author Roman
 */

public class ConexionSQLite {
    static final String controladorJDBC = "org.sqlite.JDBC";
    static final String baseDatos       = "jdbc:sqlite:CONFIGURACIONES.sqlite";
    private Connection conexion;
    private Statement stmt;
    private ResultSet rs;

    public ConexionSQLite(){
        getConnectionSQLite();
    }

    public boolean actualizaDatosConexionMySQL(String URL_bd, String usuario, String contrasenia) {
        String query = "UPDATE conexion_mysql SET usuario='" + usuario + "',contrasenia='" + contrasenia + "',url_bd='" + URL_bd + "'";
        try {
            stmt.executeUpdate(query);
            return true;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizaParametro(String nombre, String valor) {
        String query = "UPDATE parametros SET " + nombre + "='" + valor + "'";
        try {
            stmt.executeUpdate(query);
            System.out.println(query + "...SQLite...ok");
            return true;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizaParametrosCalculos(String[] dts) {
        String query = "UPDATE parametros SET total_cajones='" + dts[0] + "',horario_desde='" + dts[1] + "',horario_hasta='"
                + dts[2] + "'";
        try {
            stmt.executeUpdate(query);
            System.out.println(query + "...SQLite...ok");
            return true;
        }
        catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizaParametrosEmpresa(String []dts) {
        String query = "UPDATE parametros SET razon_social='" + dts[0] + "',actividad='" + dts[1] + "',id_centro_operativo='" + dts[2] +
                "',id_centro_costos='" + dts[3] + "',id_caseta='" + dts[4] + "',direccion='" + dts[5] + "',codigo_postal='" + dts[6] +
                "',pais='" + dts[7] + "',localidad='" + dts[8] + "',codigo='" + dts[9] + "',pagina_web='" + dts[10] +
                "',email='" + dts[11] + "',situado='" + dts[12] + "',telefono='" + dts[13] + "',centro_operativo='" + dts[14] +
                "',centro_costos='" + dts[15] + "'";
        try {
            stmt.executeUpdate(query);
            System.out.println(query + "...SQLite...ok");
            return true;
        }
        catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizaRangoFolios(String rangoInicial, String rangoFinal) {
        String query = "UPDATE parametros SET folio_rango_inicial='" + rangoInicial +
                "',folio_rango_final='" + rangoFinal + "'";
        try {
            stmt.executeUpdate(query);
            System.out.println(query + "...SQLite...ok");
            return true;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public /*Connection*/void getConnectionSQLite() {
        try {
            System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");

            Class.forName(controladorJDBC);
            conexion = DriverManager.getConnection(baseDatos);
            stmt = conexion.createStatement();
            System.out.println("Conexion SQLite realizada con exito...");

//            String query = "SELECT razon_social,actividad,id_centro_operativo,id_centro_costos,id_caseta,direccion,codigo_postal,pais," +
//                    "localidad,codigo,pagina_web,email,situado,telefono,obligatorio_placa,puede_anular,puede_modificar,puede_trasladar," +
//                    "tarifa_pago,impresion_auto_entradas,impresion_auto_salidas,puede_mod_fecha_entrada,puede_mod_fecha_salida," +
//                    "ejecutar_al_inicio,folio_rango_inicial,folio_rango_final,dias_caducidad_contras,dias_aviso_caduc_contra," +
//                    "tarifa_por_omision,centro_operativo,centro_costos,total_cajones,horario_desde AS HD,horario_hasta AS HH FROM parametros;";
//            rs = stmt.executeQuery(query);
//            StringBuffer res = new StringBuffer();
//            ResultSetMetaData metaDatos = rs.getMetaData();
//
//            int numeroColumnas = metaDatos.getColumnCount();
//            for(int i = 1; i <= numeroColumnas; i++)
//                res.append(metaDatos.getColumnName(i) + "\t");
//                res.append("\n");
//
//            while(rs.next()){
//                for(int i = 1; i <= numeroColumnas; i++)
//                res.append(rs.getObject(i) + "\t");
//                res.append("\n");
//            }
//            System.out.println(res.toString());
//            System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");
//            rs = stmt.executeQuery("SELECT * FROM conexion_mysql");
//            rs.next();
//            System.out.println(rs.getString(1));
//            System.out.println(rs.getString(2));
//            System.out.println(rs.getString(3));
//            System.out.println(rs.getString(4));
//            System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");

            } catch(SQLException excepcionSql) {
                excepcionSql.printStackTrace();
                JOptionPane.showMessageDialog(null, excepcionSql.getMessage(),
                "Error en la base de datos SQLite", JOptionPane.ERROR_MESSAGE);
            } catch(ClassNotFoundException claseNoEncontrada){
                claseNoEncontrada.printStackTrace();
                JOptionPane.showMessageDialog(null, claseNoEncontrada.getMessage(),
                "No se encontro el controlador SQLite", JOptionPane.ERROR_MESSAGE);
            }
//        finally {
//                try {
//                    stmt.close();
//                    rs.close();
//                    conexion.close();
//                } catch(SQLException excepcionSQL){
//                        JOptionPane.showMessageDialog(null, excepcionSQL.getMessage(),
//                        "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
//                        System.exit(1);
//                }
//            }
    } // ConexionSQLite

    /**
     * Cierra la Conexion con la Base de Datos
     *
     * @exception SQLException si ocurre un error al accesar a la Base se Datos
     */
    public void cerrarConexionSQLite() {
        try {
            stmt.close();
            if (rs != null) rs.close();
            conexion.close();
            System.out.println("Conexion SQLite cerrada con exito...");
            System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");
        }
        catch(SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error de acceso a la Base de Datos.",JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ConexionSQLite c = new ConexionSQLite();
                c.setParametrosGenerales();
                c.cerrarConexionSQLite();
            }
        });
    }

    public boolean setParametrosGenerales() {
        String query = "SELECT razon_social,actividad,id_centro_operativo,id_centro_costos,id_caseta,direccion,codigo_postal,pais," +
                    "localidad,codigo,pagina_web,email,situado,telefono,obligatorio_placa,puede_anular,puede_modificar,puede_trasladar," +
                    "tarifa_pago,impresion_auto_entradas,impresion_auto_salidas,puede_mod_fecha_entrada,puede_mod_fecha_salida," +
                    "ejecutar_al_inicio,folio_rango_inicial,folio_rango_final,dias_caducidad_contras,dias_aviso_caduc_contra," +
                    "tarifa_por_omision,centro_operativo,centro_costos,total_cajones,horario_desde,horario_hasta,tipo_boleto FROM parametros;";
        try {
            ResultSet rsParam = stmt.executeQuery(query);
            rsParam.next();
            PARAMETROS.RAZON_SOCIAL = rsParam.getString("razon_social");
            PARAMETROS.ACTIVIDAD = rsParam.getString("actividad");
            PARAMETROS.ID_CENTRO_OPERATIVO = rsParam.getInt("id_centro_operativo");
            PARAMETROS.ID_CENTRO_COSTOS = rsParam.getInt("id_centro_costos");
            PARAMETROS.ID_CASETA = rsParam.getInt("id_caseta");
            PARAMETROS.DIRECCION = rsParam.getString("direccion");
            PARAMETROS.CODIGO_POSTAL = rsParam.getString("codigo_postal");
            PARAMETROS.PAIS = rsParam.getString("pais");
            PARAMETROS.LOCALIDAD = rsParam.getString("localidad");
            PARAMETROS.CODIGO = rsParam.getString("codigo");
            PARAMETROS.PAGINA_WEB = rsParam.getString("pagina_web");
            PARAMETROS.EMAIL = rsParam.getString("email");
            PARAMETROS.SITUADO = rsParam.getString("situado");
            PARAMETROS.TELEFONO = rsParam.getString("telefono");
            PARAMETROS.OBLIGATORIO_PLACA_MATRICULA = rsParam.getString("obligatorio_placa").equals("S");
            PARAMETROS.PUEDE_ANULAR_ENTRADAS = rsParam.getString("puede_anular").equals("S");
            PARAMETROS.PUEDE_MODIFICAR_ENTRADAS = rsParam.getString("puede_modificar").equals("S");
            PARAMETROS.PUEDE_TRASLADAR_A_CAJA = rsParam.getString("puede_trasladar").equals("S");
            //PARAMETROS.TARIFA_PAGO = false;
            PARAMETROS.IMPRESION_AUTOMATICA_ENTRADAS = rsParam.getString("impresion_auto_entradas").equals("S");
            PARAMETROS.IMPRESION_AUTOMATICA_SALIDAS = rsParam.getString("impresion_auto_salidas").equals("S");
            PARAMETROS.PUEDE_MODIFICAR_FECHA_ENTRADA = rsParam.getString("puede_mod_fecha_entrada").equals("S");
            PARAMETROS.PUEDE_MODIFICAR_FECHA_SALIDA = rsParam.getString("puede_mod_fecha_salida").equals("S");
            PARAMETROS.EJECUTAR_INICIO = rsParam.getString("ejecutar_al_inicio").equals("S");

            PARAMETROS.FOLIO_RANGO_INICIAL = rsParam.getInt("folio_rango_inicial");
            PARAMETROS.FOLIO_RANGO_FINAL = rsParam.getInt("folio_rango_final");
            PARAMETROS.DIAS_CADUCIDAD_CONTRAS = rsParam.getInt("dias_caducidad_contras");
            PARAMETROS.DIAS_AVISO_VIGENCIA = rsParam.getInt("dias_aviso_caduc_contra");
            PARAMETROS.TARIFA_POR_OMISION = rsParam.getInt("tarifa_por_omision");
            PARAMETROS.CENTRO_OPERATIVO = rsParam.getString("centro_operativo");
            PARAMETROS.CENTRO_COSTOS = rsParam.getString("centro_costos");
            PARAMETROS.TOTAL_CAJONES = rsParam.getInt("total_cajones");
            PARAMETROS.HORARIO_ATENCION_DESDE = rsParam.getString("horario_desde");
            PARAMETROS.HORARIO_ATENCION_HASTA = rsParam.getString("horario_hasta");
            PARAMETROS.BOLETO_VALET_PARKING = rsParam.getString("tipo_boleto").equals("1");

            System.out.println("******************************************************************************************************");

            query = "SELECT usuario,contrasenia,url_bd FROM conexion_mysql";
            ResultSet db = stmt.executeQuery(query);
            db.next();
            PARAMETROS.USUARIO_BD = db.getString(1);
            PARAMETROS.CONTRASENIA_BD = db.getString(2);
            PARAMETROS.URL_BD = db.getString(3);
            System.out.println(PARAMETROS.USUARIO_BD);
            System.out.println(PARAMETROS.CONTRASENIA_BD);
            System.out.println(PARAMETROS.URL_BD);

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
