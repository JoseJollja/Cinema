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
import pe.unmsm.cine.entidades.Empresa;
import pe.unmsm.cine.estructuras.ListaDoble;
/**
 *
 * @author Jollja
 */
public class ConfiguracionesDAO implements AbstractDAO<Empresa,Integer> {
    @Override
    public boolean registrar(Empresa emp) {
        try {
            Connection conn = AccesoDB.getConexion();

            PreparedStatement sentPrep = null;
 
            String sentencia = "INSERT INTO configuracion (nomEmpresa,dirEmpresa,telfEmpresa,rucEmpresa,saludoEmpresa,logoEmpresa,ubicacionBoleta) VALUES (?,?,?,?,?,?)";

            sentPrep = conn.prepareStatement(sentencia);
            
            sentPrep.setString(1, emp.getNombre());
            sentPrep.setString(2, emp.getDireccion());
            sentPrep.setString(3, emp.getTelefono());
            sentPrep.setString(4, emp.getRuc());
            sentPrep.setString(5, emp.getMensaje());
            sentPrep.setString(6, emp.getLogo() );
            sentPrep.setString(7, emp.getLink());

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
    public boolean modificar(Empresa emp) {
        try {

            Connection conn = AccesoDB.getConexion();
            PreparedStatement sentPrep = null;

            String sentencia = "UPDATE Configuracion "
                    + "SET nomEmpresa = ?,"
                    + "logoEmpresa = ?,"
                    + "dirEmpresa = ?, "
                    + "telfEmpresa = ?, "
                    + "rucEmpresa = ?, "
                    + "saludoEmpresa = ?, "
                    + "ubicacionBoleta = ?"
                    + "WHERE codConfig = ?;";

            sentPrep = conn.prepareStatement(sentencia);
            sentPrep.setString(1, emp.getNombre());
            sentPrep.setString(2, emp.getLogo());
            sentPrep.setString(3, emp.getDireccion());
            sentPrep.setString(4, emp.getTelefono());
            sentPrep.setString(5, emp.getRuc());
            sentPrep.setString(6, emp.getMensaje());
            sentPrep.setString(7, emp.getLink());
            sentPrep.setInt(8, emp.getCod());

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
    public Empresa get(Integer id) {
        try {
            Connection conn = AccesoDB.getConexion();

            PreparedStatement sentPrep = null;

            ResultSet rs = null;

            String sentencia = "SELECT * FROM Configuracion WHERE codConfig = ?";

            sentPrep = conn.prepareStatement(sentencia);
            sentPrep.setInt(1, id);

            rs = sentPrep.executeQuery();

            Empresa emp = null;
            if (rs.next()) {
                int codigo = rs.getInt("codConfig");
                String nombre = rs.getString("nomEmpresa");
                String direccion = rs.getString("dirEmpresa");
                String telefono = rs.getString("telfEmpresa");
                String ruc = rs.getString("rucEmpresa");
                String mensaje = rs.getString("saludoEmpresa");
                String logo = rs.getString("logoEmpresa");
                String link = rs.getString("ubicacionBoleta");
                emp = new Empresa(codigo,nombre,direccion,telefono,ruc,mensaje,logo,link);
            }

            conn.close();
            sentPrep.close();
            rs.close();

            return emp;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public ListaDoble<Empresa> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(Integer id) {
        try {

            Connection conn = AccesoDB.getConexion();;

            PreparedStatement sentPrep = null;

            String sentencia = "DELETE FROM configuracion WHERE codConfig = ?;";

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
