/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import domain.Bodega; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author visitante
 */
public class BodegaDAO {

    private static final String SQL_SELECT = "SELECT pkid, tipo_bodega, nombre_bodega, direccion, estado FROM bodega";
    private static final String SQL_INSERT = "INSERT INTO bodega(pkid, tipo_bodega, nombre_bodega, direccion, estado) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE bodega SET tipo_bodega=?, nombre_bodega=?,direccion=?, estado=? WHERE pkid =?"; //comodines son los =?
    private static final String SQL_DELETE = "DELETE FROM bodega WHERE pkid=?";
    private static final String SQL_QUERY = "SELECT pkid, tipo_bodega, nombre_bodega, direccion, estado FROM bodega WHERE pkid = ?";

    public List<Bodega> select() { //primer mantenimiento, va a consultar
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Bodega bodega = null; //vendedor = alumno, Vendedor = Alumno
        List<Bodega> bodegas = new ArrayList<Bodega>(); //vendedores = alumnos

        try { //try es un if, permite condicionar y captar un error
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String pKid = rs.getString("pkid");
                String tipo_Bodega = rs.getString("tipo_bodega");
                String nombre_Bodega = rs.getString("nombre_bodega");
                String Direccion = rs.getString("direccion");
                String Estado = rs.getString("estado");
                
                bodega = new Bodega();
                bodega.setPkid(pKid);
                bodega.setTipo_bodega(tipo_Bodega);
                bodega.setNombre_bodega(nombre_Bodega);
                bodega.setDireccion(Direccion);
                bodega.setEstado(Estado);
                
                bodegas.add(bodega);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return bodegas;
    }

    public int insert(Bodega bodega) { //segundo metodo, permite establecer informacion
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            //stmt.setString(1, alumno.getCarnet_alumno());
            stmt.setString(1, bodega.getPkid());
            stmt.setString(2, bodega.getTipo_bodega());
            stmt.setString(3, bodega.getNombre_bodega());
            stmt.setString(4, bodega.getDireccion());
            stmt.setString(5, bodega.getEstado());

            System.out.println("ejecutando query: " + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int update(Bodega bodega) { //tercer mantenimiento, actualiza
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            
             stmt.setString(1, bodega.getTipo_bodega());
            stmt.setString(2, bodega.getNombre_bodega());
            stmt.setString(3, bodega.getDireccion());
            stmt.setString(4, bodega.getEstado());
            stmt.setString(5, bodega.getPkid());
            
            
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizado: " + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int delete(Bodega bodega) {//cuarto metodo, borra
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query: " + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, bodega.getPkid());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados: " + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

//    public List<Persona> query(Persona vendedor) { // Si se utiliza un ArrayList
    public Bodega query(Bodega bodega) {    //Select enfocado
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Bodega> bodegas = new ArrayList<Bodega>();
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query: " + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setString(1, bodega.getPkid());
            rs = stmt.executeQuery();
            while (rs.next()) {
                String pKid = rs.getString("pkid");
                String tipoBodega = rs.getString("tipo_bodega");
                String nombreBodega = rs.getString("nombre_bodega");
                String Direccion = rs.getString("direccion");
                String Estado = rs.getString("estado");
                
                bodega = new Bodega();
                bodega.setPkid(pKid);
                bodega.setTipo_bodega(tipoBodega);
                bodega.setNombre_bodega(nombreBodega);
                bodega.setDireccion(Direccion);
                bodega.setEstado(Estado);
                //con los set, enviamos los objetos
                //vendedores.add(vendedor); // Si se utiliza un ArrayList
            }
            //System.out.println("Registros buscado:" + vendedor);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        //return vendedores;  // Si se utiliza un ArrayList
        return bodega; //retorna el objeto unico
    }
        
}
