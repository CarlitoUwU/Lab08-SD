package com.sd.lab8sd.dao;

import com.sd.lab8sd.model.Proyecto;
import com.sd.lab8sd.util.ConexionDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProyectoDAO {
    public boolean insertar(Proyecto p) throws SQLException {
        String sql = "INSERT INTO Proyecto (Nombre, Fec_Inicio, Fec_Termino) VALUES (?,?,?)";
        try (Connection c = ConexionDB.getConnection()) {
            assert c != null;
            try (PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, p.getNombre());
                ps.setDate(2, Date.valueOf(p.getFechaInicio()));
                ps.setDate(3, p.getFechaFin() != null ? Date.valueOf(p.getFechaFin()) : null);
                int filas = ps.executeUpdate();
                if (filas > 0) {
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) p.setIdProy(rs.getInt(1));
                    }
                }
                return filas > 0;
            }
        }
    }

    public boolean actualizar(Proyecto p) throws SQLException {
        String sql = "UPDATE Proyecto SET Nombre=?, Fec_Inicio=?, Fec_Termino=? WHERE IDProy=?";
        try (Connection c = ConexionDB.getConnection()) {
            assert c != null;
            try (PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setString(1, p.getNombre());
                ps.setDate(2, Date.valueOf(p.getFechaInicio()));
                ps.setDate(3, p.getFechaFin() != null ? Date.valueOf(p.getFechaFin()) : null);
                ps.setInt(4, p.getIdProy());
                return ps.executeUpdate() > 0;
            }
        }
    }

    public boolean eliminar(int idProy) throws SQLException {
        String sql = "DELETE FROM Proyecto WHERE IDProy=?";
        try (Connection c = ConexionDB.getConnection()) {
            assert c != null;
            try (PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setInt(1, idProy);
                return ps.executeUpdate() > 0;
            }
        }
    }

    public List<Proyecto> listar() throws Exception {
        List<Proyecto> lista = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = ConexionDB.getConnection();
            assert c != null;
            ps = c.prepareStatement("SELECT IDProy, Nombre, Fec_Inicio, Fec_Termino FROM Proyecto");
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("IDProy");
                String nombre = rs.getString("Nombre");
                LocalDate fechaInicio = rs.getDate("Fec_Inicio").toLocalDate();
                Date fechaFinDate = rs.getDate("Fec_Termino");
                LocalDate fechaFin = (fechaFinDate != null) ? fechaFinDate.toLocalDate() : null;
                Proyecto p = new Proyecto(id, nombre, fechaInicio, fechaFin);
                lista.add(p);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (c != null) c.close();
        }
        return lista;
    }
}

