package modelos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Empleado implements IDBModel, Serializable {

    long id;

    String nombre;
    String tipoPuesto;
    String tipoTurno;
    String usuario;
    String contraseña;
    String clave;

    public Empleado() {
    }

    public Empleado(int id, String nombre, String tipoPuesto, String usuario, String contraseña, String clave) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.clave = clave;
        this.tipoPuesto = tipoPuesto;
    }

    public static Empleado getById(Long idEmpleado) {
        Empleado empleado = null;
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT * FROM usuarios where id = ?");
            statement.setLong(1, idEmpleado);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                empleado = new Empleado(resultSet.getInt("id"),
                        resultSet.getString("nombre_completo"),
                        resultSet.getString("tipo_empleado"),
                        resultSet.getString("nombre"), resultSet.getString("contras"), resultSet.getString("clave"));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empleado;
    }

    public static ArrayList<Empleado> getAll() {
        ArrayList<Empleado> empleados = new ArrayList<>();
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT id FROM usuarios");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                empleados.add(Empleado.getById(resultSet.getLong("id")));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empleados;
    }

    public static Empleado getEmpleadoLogin(String usuario, String contraseña) {
        Empleado empleado = null;
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT id FROM usuarios where nombre = ? and contras= ?");
            statement.setString(1, usuario);
            statement.setString(2, contraseña);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                empleado = Empleado.getById(resultSet.getLong("id"));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empleado;
    }

    public static Empleado getByIdClave(String id) {
        Empleado empleado = null;
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT id FROM usuarios where clave = ? and  id= ?");
            statement.setString(1, id.substring(0, 6));
            statement.setInt(2, Integer.valueOf(id.substring(6, 12)));

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                empleado = Empleado.getById(resultSet.getLong("id"));
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empleado;
    }

    @Override
    public void guardar() {
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("INSERT INTO usuarios (`nombre_completo`,"
                            + " `nombre`,`contras`,`tipo_empleado`,`clave`,`id_caseta`)"
                            + " VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, nombre);
            statement.setString(2, usuario);
            statement.setString(3, contraseña);
            statement.setString(4, tipoPuesto);
            statement.setString(5, clave);
            statement.setInt(6, 1);
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt("GENERATED_KEY");
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actualizar() {
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("UPDATE usuarios SET `nombre_completo`=? , `nombre` =?"
                            + ",`contras` =? ,`tipo_empleado` =? ,`clave` =?    WHERE `id`=?");
            statement.setString(1, nombre);
            statement.setString(2, usuario);
            statement.setString(3, contraseña);
            statement.setString(4, tipoPuesto);
            statement.setString(5, clave);
            statement.setLong(6, id);

            statement.executeUpdate();
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminar() {
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("DELETE FROM usuarios WHERE `id`= ?");
            statement.setLong(1, id);

            statement.executeUpdate();
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipoPuesto() {
        return tipoPuesto;
    }

    public void setTipoPuesto(String tipoPuesto) {
        this.tipoPuesto = tipoPuesto;
    }

    public String getTipoTurno() {
        return tipoTurno;
    }

    public void setTipoTurno(String tipoTurno) {
        this.tipoTurno = tipoTurno;
    }

}
