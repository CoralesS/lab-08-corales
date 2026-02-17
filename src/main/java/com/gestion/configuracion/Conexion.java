package com.gestion.configuracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    // Configuración de la Base de Datos
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_tareas_db";
    private static final String USUARIO = "root";
    private static final String CLAVE = "Corales.7604";

    public static Connection obtenerConexion() {
        Connection conexion = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establece la conexión
            conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            System.out.println("Conexión exitosa a la base de datos.");

        } catch (ClassNotFoundException e) {
            System.err.println("No se encontró el driver de MySQL");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Falló la conexión a la BD");
            e.printStackTrace();
        }
        return conexion;
    }

    // metodo main temporal para verificar la conexion a la BD
    public static void main(String[] args) {
        // Obtiene la conexión
        java.sql.Connection prueba = Conexion.obtenerConexion();

        if (prueba != null) {
            System.out.println("La conexión se realizó correctamente");
        } else {
            System.out.println("Revisa la consola para ver el error");
        }
    }
}
