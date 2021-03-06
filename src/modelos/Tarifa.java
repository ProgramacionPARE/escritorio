/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asistente Proyectos2
 */
public class Tarifa implements IDBModel {

    Long id;
    int fraciones;
    float costos[];
    float precioHora;
    float tarifaMaxima;
    float tarifaUnica;
    float precioBoletoPerdido;
    int horasCompletas;
    String descripcion;
    float montoInicial;
    boolean perdidoTiempo;

    public Tarifa() {
        costos = new float[4];

    }

    public Tarifa(long id, int fraciones, float[] costos, float precioHora,
            float tarifaMaxima, float precioBoletoPerdido, int horasCompletas,
            String descripcion, float tarifaUnica, float montoInicial, boolean perdidoTiempo) {
        this.id = id;
        this.fraciones = fraciones;
        this.costos = costos;
        this.precioHora = precioHora;
        this.tarifaMaxima = tarifaMaxima;
        this.precioBoletoPerdido = precioBoletoPerdido;
        this.horasCompletas = horasCompletas;
        this.descripcion = descripcion;
        this.tarifaUnica = tarifaUnica;
        this.montoInicial = montoInicial;
        this.perdidoTiempo = perdidoTiempo;

    }

    public static float getImporteEstadia(Auto auto) {
        float importeMinutos = 0, importeHoras = 0;

        //Calculo del monto por fraccion de hora
        if (auto.isBoletoCancelado()) {
            return 0;
        }
        if (auto.getHorasTangibles() < auto.getTarifa().getHorasCompletas()) {
            importeMinutos = auto.getTarifa().getPrecioHora();
        } else {
            for (int i = 0; i < auto.getTarifa().getFraciones(); i++) {
                if (auto.getMinutosTangibles() > 0) {
                    importeMinutos += auto.getTarifa().costos[i];
                }
                if (auto.getMinutosTangibles() - ((60 / auto.getTarifa().getFraciones()) * (i + 1)) <= 0) {
                    break;
                }
            }
        }
        importeHoras = auto.getHorasTangibles() * auto.getTarifa().getPrecioHora();
        float importeFinal = importeHoras + importeMinutos;

        if (auto.getTarifa().getMontoInicial() > 0) {
            importeFinal += auto.getTarifa().getMontoInicial();
        }

        if (auto.getTarifa().getTarifaMaxima() > 0) {
            if (importeFinal > auto.getTarifa().getTarifaMaxima()) {
                importeFinal = auto.getTarifa().getTarifaMaxima();
            }
        }
        importeFinal -= auto.getDescuento();
        if (importeFinal < 0) {
            importeFinal = 0;
        }
        if (auto.getTarifa().getTarifaUnica() > 0) {
            importeFinal = auto.getTarifa().getTarifaUnica();
        }
        if (auto.isBoletoPerdido()) {
            if (auto.getTarifa().isPerdidoTiempo()) {
                importeFinal += auto.getTarifa().getPrecioBoletoPerdido();
            } else {
                importeFinal = auto.getTarifa().getPrecioBoletoPerdido();
            }
        }

        return importeFinal;
    }

    public static String getRangoEstadia(Auto auto) {
        int horaI = 0, horaF = 0, minutoI = 0, minutoF = 0;
        if (auto.getHorasTangibles() < auto.getTarifa().getHorasCompletas()) {
            horaF = auto.getTarifa().getHorasCompletas();
        } else {
            for (int i = 0; i < auto.getTarifa().getFraciones(); i++) {
                if (auto.getMinutosTangibles() - ((60 / auto.getTarifa().getFraciones()) * (i)) >= 0
                        && auto.getMinutosTangibles() - ((60 / auto.getTarifa().getFraciones()) * (i + 1)) < 0) {
                    minutoI = ((60 / auto.getTarifa().getFraciones()) * (i)) + 1;
                    minutoF = ((60 / auto.getTarifa().getFraciones()) * (i + 1)) % 60;
                    horaI = auto.getHorasTangibles();
                    horaF = i != auto.getTarifa().getFraciones() - 1 ? auto.getHorasTangibles()
                            : (auto.getHorasTangibles() + 1);
                    break;
                }
            }
        }
        return String.format("%02d:%02d-%02d:%02d", horaI, minutoI, horaF, minutoF);
    }

