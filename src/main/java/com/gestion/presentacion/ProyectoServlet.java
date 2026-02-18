package com.gestion.presentacion;

import com.google.gson.Gson;
import com.gestion.modelo.Proyecto;
import com.gestion.negocio.ProyectoNegocio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/proyectos")
public class ProyectoServlet extends HttpServlet {

    private ProyectoNegocio negocio = new ProyectoNegocio();
    private Gson gson = new Gson();

    // GET: Listar Proyectos
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Proyecto> lista = negocio.obtenerProyectos();

        // Configurar respuesta JSON
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(lista));
        out.flush();
    }

    // POST: Crear Proyecto
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // lee json de peticion
        BufferedReader reader = req.getReader();
        Proyecto nuevo = gson.fromJson(reader, Proyecto.class);

        boolean exito = negocio.registrarProyecto(nuevo);

        // Respuesta en archivo json
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print("{\"exito\": " + exito + "}");
        out.flush();
    }
}
