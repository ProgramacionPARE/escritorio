///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package proyectopare.clases;
//
//import java.sql.SQLException;
//import java.util.Vector;
//import modelos.Conexion;
//
///**
// *
// * @author Administrador
// */
//public class LoginUsuarios extends Conexion {
//
//    public LoginUsuarios() {
//        super();
//    }
//
//    public Vector<String[]> getUsuarios() {
//        Vector<String[]> vec = new Vector<String[]>();
//        String[] usu = null;
//        // by RUSP 09/MAYO/2009
//        // String query =
//        //        "SELECT id,id_centro_costos,id_centro_operaciones,id_caseta,nombre,contras,nivel FROM usuarios";
//        String query =
//               "SELECT id,id_centro_costos,id_centro_operaciones,id_caseta,nombre,contras,nivel,clave_usuario,permisos,nombre_completo FROM usuarios";
//
//        try {
//            java.sql.ResultSet rsUs = sentenciaSQL.executeQuery(query);
//            while(rsUs.next()) {
//                //usu = new String[7];  by RUSP 09/MAYO/2009
//                usu = new String[10];  // by RUSP 10/MAYO/2009
//                usu[0] = rsUs.getString(1);
//                usu[1] = rsUs.getString(2);
//                usu[2] = rsUs.getString(3);
//                usu[3] = rsUs.getString(4);
//                usu[4] = rsUs.getString(5);
//                usu[5] = rsUs.getString(6);
//                usu[6] = rsUs.getString(7);
//                usu[7] = rsUs.getString(8);   // by RUSP 09/MAYO/2009
//                usu[8] = rsUs.getString(9);   // by RUSP 10/MAYO/2009
//                usu[9] = rsUs.getString(10);
//                vec.add(usu);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            return vec;
//        }
//    }
//
//    public String[] getInformacionUsuario(int idUsuario) {
//        String[] usu = new String[3];
//        String query = "SELECT lst1.nombre,lst2.nombre,lst3.nombre FROM usuarios LEFT JOIN listas lst1 " +
//                "ON usuarios.id_centro_costos=lst1.elemento AND lst1.lista=1 LEFT JOIN listas lst2 " +
//                "ON usuarios.id_centro_operaciones=lst2.elemento AND lst2.lista=2 LEFT JOIN listas lst3 " +
//                "ON usuarios.id_caseta=lst3.elemento AND lst3.lista=3 WHERE usuarios.id='" + idUsuario + "'";
//        try {
//            java.sql.ResultSet rsUs = sentenciaSQL.executeQuery(query);
//            rsUs.first();
//            usu[0] = rsUs.getString(2);
//            usu[1] = rsUs.getString(1);
//            usu[2] = rsUs.getString(3);
//            return usu;
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }
//
//}
//