    public static Tarifa getById(long id) {
        Tarifa tarifa = new Tarifa();
        Conexion conexion = Conexion.getInstance();
        try {
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT * FROM tarifa where id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String costosString[] = resultSet.getString("costos").split("@");
                float costosArray[] = new float[resultSet.getInt("fracciones")];
                for (int i = 0; i < resultSet.getInt("fracciones"); i++) {
                    costosArray[i] = Float.valueOf(costosString[i]);
                }
                tarifa = new Tarifa(resultSet.getInt("id"), resultSet.getInt("fracciones"), costosArray,
                        resultSet.getInt("precio_hora"), resultSet.getFloat("tarifa_maxima"),
                        resultSet.getFloat("boleto_perdido"), resultSet.getInt("hora_inicial"),
                        resultSet.getString("descripcion"), resultSet.getFloat("tarifa_unica"),
                        resultSet.getFloat("monto_inicial"), resultSet.getString("perdido_tiempo").equals("SI"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }
        return tarifa;
    }

    public float getTarifaUnica() {
        return tarifaUnica;
    }

    public void setTarifaUnica(float tarifaUnica) {
        this.tarifaUnica = tarifaUnica;
    }

    public static ArrayList<Tarifa> getAll() {
        ArrayList<Tarifa> tarifas = new ArrayList<Tarifa>();
        Conexion conexion = Conexion.getInstance();
        try {
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("SELECT * FROM tarifa");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tarifas.add(Tarifa.getById(resultSet.getInt("id")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }
        return tarifas;
    }

    @Override
    public void guardar() {
        String costosString = "";
        for (Float costo : costos) {
            costosString += String.valueOf(costo) + "@";
        }
        costosString = costosString.substring(0, costosString.length() - 1);
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("INSERT INTO tarifa (`fracciones`, `costos`,"
                            + " `precio_hora`,`tarifa_maxima`,`boleto_perdido`,"
                            + "`hora_inicial`,`descripcion`,`tarifa_unica`,`monto_inicial`,`perdido_tiempo` )"
                            + " VALUES (?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, 4);
            statement.setString(2, costosString);
            statement.setFloat(3, precioHora);
            statement.setFloat(4, tarifaMaxima);
            statement.setFloat(5, precioBoletoPerdido);
            statement.setLong(6, horasCompletas);
            statement.setString(7, descripcion);
            statement.setFloat(8, tarifaUnica);
            statement.setFloat(9, montoInicial);
            statement.setString(10, perdidoTiempo ? "SI" : "NO");

            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getLong("GENERATED_KEY");
            }
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actualizar() {
        String costosString = "";
        for (Float costo : costos) {
            costosString += String.valueOf(costo) + "@";
        }
        costosString = costosString.substring(0, costosString.length() - 1);
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("UPDATE tarifa SET `fracciones`=? ,`costos` =? , "
                            + "`precio_hora` =?,`tarifa_maxima` =? ,`boleto_perdido` =?"
                            + " ,`hora_inicial` =? ,`descripcion` =?,`tarifa_unica` =? "
                            + ",`monto_inicial` =? , perdido_tiempo = ?   WHERE `id`=?");
            statement.setInt(1, 4);
            statement.setString(2, costosString);
            statement.setFloat(3, precioHora);
            statement.setFloat(4, tarifaMaxima);
            statement.setFloat(5, precioBoletoPerdido);
            statement.setLong(6, horasCompletas);
            statement.setString(7, descripcion);
            statement.setFloat(8, tarifaUnica);
            statement.setFloat(9, montoInicial);
            statement.setString(10, perdidoTiempo ? "SI" : "NO");
            statement.setLong(11, id);
            statement.executeUpdate();
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isPerdidoTiempo() {
        return perdidoTiempo;
    }

    public void setPerdidoTiempo(boolean perdidoTiempo) {
        this.perdidoTiempo = perdidoTiempo;
    }

    public float getMontoInicial() {
        return montoInicial;
    }

    public void setMontoInicial(float montoInicial) {
        this.montoInicial = montoInicial;
    }

    @Override
    public void eliminar() {
        try {
            Conexion conexion = Conexion.getInstance();
            Connection connectionDB = conexion.getConnection();
            PreparedStatement statement = connectionDB.
                    prepareStatement("DELETE FROM tarifa WHERE `id`= ?");
            statement.setLong(1, id);

            statement.executeUpdate();
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getFraciones() {
        return fraciones;
    }

    public void setFraciones(int fraciones) {
        this.fraciones = fraciones;
    }

    public float[] getCostos() {
        return costos;
    }

    public void setCostos(float[] costos) {
        this.costos = costos;
    }

    public float getPrecioHora() {
        return precioHora;
    }

    public void setPrecioHora(float precioHora) {
        this.precioHora = precioHora;
    }

    public float getTarifaMaxima() {
        return tarifaMaxima;
    }

    public void setTarifaMaxima(float tarifaMaxima) {
        this.tarifaMaxima = tarifaMaxima;
    }

    public float getPrecioBoletoPerdido() {
        return precioBoletoPerdido;
    }

    public void setPrecioBoletoPerdido(float precioBoletoPerdido) {
        this.precioBoletoPerdido = precioBoletoPerdido;
    }

    public int getHorasCompletas() {
        return horasCompletas;
    }

    public void setHorasCompletas(int horasCompletas) {
        this.horasCompletas = horasCompletas;
    }

}
