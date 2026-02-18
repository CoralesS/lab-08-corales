package com.gestion.presentacion;

import com.google.gson.Gson;
import com.gestion.modelo.Tarea;
import com.gestion.negocio.TareaNegocio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/tareas")
public class TareaServlet extends HttpServlet {

    private TareaNegocio negocio = new TareaNegocio();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Tarea> lista = negocio.obtenerTareas();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(gson.toJson(lista));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();

        Tarea nueva = gson.fromJson(reader, Tarea.class);
        boolean exito = negocio.registrarTarea(nueva);

        resp.setContentType("application/json");
        resp.getWriter().print("{\"exito\": " + exito + "}");
    }

    // PUT: Actualizar estado
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        //lee el json
        Tarea t = gson.fromJson(reader, Tarea.class);

        boolean exito = negocio.cambiarEstadoTarea(t.getId(), t.getEstadoId());

        resp.setContentType("application/json");
        resp.getWriter().print("{\"exito\": " + exito + "}");
    }
}
