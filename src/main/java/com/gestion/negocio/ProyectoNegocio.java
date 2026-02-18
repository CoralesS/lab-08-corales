package com.gestion.negocio;

import com.gestion.accesodatos.ProyectoDAO;
import com.gestion.modelo.Proyecto;
import java.util.List;

public class ProyectoNegocio {

    private ProyectoDAO dao = new ProyectoDAO();

    // Obtener la lista de proyectos
    public List<Proyecto> obtenerProyectos() {
        return dao.listar();
    }

    // Registrar un nuevo proyecto con validación básica
    public boolean registrarProyecto(Proyecto p) {
        // Valida nombre no este vacio
        if (p.getNombre() == null || p.getNombre().trim().isEmpty()) {
            return false;
        }

        // llama al DAO
        return dao.crear(p);
    }
}
