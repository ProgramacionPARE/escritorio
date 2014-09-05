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
import java.util.Vector;
import javax.swing.JOptionPane;
import proyectopare.clases.PARAMETROS;

/**
 *
 * @author Roman
 */

public class ConexionOld {
    private Statement sentenciaSQL2;

    public ConexionOld(){
        getConnectionDB();
    }

    public Connection getConnectionDB() {
        try {
            String controlador = "com.mysql.jdbc.Driver";
            try {
                Class.forName(controlador).newInstance();
            } catch (InstantiationException ex) {
                ex.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Error de instanciacion.\n" + ex.getMessage(),"Error de acceso a la Base de Datos.",
//                JOptionPane.ERROR_MESSAGE);
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
//                JOptionPane.showMessageDialog(null, "Acceso ilegal a la Base de Datos.\n" + ex.getMessage(),"Error de acceso a la Base de Datos.",
//                    JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "No se puede estableser una Conexion con la Base de Datos:\nVerifique que se encuentren todas las librerias necesarias.",
                        "Error de acceso a la Base de Datos.",javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            //Direccion de la BD=LOCALHOST  PUERTO=3306
//            String URL_bd = "jdbc:mysql://192.168.1.1:3306/paredb";
//            String URL_bd = "jdbc:mysql://192.168.1.64:3306/paredb";
//            String URL_bd = "jdbc:mysql://192.168.1.1:3306/paredb";
//            String usuario = "root";
            String usuario = PARAMETROS.USUARIO_BD;
//            String contrasenia = "edldysjpl";
//            String contrasenia = "romanos";
            String contrasenia = PARAMETROS.CONTRASENIA_BD;
            conexion = DriverManager.getConnection(PARAMETROS.URL_BD,usuario,contrasenia);
            sentenciaSQL2 = conexion.createStatement();
            sentenciaSQL = conexion.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error de acceso a la Base de Datos.",JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } finally {
            return conexion;
        }
    } // Conexion

    public Vector<String> getVectorTurnos() {
        java.sql.ResultSet aux = null;
        Vector<String> listaTurnos = new Vector<String>();
        String query = "SELECT descripcion FROM turnos";
        try{
            aux = sentenciaSQL.executeQuery(query);
            while(aux.next()) {
                listaTurnos.add(aux.getString(1));
            }
            return listaTurnos;
        }
        catch(java.sql.SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error de acceso a la Base de Datos.",JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    /**
     * Genera un Vector de cadenas con los datos de las listas correspondientes al
     * numero de lista
     * @param numLista numero de lista
     * @return Vector con la infomacion solicitada
     */
    public Vector<String> getVectorCombo(String numLista) {
        java.sql.ResultSet aux = null;
        Vector<String> listaProv = new Vector<String>();
        String query = "SELECT elemento,nombre FROM listas WHERE lista='" + numLista +"' AND elemento>0";
        try{
            aux = sentenciaSQL.executeQuery(query);
            while(aux.next()){
                listaProv.add(aux.getInt(1) + "      " + aux.getString(2));
            }
            return listaProv;
        }
        catch(java.sql.SQLException e){
            return null;
        }
    }

    /**
     * Da de alta un nuevo registro sobre la tabla especificada por el argumento.
     * @param datos     Contiene la informaacion de los campos y los valores que deberá contener el nuevo registro
     * @param tabla     Especifica el nombre exacto de la tabla contenida en la Base de Datos.
     * @return          Devuelve <strong> true </strong> si el registro fue dado de alta correctamente <strong> false </strong> en caso contrario.
     */
    public boolean altaRegistroGenerico(String[] datos, String tabla) {
        boolean resultado = false;
        String query = "INSERT INTO " + tabla + " VALUES ('";
        for (String elem : datos) {
            query += elem + "','";
        }
        query = query.substring(0, query.length()-2) + ")";
        //javax.swing.JOptionPane.showMessageDialog(null, query);

        try {
            sentenciaSQL.executeUpdate(query);
            resultado = true;
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Verifique que los datos esten escritos correctamente:\n\n" + e.getMessage(),
                    "Error de acceso a la Base de Datos.",JOptionPane.ERROR_MESSAGE);
            resultado = false;
        } finally {
            return resultado;
        }
    }

    /**
     * Actualiza un registro contenido en la tabla especificada por el argumento.
     * @param vector    Contiene tanto el nombre como el valor de cada uno de los campos de la tabla.
     * @param tabla     Tabla contenida en la Base de Datos.
     * @return          Devuelve <strong> true </strong> si el registro se actualizo correctamente  y <strong> false </strong> en caso contrario.
     */
    public boolean actualizarRegistroGenerico(Vector<String[]> vecDatos, String tabla) {
        boolean res = false;
        String query = "UPDATE " + tabla + " SET ";
        for (int i = 1; i < vecDatos.size(); i++) {
            String[] registro = vecDatos.elementAt(i);
            query += registro[0] + "='" + registro[1] + "',";
        }
        String[] id = vecDatos.get(0);
        query = query.substring(0, query.length()-1) + " WHERE " + id[0] + " = '" + id[1] + "'";
//        javax.swing.JOptionPane.showMessageDialog(null, query);
        
        try {
            
            sentenciaSQL.executeUpdate(query);
            res = true;
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error de acceso a la Base de Datos.",JOptionPane.ERROR_MESSAGE);
            res = false;
        } finally {
            return res;
        }
    }

    /**
     * Cierra la Conexion con la Base de Datos
     *
     * @exception SQLException si ocurre un error al accesar a la Base se Datos
     */
    public void cerrarConexion() {
        try{
            if(rs != null) rs.close();
            if(sentenciaSQL != null) sentenciaSQL.close();
            if(sentenciaSQL2 != null) sentenciaSQL2.close();
            if(conexion != null) conexion.close();
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error de acceso a la Base de Datos.",JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Carga la informacion de la Base de Datos, contenida en la tabla especificada por el parametro
     *
     * @exception SQLException si ocurre un error al accesar a la Base se Datos
     *
     * @param   tabla es el nombre de tabla contenida en la Base de Datos
     */
    public void obtenerTabla(String tabla){
        try {
            rs = sentenciaSQL.executeQuery("SELECT * FROM " + tabla);
        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error al intentar leer la tabla:\n" +
                    tabla,"Error en la Base de Datos.", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean setParametrosGenerales() {
//        String query = "SELECT razon_social,actividad,id_centro_operativo,id_centro_costos,id_caseta,direccion," +
//                    "codigo_postal,pais,localidad,codigo,pagina_web,email,situado,telefono,obligatorio_placa," +
//                    "puede_anular,puede_modificar,puede_trasladar,tarifa_pago,impresion_auto_entradas," +
//                    "impresion_auto_salidas,puede_mod_fecha_entrada,puede_mod_fecha_salida,ejecutar_al_inicio," +
//                    "folio_rango_inicial,folio_rango_final,dias_caducidad_contras,dias_aviso_caduc_contra,tarifa_por_omision,centro_operativo,centro_costos," +
//                    "total_cajones,horario_desde AS HD,horario_hasta AS HH FROM parametros";
        try {
//            ResultSet rsParam = sentenciaSQL2.executeQuery(query);
//            rsParam.first();
//            PARAMETROS.RAZON_SOCIAL = rsParam.getString("razon_social");
//            PARAMETROS.ACTIVIDAD = rsParam.getString("actividad");
//            PARAMETROS.ID_CENTRO_OPERATIVO = rsParam.getInt("id_centro_operativo");
//            PARAMETROS.ID_CENTRO_COSTOS = rsParam.getInt("id_centro_costos");
//            PARAMETROS.ID_CASETA = rsParam.getInt("id_caseta");
//            PARAMETROS.DIRECCION = rsParam.getString("direccion");
//            PARAMETROS.CODIGO_POSTAL = rsParam.getString("codigo_postal");
//            PARAMETROS.PAIS = rsParam.getString("pais");
//            PARAMETROS.LOCALIDAD = rsParam.getString("localidad");
//            PARAMETROS.CODIGO = rsParam.getString("codigo");
//            PARAMETROS.PAGINA_WEB = rsParam.getString("pagina_web");
//            PARAMETROS.EMAIL = rsParam.getString("email");
//            PARAMETROS.SITUADO = rsParam.getString("situado");
//            PARAMETROS.TELEFONO = rsParam.getString("telefono");
//            PARAMETROS.OBLIGATORIO_PLACA_MATRICULA = rsParam.getString("obligatorio_placa").equals("S");
//            PARAMETROS.PUEDE_ANULAR_ENTRADAS = rsParam.getString("puede_anular").equals("S");
//            PARAMETROS.PUEDE_MODIFICAR_ENTRADAS = rsParam.getString("puede_modificar").equals("S");
//            PARAMETROS.PUEDE_TRASLADAR_A_CAJA = rsParam.getString("puede_trasladar").equals("S");
//            //PARAMETROS.TARIFA_PAGO = false;
//            PARAMETROS.IMPRESION_AUTOMATICA_ENTRADAS = rsParam.getString("impresion_auto_entradas").equals("S");
//            PARAMETROS.IMPRESION_AUTOMATICA_SALIDAS = rsParam.getString("impresion_auto_salidas").equals("S");
//            PARAMETROS.PUEDE_MODIFICAR_FECHA_ENTRADA = rsParam.getString("puede_mod_fecha_entrada").equals("S");
//            PARAMETROS.PUEDE_MODIFICAR_FECHA_SALIDA = rsParam.getString("puede_mod_fecha_salida").equals("S");
//            PARAMETROS.EJECUTAR_INICIO = rsParam.getString("ejecutar_al_inicio").equals("S");
//
//            PARAMETROS.FOLIO_RANGO_INICIAL = rsParam.getInt("folio_rango_inicial");
//            PARAMETROS.FOLIO_RANGO_FINAL = rsParam.getInt("folio_rango_final");
//            PARAMETROS.DIAS_CADUCIDAD_CONTRAS = rsParam.getInt("dias_caducidad_contras");
//            PARAMETROS.DIAS_AVISO_VIGENCIA = rsParam.getInt("dias_aviso_caduc_contra");
//            PARAMETROS.TARIFA_POR_OMISION = rsParam.getInt("tarifa_por_omision");
//            PARAMETROS.CENTRO_OPERATIVO = rsParam.getString("centro_operativo");
//            PARAMETROS.CENTRO_COSTOS = rsParam.getString("centro_costos");
//            PARAMETROS.TOTAL_CAJONES = rsParam.getInt("total_cajones");
//            PARAMETROS.HORARIO_ATENCION_DESDE = rsParam.getString("HD");
//            PARAMETROS.HORARIO_ATENCION_HASTA = rsParam.getString("HH");

            String query = "SELECT ejercicio FROM folios_formatos WHERE id_centro_costos='" + PARAMETROS.ID_CENTRO_COSTOS + "' AND id_caseta='" +
                    PARAMETROS.ID_CASETA + "' AND id_formato='18'";
            ResultSet ej = sentenciaSQL.executeQuery(query);
            ej.first();
            //PARAMETROS.EJERCICIO = ej.getString(1);

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     *  Muestra los datos del registro actualmente seleccionado, por medio de un Arreglo de cadenas
     *
     * @return <strong>String[]</strong> que contiene los datos de un registro
     *
     * @exception SQLException  si ocurre un error al accesar a la Base se Datos, o si
     *                         tabla esta actualmente en <strong>null</strong>
     */
    public String[] mostrarFilaActual(){
        String[] datos = null;
        try{
            int nColumnas = rs.getMetaData().getColumnCount();
            datos = new String[nColumnas];
            for (int i = 1,x=0; i <= nColumnas; ++i,x++)
                datos[x] = rs.getString(i);
            return datos;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    //Funciones para desplazarse por los registros

    /**
     * Mueve al Primer registro de la tabla contenida en la Base de Datos, actualmente cargada en memoria
     *
     * @exception SQLException si ocurre un error al accesar a la Base se Datos, o si
     *                         tabla esta actualmente en null
     */
    public void primero(){
    	try{
            rs.first();
    	} catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Mueve al Ultimo registro de la tabla contenida en la Base de Datos, actualmente cargada en memoria
     *
     * @exception SQLException si ocurre un error al accesar a la Base se Datos, o si
     *                         tabla esta actualmente en null
     */
    public void ultimo() {
        try{
            rs.last();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Mueve al Siguiente registro de la tabla (actualmente cargada en memoria), contenida en la Base de Datos
     *
     ** @return <strong>true</strong> si el registro que selecciono es el Ultimo, <strong>false</strong> en caso
     *          contrario o que ocurra algun error.
     *
     * @exception SQLException si ocurre un error al accesar a la Base se Datos, o si
     *                         tabla esta actualmente en null
     */
    public boolean siguiente(){
        try{
            if(rs.isLast())
                return true;
            rs.next();
            return false;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Mueve al registro Anterior de la tabla (actualmente cargada en memoria), contenida en la Base de Datos
     *
     ** @return <strong>true</strong> si el registro que selecciono es el Primero, <strong>false</strong> en caso
     *          contrario o que ocurra algun error.
     *
     * @exception SQLException si ocurre un error al accesar a la Base se Datos, o si
     *                         tabla esta actualmente en null
     */
    public boolean anterior(){
        try{
            if(rs.isFirst())
                return true;
            rs.previous();
            return false;
        } catch(SQLException ex) {
            return false;
        }
    }

    /**
     * Regresa el numero de registro en el que se encuentra el ResultSet rs, y cero
     * si no tiene registros u ocurre al gun error
     * @return el numero de registro
     */
    public int getFilaActual() {
        try {
            return rs.getRow();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    /**
     * Elimina un registro de la Base de Datos, especificado por los parametros dato, tabla y where
     *
     * @param   dato        Es el identificador del registro que se desea borrar de la Base de Datos
     * @param   tabla       Nombre de la tabla contenida en la Base de Datos
     * @param   where       Condicion que debe cumplir el identificador del registro
     *
     * @return <strong>true</strong> si el registro fue eliminado correctamente de la Base de Datos,
     *         <strong>false</strong> en caso contrario
     *
     * @exception SQLException  si ocurre un error al accesar a la Base se Datos
     */
    public boolean eliminarGenerico(String dato, String tabla, String where){
        try{
            sentenciaSQL.executeUpdate("DELETE FROM " + tabla +
                    " WHERE " + where + " like '" +  dato + "'");
            return true;
        } catch(SQLException ex) {
            return false;
        }
    }

    public int getProgresivoFormato(String idCC,String idCaseta,String numeroFormatoEnListas) {
        String query = "SELECT folio FROM folios_formatos WHERE id_centro_costos='" + idCC + "' AND id_caseta='" + idCaseta +
                "' AND id_formato='" + numeroFormatoEnListas + "'";
        try{
            ResultSet auxFolio = sentenciaSQL2.executeQuery(query);
            if (auxFolio.first()) return auxFolio.getInt(1);
            return -1;
        }
        catch(SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public boolean actualizaProgresivoFormato(String idCC,String idCaseta,String ultimoFolio,String numeroFormatoEnListas) {
        String query = "UPDATE folios_formatos SET folio='" + ultimoFolio + "' WHERE id_centro_costos='" + idCC +
                "' AND id_caseta='" + idCaseta + "' AND id_formato='" + numeroFormatoEnListas + "'";
        //javax.swing.JOptionPane.showMessageDialog(null,query);
        try {
            sentenciaSQL2.executeUpdate(query);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private java.sql.Connection conexion;
    protected java.sql.Statement sentenciaSQL;
    protected ResultSet rs;
    
    /** Constante que indica que nos vamos a mover al Primer registro cargado actualmente en memoria*/
    public static final int PRIMER_REGISTRO = 1;
    /** Constante que indica que nos vamos a mover al registro Anterior cargado actualmente en memoria*/
    public static final int REGISTRO_ANTERIOR = 2;
    /** Constante que indica que nos vamos a mover al Siguiente registro cargado actualmente en memoria*/
    public static final int SIGUIENTE_REGISTRO = 3;
    /** Constante que indica que nos vamos a mover al Ultimo registro cargado actualmente en memoria*/
    public static final int ULTIMO_REGISTRO = 4;
}
