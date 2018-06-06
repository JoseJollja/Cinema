/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.unmsm.cine.dao;
import pe.unmsm.cine.dao.AbstractDAO;
import pe.unmsm.cine.dao.AccesoDB;
import pe.unmsm.cine.entidades.Sala;
import pe.unmsm.cine.estructuras.ListaDoble;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jollja
 */
public class SalaDAO implements AbstractDAO<Sala, Integer> {

@Override
    public boolean registrar(Sala nH) {
        try {
            Connection conn = AccesoDB.getConexion();

            PreparedStatement sentPrep = null;
 
            String sentencia = "INSERT INTO Habitacion (nroSala,maxPerSala, precioSala, tipoSala, estadoSala) VALUES (?,?,?,?,?)";

            sentPrep = conn.prepareStatement(sentencia);
            sentPrep.setInt(1, nH.getNroSala());
            sentPrep.setInt(2, nH.getCantMaxPersonas());
            sentPrep.setDouble(3, nH.getPrecio());
            sentPrep.setString(4, nH.getTipo());
            sentPrep.setBoolean(5, nH.getEstado());

            sentPrep.executeUpdate();
            conn.close();
            sentPrep.close();

        } catch (SQLException t) {
            System.out.println(t);
            return false;
        }
        return true;
    }

    @Override

    public boolean modificar(Sala nH) {
        try {

            Connection conn = AccesoDB.getConexion();
            PreparedStatement sentPrep = null;

            String sentencia = "UPDATE Sala "
                    + "SET nroSala = ?,"
                    + "maxPerSala = ?, "
                    + "precioSala = ?, "
                    + "caractSala = ?, "
                    + "estadoSala = ? "
                    + "WHERE codSala = ?;";

            sentPrep = conn.prepareStatement(sentencia);
            sentPrep.setInt(1, nH.getNroSala());
            sentPrep.setInt(2, nH.getCantMaxPersonas());
            sentPrep.setDouble(3, nH.getPrecio());
            sentPrep.setString(4, nH.getTipo());
            sentPrep.setBoolean(5, nH.getEstado());
            sentPrep.setInt(6, nH.getCodigo());

            sentPrep.executeUpdate();

            conn.close();
            sentPrep.close();

            return true;

        } catch (SQLException e) {
            System.out.println(e);
        }

        return false;
    }

    @Override
    public Sala get(Integer id) {
        try {
            Connection conn = AccesoDB.getConexion();

            PreparedStatement sentPrep = null;

            ResultSet rs = null;

            String sentencia = "SELECT * FROM Habitacion WHERE codHabitacion = ?";

            sentPrep = conn.prepareStatement(sentencia);
            sentPrep.setInt(1, id);

            rs = sentPrep.executeQuery();

            Sala sal = null;
            if (rs.next()) {
                int codigo = rs.getInt("codSala");
                int nroSala = rs.getInt("nroSala");
                int maxPorSala = rs.getInt("maxPerSala");
                double precio = rs.getDouble("precioSala");
                String tipo = rs.getString("caractSala");
                boolean estadoSala = rs.getBoolean("estadoSala");
                sal = new  Sala(codigo, nroSala, maxPorSala, precio, tipo, estadoSala);
            }

            conn.close();
            sentPrep.close();
            rs.close();

            return sal;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public ListaDoble<Sala> getAll() {

        try {

            ResultSet rs = null;
            Connection conn = null;
            PreparedStatement sentPrep = null;
            ListaDoble<Sala> listaSala = new ListaDoble<>();

            conn = AccesoDB.getConexion();

            String sentencia = "SELECT * FROM Sala";
            sentPrep = conn.prepareStatement(sentencia);

            rs = sentPrep.executeQuery();

            while (rs.next()) {
                int codigo = rs.getInt("codSala");
                int nroSala = rs.getInt("nroSala");
                int maxPorSala = rs.getInt("maxPerSala");
                double precio = rs.getDouble("precioSala");
                String tipo = rs.getString("tipoSala");
                boolean estadoSala = rs.getBoolean("estadoSala");
                listaSala.insertarAlFinal(new Sala(codigo, nroSala, maxPorSala, precio, tipo, estadoSala));
            }

            conn.close();
            rs.close();
            sentPrep.close();

            return listaSala;
        } catch (SQLException e) {
            System.out.println(e);
        }

        /* Si no encontro ningun dato, retornamos null */
        return null;
    }

    @Override
    public boolean eliminar(Integer id) {
        try {

            Connection conn = AccesoDB.getConexion();;

            PreparedStatement sentPrep = null;

            String sentencia = "DELETE FROM sala WHERE codSala = ?;";

            sentPrep = conn.prepareStatement(sentencia);
            sentPrep.setInt(1, id);

            sentPrep.executeUpdate();

            conn.close();
            sentPrep.close();

            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return false;
    }    
}
