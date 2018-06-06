/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.unmsm.cine.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.unmsm.cine.entidades.Usuario;
import pe.unmsm.cine.estructuras.ListaDoble;
/**
 *
 * @author Jollja
 */
public class UsuarioDAO implements AbstractDAO<Usuario,Integer>{
    @Override
    public boolean registrar(Usuario usuario) {
        try {
            Connection conn = AccesoDB.getConexion();

            PreparedStatement sentPrep = null;
 
            String sentencia = "INSERT INTO Usuario (nomUsuario,apeUsuario,telefonoUsuario,idUsuario,claveUsuario) VALUES (?,?,?,?,?)";

            sentPrep = conn.prepareStatement(sentencia);
            sentPrep.setString(1, usuario.getNombres());
            sentPrep.setString(2, usuario.getApellidos());
            sentPrep.setString(3, usuario.getTelefono());
            sentPrep.setString(4, usuario.getId());
            sentPrep.setString(5, usuario.getContraseña());

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
    public boolean modificar(Usuario usuario) {
        try {

            Connection conn = AccesoDB.getConexion();
            PreparedStatement sentPrep = null;

            String sentencia = "UPDATE Usuario "
                    + "SET nomUsuario = ?,"
                    + "apeUsuario = ?, "
                    + "telefonoUsuario = ?, "
                    + "idUsuario = ?, "
                    + "claveUsuario = ? "
                    + "WHERE codUsuario = ?;";

            sentPrep = conn.prepareStatement(sentencia);
            sentPrep.setString(1, usuario.getNombres());
            sentPrep.setString(2, usuario.getApellidos());
            sentPrep.setString(3, usuario.getTelefono());
            sentPrep.setString(4, usuario.getId());
            sentPrep.setString(5, usuario.getContraseña());
            sentPrep.setInt(6, usuario.getCodigo());

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
    public Usuario get(Integer id) {
        try {
            Connection conn = AccesoDB.getConexion();

            PreparedStatement sentPrep = null;

            ResultSet rs = null;

            String sentencia = "SELECT * FROM Usuario WHERE codUsuario= ?";

            sentPrep = conn.prepareStatement(sentencia);
            sentPrep.setInt(1, id);

            rs = sentPrep.executeQuery();

            Usuario usuario = null;
            if (rs.next()) {
                int cod = rs.getInt("codUsuario");
                String nombres = rs.getString("nomUsuario");
                String apellidos = rs.getString("apeUsuario");
                String telefono = rs.getString("telefonoUsuario");
                String idUsuario = rs.getString("idUsuario");
                String contraseña = rs.getString("claveUsuario");
                usuario = new Usuario(cod,nombres,apellidos,telefono,idUsuario,contraseña);
            }

            conn.close();
            sentPrep.close();
            rs.close();

            return usuario;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public ListaDoble<Usuario> getAll() {
        try {

            ResultSet rs = null;
            Connection conn = null;
            PreparedStatement sentPrep = null;
            ListaDoble<Usuario> listaUsuarios = new ListaDoble<>();

            conn = AccesoDB.getConexion();

            String sentencia = "SELECT * FROM Usuario";
            sentPrep = conn.prepareStatement(sentencia);

            rs = sentPrep.executeQuery();

            while (rs.next()) {
                int cod = rs.getInt("codUsuario");
                String nombres = rs.getString("nomUsuario");
                String apellidos = rs.getString("apeUsuario");
                String telefono = rs.getString("telefonoUsuario");
                String idUsuario = rs.getString("idUsuario");
                String contraseña = rs.getString("claveUsuario");
                listaUsuarios.insertarAlFinal(new Usuario(cod,nombres,apellidos,telefono,idUsuario,contraseña));
            }

            conn.close();
            rs.close();
            sentPrep.close();

            return listaUsuarios;
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

            String sentencia = "DELETE FROM Usuario WHERE codUsuario = ?;";

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
