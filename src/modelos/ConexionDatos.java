
package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;



public  class ConexionDatos {
    private static ConexionDatos CONEXION_DATOS = new ConexionDatos();
    private  Connection connection ;
    private ConexionDatos(){
       

    }

    public static ConexionDatos getInstance(){
        return CONEXION_DATOS;
    }
    
   
    
    public Connection getConnection() {
         try {
            Class.forName("com.mysql.jdbc.Driver");
            String servidor = "jdbc:mysql://localhost/paredatos";
            String usuarioDB="root";
            String passwordDB="#parePROGRAMACIONdb";
            this.connection = DriverManager.getConnection(servidor,usuarioDB,passwordDB);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConexionDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
    
     public Connection getConnection(String ip) {
         try {
            Class.forName("com.mysql.jdbc.Driver");
            String servidor = "jdbc:mysql://"+ip+"/paredatos";
            String usuarioDB="root";
            String passwordDB="#parePROGRAMACIONdb";
            this.connection = DriverManager.getConnection(servidor,usuarioDB,passwordDB);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConexionDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    void cerrarConexion() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void checkDB() {
        try{
             
            ArrayList<String> databases = new ArrayList(); 
            ArrayList<String> db = new ArrayList(); 
            db.add("paredatos"); db.add("paredb");
            
            Class.forName("com.mysql.jdbc.Driver");
            String servidor = "jdbc:mysql://localhost/";
            String usuarioDB="root";
            String passwordDB="#parePROGRAMACIONdb";
            Connection conn= DriverManager.getConnection(servidor,usuarioDB,passwordDB);
            ResultSet catalogs = conn.getMetaData().getCatalogs();
            while( catalogs.next())
                databases.add(catalogs.getString(1));
            
            Iterator<String> dbIterator = db.iterator();
            while(dbIterator.hasNext()){
                String dbNext = dbIterator.next();
                Iterator<String> databasesIterator = databases.iterator();
                boolean find = false;
                while(databasesIterator.hasNext()){
                    if(dbNext.equals(databasesIterator.next())) find = true ;
                }
                if(!find){
                    Statement createStatement = conn.createStatement(); //"CREATE SCHEMA ? ");
                    createStatement.execute("CREATE SCHEMA "+dbNext+" ");
                }
            }
            checkTables();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConexionDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
     public void checkTables() {
        try {
           
            Class.forName("com.mysql.jdbc.Driver");
            String servidor = "jdbc:mysql://localhost/paredatos";
            String usuarioDB="root";
            String passwordDB="#parePROGRAMACIONdb";
            Connection conn = DriverManager.getConnection(servidor,usuarioDB,passwordDB);
            //////////////////////////////////////////////////////////////////////////////////////////////////
            //                                  Cargo la base PAREDATOS                                     //
            //////////////////////////////////////////////////////////////////////////////////////////////////
            Statement createStatement = conn.createStatement();
            createStatement.execute(
                    "CREATE TABLE IF NOT EXISTS `configuracion` ( `id` int(11) NOT NULL AUTO_INCREMENT," +
                    "  `ip` varchar(45) DEFAULT 'localhost', `terminal` varchar(10) DEFAULT 'caja'," +
                    "  `url` varchar(45) DEFAULT 'http://localhost:9000/'," +
                    "  PRIMARY KEY (`id`)) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;");
//           createStatement.execute("INSERT INTO `configuracion` VALUES (1,'127.0.0.1','caja','http://pare.herokuapp.com/')");
            
            createStatement.execute(
                    "CREATE TABLE IF NOT EXISTS `estacionamientos` ( `id` int(11) NOT NULL AUTO_INCREMENT," +
                    "  `nombre` varchar(45) DEFAULT NULL, `ip` varchar(45) DEFAULT NULL," +
                    "  PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
           
//            createStatement.execute("INSERT INTO `estacionamientos` VALUES (2,'Best buy','25.1.230.133'),"+
//            "(3,'Inah a','25.124.237.52'),(4,'Inah b','25.115.53.226'),(5,'Magdalena','25.151.249.201'),"+
//            "(6,'Pelvoux','25.191.112.165'),(7,'Rio Bamba','25.138.41.145')");
            
            //////////////////////////////////////////////////////////////////////////////////////////////////
            //                                  Cargo la base PAREDB                                        //
            //////////////////////////////////////////////////////////////////////////////////////////////////
            Class.forName("com.mysql.jdbc.Driver");
            servidor = "jdbc:mysql://localhost/paredb";
            conn = DriverManager.getConnection(servidor,usuarioDB,passwordDB);
            createStatement = conn.createStatement();
            
            createStatement.execute("CREATE TABLE IF NOT EXISTS `autos` (`id` int(6) unsigned zerofill NOT NULL AUTO_INCREMENT, `matricula` varchar(10) DEFAULT NULL,"
                    +"`progresivo` char(6) DEFAULT NULL, `entrada_salida` char(1) DEFAULT 'E', `fecha_entrada` varchar(10) DEFAULT NULL,"
                    +"`fecha_salida` varchar(10) DEFAULT NULL,`hora_entrada` varchar(5) DEFAULT NULL,`hora_salida` varchar(5) DEFAULT NULL,"
                    +"`horas_estadia` int(3) DEFAULT NULL,`minutos_estadia` int(3) DEFAULT NULL, `monto` double DEFAULT '0',`turno_entrada_id` int(11) DEFAULT NULL,"
                    +"`turno_salida_id` int(11) DEFAULT NULL, `id_tarifa` int(6) unsigned zerofill DEFAULT NULL, `id_caseta` int(3) DEFAULT NULL,"
                    +"`operador_entrada` int(10) DEFAULT NULL, `operador_salida` int(10) DEFAULT '0', `id_boleto_perdido` int(11) DEFAULT '0',"
                    +"`id_boleto_cancelado` int(11) DEFAULT '0', `id_boleto_contra` int(11) DEFAULT '0', `id_boleto_manual` int(11) DEFAULT '0',"
                    +" `boleto_perdido` varchar(4) DEFAULT 'NO', `boleto_cancelado` varchar(4) DEFAULT 'NO', `boleto_contra` varchar(4) DEFAULT 'NO',"
                    +"`boleto_manual` varchar(4) DEFAULT 'NO',  `boleto_pendiente` varchar(4) DEFAULT 'NO',  `recibo` varchar(5) DEFAULT 'NO',"
                    +"`marca` varchar(45) DEFAULT NULL, `modelo` varchar(45) DEFAULT NULL, `color` varchar(45) DEFAULT NULL, `clave` char(6) DEFAULT NULL,"
                    +" `descuento` decimal(10,0) DEFAULT '0',`serie` char(2) DEFAULT '0',`notas` varchar(80) DEFAULT NULL, `id_cliente` int(5) DEFAULT NULL,"
                    +" `estado_servidor` varchar(4) DEFAULT '0', `id_remoto` varchar(45) DEFAULT NULL, `boleto_oficina` varchar(4) CHARACTER SET dec8 DEFAULT 'NO',"
                    +"  PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=latin1;");
             
            createStatement.execute("CREATE TABLE IF NOT EXISTS `boleto_cancelado` (" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT, `id_turno` int(11) DEFAULT NULL," +
                    "  `id_auto` int(11) DEFAULT NULL,  `razon` varchar(500) DEFAULT NULL," +
                    "  PRIMARY KEY (`id`) ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;");
            
            createStatement.execute("CREATE TABLE IF NOT EXISTS `boleto_manual` (" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT, `fecha_entrada` varchar(10) DEFAULT NULL," +
                    "  `fecha_salida` varchar(10) DEFAULT NULL, `hora_entrada` varchar(10) DEFAULT NULL," +
                    "  `hora_salida` varchar(10) DEFAULT NULL,  `razon` varchar(45) DEFAULT NULL," +
                    "  `id_auto` int(11) DEFAULT NULL,  PRIMARY KEY (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=latin1;");
            createStatement.execute("CREATE TABLE IF NOT EXISTS `boleto_pendiente` (" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,  `id_auto` int(11) DEFAULT '0', `id_turno_pendiente` int(11) DEFAULT '0'," +
                    "  `serie` varchar(4) DEFAULT '0',  PRIMARY KEY (`id`)" +
                                    ") ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;");
            createStatement.execute("CREATE TABLE IF NOT EXISTS `caja` (" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT, `monto` decimal(10,0) DEFAULT NULL, `id_caseta` int(11) DEFAULT NULL," +
                    "  `fondo` varchar(45) DEFAULT NULL,  `monto_alarma` int(11) DEFAULT '500'," +
                    "  PRIMARY KEY (`id`) ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;");
            createStatement.execute("CREATE TABLE IF NOT EXISTS `caseta` (" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT, `descripcion` varchar(45) DEFAULT NULL, `id_estacionameinto` int(11) DEFAULT NULL," +
                    "  `id_tarifa` int(11) DEFAULT NULL, `series` varchar(45) DEFAULT NULL,  PRIMARY KEY (`id`) ) "
                    + "ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;");
            createStatement.execute("CREATE TABLE IF NOT EXISTS `descuentos` (" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT, `folio` varchar(45) DEFAULT NULL,  `descuento` decimal(10,0) DEFAULT NULL,  "
                    + "`activo` varchar(20) DEFAULT NULL,  `clave` varchar(20) DEFAULT NULL, PRIMARY KEY (`id`) )"
                    + " ENGINE=InnoDB AUTO_INCREMENT=251 DEFAULT CHARSET=utf8;");
            
            createStatement.execute("CREATE TABLE IF NOT EXISTS `detalle_turno` ( `id` int(11) NOT NULL AUTO_INCREMENT," +
                    "  `id_remoto` varchar(45) DEFAULT NULL, `folio_inicial` bigint(20) DEFAULT '0'," +
                    "  `folio_final` bigint(20) DEFAULT '0', `no_bol_turno_a` int(11) DEFAULT '0'," +
                    "  `no_bol_cancelados` int(11) DEFAULT '0', `no_bol_perdidos` int(11) DEFAULT '0'," +
                    "  `no_bol_cobrados` int(11) DEFAULT '0',  `no_bol_turno_s` int(11) DEFAULT '0'," +
                    "  `total` decimal(10,0) DEFAULT '0', `no_bol` int(11) DEFAULT '0'," +
                    "  `id_turno` int(11) DEFAULT NULL,  `no_bol_manual` int(11) DEFAULT '0'," +
                    "  `no_bol_contra` int(11) DEFAULT '0',  `serie` varchar(45) DEFAULT '0'," +
                    "  PRIMARY KEY (`id`) ) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;");
            
            createStatement.execute("CREATE TABLE IF NOT EXISTS `estacionamiento` ( `id` int(11) NOT NULL AUTO_INCREMENT," +
                    "  `centro_costos` int(11) DEFAULT NULL,  `descripcion` varchar(45) DEFAULT NULL," +
                    "  `caseta_actual` int(11) DEFAULT NULL,  `id_tarifa` int(11) DEFAULT NULL," +
                    "  `direccion` varchar(45) DEFAULT NULL, `tipo` varchar(45) DEFAULT NULL," +
                    "  `correo` varchar(45) DEFAULT NULL, `contra` varchar(45) DEFAULT NULL," +
                    "  `id_remoto` varchar(45) DEFAULT NULL,  PRIMARY KEY (`id`)" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;");
            
            createStatement.execute("CREATE TABLE IF NOT EXISTS `progresivos` (  `id` int(11) NOT NULL AUTO_INCREMENT," +
                    "  `tipo` varchar(45) DEFAULT NULL,  `ultimo_progresivo` char(12) DEFAULT NULL," +
                    "  `id_cajero` int(11) DEFAULT NULL, PRIMARY KEY (`id`)" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;");
            
            createStatement.execute("CREATE TABLE IF NOT EXISTS `propietario_perdido` ( `id` int(11) NOT NULL AUTO_INCREMENT," +
                    "  `nombre` varchar(40) DEFAULT NULL, `direccion` varchar(45) DEFAULT NULL," +
                    "  `telefono` varchar(45) DEFAULT NULL, `tipo_identificacion` varchar(45) DEFAULT NULL," +
                    "  `numero_identificacion` varchar(45) DEFAULT NULL,  PRIMARY KEY (`id`)" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;");
            createStatement.execute("CREATE TABLE IF NOT EXISTS `retiro_parcial` (" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,  `progresivo` int(11) DEFAULT NULL," +
                    "  `fecha` varchar(45) DEFAULT NULL,  `hora` varchar(45) DEFAULT NULL," +
                    "  `id_caseta` int(11) DEFAULT NULL,  `id_turno` int(11) DEFAULT NULL," +
                    "  `monto` decimal(10,0) DEFAULT NULL, `monto_real` decimal(10,0) DEFAULT NULL," +
                    "  PRIMARY KEY (`id`) ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;");
            
            createStatement.execute("CREATE TABLE IF NOT EXISTS `tarifa` ( `id` int(11) NOT NULL AUTO_INCREMENT," +
                    "  `fracciones` int(11) DEFAULT NULL,  `costos` varchar(100) DEFAULT NULL," +
                    "  `precio_hora` decimal(10,0) DEFAULT NULL, `tarifa_maxima` decimal(10,0) DEFAULT NULL," +
                    "  `boleto_perdido` decimal(10,0) DEFAULT NULL, `descripcion` varchar(45) DEFAULT NULL," +
                    "  `tarifa_unica` decimal(10,0) DEFAULT NULL, `monto_inicial` decimal(10,0) DEFAULT '0'," +
                    "  `perdido_tiempo` varchar(4) DEFAULT 'SI', PRIMARY KEY (`id`))"
                    + " ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;");
            
            createStatement.execute("CREATE TABLE IF NOT EXISTS `turnos` ( `id` int(11) NOT NULL AUTO_INCREMENT," +
                    "  `tipo_turno` varchar(45) DEFAULT NULL, `fecha_apertura` varchar(45) DEFAULT NULL," +
                    "  `fecha_cierre` varchar(45) DEFAULT NULL, `hora_apertura` varchar(5) DEFAULT NULL," +
                    "  `hora_cierre` varchar(5) DEFAULT NULL,  `id_empleado_apertura` int(11) DEFAULT '0'," +
                    "  `id_empleado_cierre` int(11) DEFAULT '0', `id_remoto` varchar(45) DEFAULT NULL," +
                    "  `estado_servidor` varchar(45) DEFAULT NULL, PRIMARY KEY (`id`)" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;");
            
            createStatement.execute("CREATE TABLE IF NOT EXISTS `usuarios` (" +
                    "  `id` tinyint(3) unsigned zerofill NOT NULL AUTO_INCREMENT," +
                    "  `id_caseta` tinyint(3) unsigned zerofill DEFAULT NULL," +
                    "  `nombre` char(25) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL," +
                    "  `contras` char(10) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL," +
                    "  `clave` varchar(12) DEFAULT NULL,  `nombre_completo` varchar(80) NOT NULL," +
                    "  `tipo_empleado` varchar(45) DEFAULT NULL,  PRIMARY KEY (`id`)" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;");


            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    
    
}

