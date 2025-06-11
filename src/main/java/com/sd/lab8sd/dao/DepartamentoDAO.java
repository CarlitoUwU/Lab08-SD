package com.sd.lab8sd.dao;

import com.sd.lab8sd.model.Departamento;
import com.sd.lab8sd.model.Proyecto;
import com.sd.lab8sd.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDAO {
    public static boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM Departamento WHERE IDDpto=?";
        try (Connection c = ConexionDB.getConnection()) {
            assert c != null;
            try (PreparedStatement p = c.prepareStatement(sql)) {
                p.setInt(1, id);
                return p.executeUpdate() > 0;
            }
        }
    }

    public boolean insertar(Departamento d) throws SQLException {
        String sql = "INSERT INTO Departamento (Nombre, Telefono, Fax) VALUES (?,?,?)";
        try (Connection c = ConexionDB.getConnection()) {
            assert c != null;
            try (PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                p.setString(1, d.getNombre());
                p.setString(2, d.getTelefono());
                p.setString(3, d.getFax());
                int filas = p.executeUpdate();
                if (filas > 0) {
                    try (ResultSet rs = p.getGeneratedKeys()) {
                        if (rs.next()) d.setId(rs.getInt(1));
                    }
                }
                return filas > 0;
            }
        }
    }

    public boolean actualizar(Departamento d) throws SQLException {
        String sql = "UPDATE Departamento SET Nombre=?, Telefono=?, Fax=? WHERE IDDpto=?";
        try (Connection c = ConexionDB.getConnection()) {
            assert c != null;
            try (PreparedStatement p = c.prepareStatement(sql)) {
                p.setString(1, d.getNombre());
                p.setString(2, d.getTelefono());
                p.setString(3, d.getFax());
                p.setInt(4, d.getId());
                return p.executeUpdate() > 0;
            }
        }
    }

    public List<Proyecto> obtenerProyectosPorDepartamento(int idDpto) throws SQLException {
        String sql = "SELECT DISTINCT p.* FROM Proyecto p " +
                "JOIN Asignacion a ON p.IDProy = a.IDProy " +
                "JOIN Ingeniero i ON a.IDIng = i.IDIng " +
                "WHERE i.IDDpto = ?";
        List<Proyecto> lista = new ArrayList<>();
        try (Connection c = ConexionDB.getConnection()) {
            assert c != null;
            try (PreparedStatement p = c.prepareStatement(sql)) {
                p.setInt(1, idDpto);
                try (ResultSet rs = p.executeQuery()) {
                    while (rs.next()) {
                        Proyecto pto = new Proyecto(
                                rs.getInt("IDProy"),
                                rs.getString("Nombre"),
                                rs.getDate("Fec_Inicio").toLocalDate(),
                                rs.getDate("Fec_Termino") != null
                                        ? rs.getDate("Fec_Termino").toLocalDate() : null
                        );
                        lista.add(pto);
                    }
                }
            }
        }
        return lista;
    }

    public List<Departamento> listar() throws Exception {
        List<Departamento> lista = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = ConexionDB.getConnection();
            assert c != null;
            ps = c.prepareStatement("SELECT IDDpto, Nombre, Telefono, Fax FROM Departamento");
            rs = ps.executeQuery();
            while (rs.next()) {
                Departamento d = new Departamento(
                        rs.getInt("IDDpto"),
                        rs.getString("Nombre"),
                        rs.getString("Telefono"),
                        rs.getString("Fax")
                );
                lista.add(d);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (c != null) c.close();
        }
        return lista;
    }
}
