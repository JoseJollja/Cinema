/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.unmsm.cine.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import pe.unmsm.cine.entidades.Comprobante;
import pe.unmsm.cine.estructuras.ListaDoble;


/**
 *
 * @author Jollja
 */
public class ComprobanteDAO implements AbstractDAO<Comprobante,Integer>{
    
    @Override
    public boolean registrar(Comprobante comp) {
        try {
            Connection conn = AccesoDB.getConexion();

            PreparedStatement sentPrep = null;
 
            String sentencia = "INSERT INTO Comprobante (codReserva,codUsuario,codConfig,deuda) VALUES (?,?,?,?)";

            sentPrep = conn.prepareStatement(sentencia);
            sentPrep.setInt(1, comp.getCodAsientos());
            sentPrep.setInt(2, comp.getCodUsuario());
            sentPrep.setInt(3, comp.getCodConfig());
            sentPrep.setDouble(4, comp.getCosto());

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
    public boolean modificar(Comprobante e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Comprobante get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListaDoble<Comprobante> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
