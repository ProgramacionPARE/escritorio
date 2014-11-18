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
    private int centroCostos;
    private String descripcion;
    private String direccion;
    private Caseta caseta;
    private String tipo;
    private String token;

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
                            + ",`tipo` =?   WHERE `id`=?");
            statement.setInt(1, this.centroCostos);
            statement.setString(2, this.descripcion);
            statement.setString(3, this.direccion);
            statement.setString(4, this.tipo);
            statement.setLong(5, this.id);
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
            }
        conexion.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
