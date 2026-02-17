package com.gestion.accesodatos;

import com.gestion.configuracion.Conexion;
import com.gestion.modelo.Proyecto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProyectoDAO {

    // Metodo para listar todos los proyectos
    public List<Proyecto> listar() {
        List<Proyecto> lista = new ArrayList<>();
        String sql = "SELECT * FROM proyecto";

        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Proyecto p = new Proyecto();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setFechaInicio(rs.getDate("fecha_inicio"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Metodo para crear un nuevo proyecto
    public boolean crear(Proyecto p) {
        String sql = "INSERT INTO proyecto (nombre, descripcion, fecha_inicio) VALUES (?, ?, ?)";

        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setDate(3, p.getFechaInicio());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}