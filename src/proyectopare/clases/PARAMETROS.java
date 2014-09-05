/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectopare.clases;

/**
 *
 * @author Roman
 */
public class PARAMETROS {

    private PARAMETROS() {
    }

    //DATOS DE LA EMPRESA
     public static String RAZON_SOCIAL = "PARE S.A";
    public static String ACTIVIDAD = "PARE (ESTACIONAMIENTOS)";
    public static String CENTRO_OPERATIVO = "Monte Pelvoux";
    public static String CENTRO_COSTOS = "Monte Pelvoux";
    public static String CASETA = "1";
    public static String DIRECCION = "MONTES URALES No. 751";
    public static String CODIGO_POSTAL = "11000";
    public static String PAIS = "MEXICO";
    public static String LOCALIDAD = "D.F.";
    public static String CODIGO = "11000";
    public static String PAGINA_WEB = "http://www.pare.com.mx/";
    public static String EMAIL = "xxxxxxxx, info@pare.com.mx";
    public static String SITUADO = "D.F.";
    public static String TELEFONO = "55400168";

    //PARAMETROS QUE DEBEN IR EN LUGAR DEL LOGIN (SE PUEDE REASIGNAR EL VALOR AL Login.XXX)
    public static int ID_CENTRO_OPERATIVO = 16;
    public static int ID_CENTRO_COSTOS = 36;
    public static int ID_CASETA = 1;
    public static int ID_SALIDA = 0;

//    public static java.io.InputStream LOGOTIPO = null;

    public static boolean OBLIGATORIO_PLACA_MATRICULA = false;
    public static boolean PUEDE_ANULAR_ENTRADAS = false;
    public static boolean PUEDE_MODIFICAR_ENTRADAS = false;
    public static boolean PUEDE_TRASLADAR_A_CAJA = false;
    public static boolean TARIFA_PAGO = false;
    public static boolean IMPRESION_AUTOMATICA_ENTRADAS = true;
    public static boolean IMPRESION_AUTOMATICA_SALIDAS = true;
    public static boolean PUEDE_MODIFICAR_FECHA_ENTRADA = false;
    public static boolean PUEDE_MODIFICAR_FECHA_SALIDA = false;
    public static boolean EJECUTAR_INICIO = false;
    public static int FOLIO_RANGO_INICIAL = -1;
    public static int FOLIO_RANGO_FINAL = -1;
    public static int DIAS_CADUCIDAD_CONTRAS = 30;
    public static int PORCENTAJE_IVA = 15;
    public static int TARIFA_POR_OMISION = 0;

    public static final int TAMANIO_FOLIO = 6;

    public static int DIAS_AVISO_VIGENCIA = 10;
    public static String EJERCICIO = "2009";
    public static int TOTAL_CAJONES = 0;
    public static String HORARIO_ATENCION_DESDE = "";
    public static String HORARIO_ATENCION_HASTA = "";


    public static String USUARIO_BD = "root";
    public static String CONTRASENIA_BD = "pare64";
    public static String PUERTO_BD = "3306";
    public static String URL_BD = "jdbc:mysql://127.0.0.1:3306/paredb";

    public static boolean BOLETO_VALET_PARKING = false;
}

