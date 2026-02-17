package com.gestion.accesodatos;

import com.gestion.configuracion.Conexion;
import com.gestion.modelo.Tarea;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TareaDAO {

    // Lista tareas proyecto y estado
    public List<Tarea> listar() {
        List<Tarea> lista = new ArrayList<>();

        String sql = "SELECT t.id, t.titulo, t.descripcion, " +
                "t.proyecto_id, p.nombre AS nombre_proyecto, " +
                "t.estado_id, e.nombre AS nombre_estado " +
                "FROM tarea t " +
                "INNER JOIN proyecto p ON t.proyecto_id = p.id " +
                "INNER JOIN estado e ON t.estado_id = e.id";

        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Tarea t = new Tarea();
                t.setId(rs.getInt("id"));
                t.setTitulo(rs.getString("titulo"));
                t.setDescripcion(rs.getString("descripcion"));

                t.setProyectoId(rs.getInt("proyecto_id"));
                t.setEstadoId(rs.getInt("estado_id"));

                t.setNombreProyecto(rs.getString("nombre_proyecto"));
                t.setNombreEstado(rs.getString("nombre_estado"));

                lista.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Lista tareas por proyecto
    public List<Tarea> listarPorProyecto(int proyectoId) {
        List<Tarea> lista = new ArrayList<>();
        String sql = "SELECT t.id, t.titulo, t.descripcion, " +
                "t.proyecto_id, p.nombre AS nombre_proyecto, " +
                "t.estado_id, e.nombre AS nombre_estado " +
                "FROM tarea t " +
                "INNER JOIN proyecto p ON t.proyecto_id = p.id " +
                "INNER JOIN estado e ON t.estado_id = e.id " +
                "WHERE t.proyecto_id = ?";

        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, proyectoId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Tarea t = new Tarea();
                    t.setId(rs.getInt("id"));
                    t.setTitulo(rs.getString("titulo"));
                    t.setDescripcion(rs.getString("descripcion"));
                    t.setProyectoId(rs.getInt("proyecto_id"));
                    t.setEstadoId(rs.getInt("estado_id"));
                    t.setNombreProyecto(rs.getString("nombre_proyecto"));
                    t.setNombreEstado(rs.getString("nombre_estado"));
                    lista.add(t);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // CREAR TAREA
    public boolean crear(Tarea t) {
        String sql = "INSERT INTO tarea (titulo, descripcion, proyecto_id, estado_id) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, t.getTitulo());
            ps.setString(2, t.getDescripcion());
            ps.setInt(3, t.getProyectoId());
            ps.setInt(4, t.getEstadoId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Metodo para actualizar el estado de tarea
    public boolean actualizarEstado(int idTarea, int nuevoEstadoId) {
        String sql = "UPDATE tarea SET estado_id = ? WHERE id = ?";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, nuevoEstadoId);
            ps.setInt(2, idTarea);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
