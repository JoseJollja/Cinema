/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.unmsm.cine.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Jollja
 */
public class AccesoDB {
    public static Connection getConexion(){
        Connection conexion = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdreserva", "root", "root");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return conexion;
    }
    
    public static void cerrarConexion(Connection con, PreparedStatement ps){
        try {
            con.close();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error de cerrado de conexion");
        }
    }
    
}
