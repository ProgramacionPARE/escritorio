package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Estacionamiento implements IDBModel {

    private static final Estacionamiento estacionamiento = new Estacionamiento();

    private int id;
    private String idRemoto;
    private int centroCostos;
    private String descripcion;
    private String direccion;
    private Caseta caseta;
    private String tipo;
    private String token;
    private String correo;
    private String contra;
    

    private Estacionamiento() {
        inicializarObjeto();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getCentroCostos() {
        return centroCostos;
    }

    public void setCentroCostos(int centroCostos) {
        this.centroCostos = centroCostos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Caseta getCaseta() {
        return caseta;
    }

    public void setCaseta(Caseta caseta) {
        this.caseta = caseta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public static Estacionamiento getDatos() {
        return estacionamiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getIdRemoto() {
        return idRemoto;
    }

    public void setIdRemoto(String idRemoto) {
        this.idRemoto = idRemoto;
    }

    @Override
    public void guardar() {

    }

    @Override
    public void actualizar() {
        try {
           Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("UPDATE estacionamiento SET `centro_costos`=? ,`descripcion` =? , `direccion` =?"
                            + ",`tipo` =? ,`correo` =?,`contra` =?, `id_remoto` =?    WHERE `id`=?");
            statement.setInt(1, this.centroCostos);
            statement.setString(2, this.descripcion);
            statement.setString(3, this.direccion);
            statement.setString(4, this.tipo);
            statement.setString(5, this.correo);
            statement.setString(6, this.contra);
            statement.setString(7, this.idRemoto);
            
            statement.setLong(8, this.id);
            statement.executeUpdate();
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminar() {

    }

    private void inicializarObjeto() {
        try {
           Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT * FROM estacionamiento");

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                this.id = resultSet.getInt("id");
                this.centroCostos = resultSet.getInt("centro_costos");
                this.descripcion = resultSet.getString("descripcion");
                this.direccion = resultSet.getString("direccion");
                this.caseta = Caseta.getById(resultSet.getLong("caseta_actual"));
                this.tipo = resultSet.getString("tipo");
                this.correo = resultSet.getString("correo");
                this.contra = resultSet.getString("contra");
                this.idRemoto = resultSet.getString("id_remoto");
                
            }
        conexion.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return "Estacionamiento{" + "id=" + id + ", idRemoto=" + idRemoto + ", centroCostos=" + centroCostos + ", descripcion=" + descripcion + ", direccion=" + direccion + ", caseta=" + caseta + ", tipo=" + tipo + ", token=" + token + ", correo=" + correo + ", contra=" + contra + '}';
    }

}
