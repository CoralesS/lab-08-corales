package com.gestion.negocio;

import com.gestion.accesodatos.TareaDAO;
import com.gestion.modelo.Tarea;
import java.util.List;

public class TareaNegocio {

    private TareaDAO dao = new TareaDAO();

    // Listar todas las tareas
    public List<Tarea> obtenerTareas() {
        return dao.listar();
    }

    // Registrar nueva tarea
    public boolean registrarTarea(Tarea t) {
        // validacion de que una tarea debe tener titulo y pertenecer a un proyecto
        if (t.getTitulo() == null || t.getTitulo().isEmpty()) {
            return false;
        }
        if (t.getProyectoId() <= 0) {
            return false;
        }

        return dao.crear(t);
    }

    // cambia estado de la tarea
    public boolean cambiarEstadoTarea(int idTarea, int nuevoEstadoId) {
        if (idTarea <= 0 || nuevoEstadoId <= 0) {
            return false;
        }
        return dao.actualizarEstado(idTarea, nuevoEstadoId);
    }
}