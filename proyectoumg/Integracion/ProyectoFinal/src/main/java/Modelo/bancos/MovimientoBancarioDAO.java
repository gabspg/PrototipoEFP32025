package Modelo.bancos;

import Controlador.bancos.movimiento_bancario;
import Modelo.Conexion;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
// Made By Ruddyard Castro 
public class MovimientoBancarioDAO {
    // Actualizadas para incluir los nuevos campos
    private static final String SQL_SELECT = "SELECT id_movimiento_bancario, id_cuenta, fecha, tipo_saldo, monto, saldo_actualizado FROM movimientos_bancarios";
    private static final String SQL_INSERT = "INSERT INTO movimientos_bancarios(id_cuenta, fecha, tipo_saldo, monto, saldo_actualizado) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE movimientos_bancarios SET id_cuenta=?, fecha=?, tipo_saldo=?, monto=?, saldo_actualizado=? WHERE id_movimiento_bancario = ?";
    private static final String SQL_DELETE = "DELETE FROM movimientos_bancarios WHERE id_movimiento_bancario=?";
    private static final String SQL_QUERY = "SELECT id_movimiento_bancario, id_cuenta, fecha, tipo_saldo, monto, saldo_actualizado FROM movimientos_bancarios WHERE id_movimiento_bancario = ?";

    public List<movimiento_bancario> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<movimiento_bancario> movimientos = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_movimiento_bancario");
                int idCuenta = rs.getInt("id_cuenta");
                LocalDateTime fecha = rs.getTimestamp("fecha").toLocalDateTime();
                String tipoSaldo = rs.getString("tipo_saldo");
                float monto = rs.getFloat("monto");
                float saldoActualizado = rs.getFloat("saldo_actualizado");

                movimiento_bancario movimiento = new movimiento_bancario();
                movimiento.setId_movimiento_bancario(id);
                movimiento.setId_cuenta(idCuenta);
                movimiento.setFecha(fecha);
                movimiento.setTipoSaldo(tipoSaldo);
                movimiento.setMonto(monto);
                movimiento.setSaldoActualizado(saldoActualizado);

                movimientos.add(movimiento);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return movimientos;
    }

    public int insert(movimiento_bancario movimiento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, movimiento.getId_cuenta());
            stmt.setTimestamp(2, Timestamp.valueOf(movimiento.getFecha()));
            stmt.setString(3, movimiento.getTipoSaldo());
            stmt.setFloat(4, movimiento.getMonto());
            stmt.setFloat(5, movimiento.getSaldoActualizado());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int update(movimiento_bancario movimiento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, movimiento.getId_cuenta());
            stmt.setTimestamp(2, Timestamp.valueOf(movimiento.getFecha()));
            stmt.setString(3, movimiento.getTipoSaldo());
            stmt.setFloat(4, movimiento.getMonto());
            stmt.setFloat(5, movimiento.getSaldoActualizado());
            stmt.setInt(6, movimiento.getId_movimiento_bancario());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int delete(movimiento_bancario movimiento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, movimiento.getId_movimiento_bancario());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public movimiento_bancario query(movimiento_bancario movimiento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, movimiento.getId_movimiento_bancario());
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id_movimiento_bancario");
                int idCuenta = rs.getInt("id_cuenta");
                LocalDateTime fecha = rs.getTimestamp("fecha").toLocalDateTime();
                String tipoSaldo = rs.getString("tipo_saldo");
                float monto = rs.getFloat("monto");
                float saldoActualizado = rs.getFloat("saldo_actualizado");

                movimiento = new movimiento_bancario();
                movimiento.setId_movimiento_bancario(id);
                movimiento.setId_cuenta(idCuenta);
                movimiento.setFecha(fecha);
                movimiento.setTipoSaldo(tipoSaldo);
                movimiento.setMonto(monto);
                movimiento.setSaldoActualizado(saldoActualizado);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return movimiento;
    }

}