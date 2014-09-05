

package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Conexion;


public class PropietarioPerdido {

    int id;
    BoletoPerdido boletoPerdido; 
    String nombre;
    String direccion;
    String telefono;
    String tipoIdentificacion;
    String numeroIdentificacion;

    public PropietarioPerdido() {
    }

    public PropietarioPerdido( BoletoPerdido boletoPerdido, String nombre, String direccion, String telefono, String tipoIdentificacion, String numeroIdentificacion) {
        this.boletoPerdido = boletoPerdido;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipoIdentificacion = tipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BoletoPerdido getBoletoPerdido() {
        return boletoPerdido;
    }

    public void setBoletoPerdido(BoletoPerdido boletoPerdido) {
        this.boletoPerdido = boletoPerdido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }
    
    static PropietarioPerdido getById(int id,BoletoPerdido boleto) {
         PropietarioPerdido propietarioPerdido = null;
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("SELECT * FROM propietario_perdido where id = ?");
            statement.setInt(1, id);
            ResultSet executeQuery = statement.executeQuery();
            if (executeQuery.next()){
                propietarioPerdido = new PropietarioPerdido(boleto,
                executeQuery.getString("nombre"), executeQuery.getString("direccion"),
                executeQuery.getString("telefono") , executeQuery.getString("tipo_identificacion"),
                executeQuery.getString("numero_identificacion"));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return propietarioPerdido;
    }
    
    public void guardar() {
        try {
            Conexion conexion = new Conexion();
            Connection connectionDB = conexion.getConnectionDB();
            PreparedStatement  statement = connectionDB.
            prepareStatement("INSERT INTO propietario_perdido (`nombre`, `direccion`,"+
                            " `telefono`,`tipo_identificacion`,`numero_identificacion`)"
                            + " VALUES (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, nombre);
            statement.setString(2, direccion);
            statement.setString(3, telefono);
            statement.setString(4, tipoIdentificacion); 
            statement.setString(5, numeroIdentificacion);
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next())
                id = generatedKeys.getInt("GENERATED_KEY");
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
