package PorImplementar;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Mike
 */
/*
@WebServlet("/AgregarCarritoServlet")
public class AgregarCarritoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("id_usuario") == null) {
            response.sendRedirect("login.html");
            return;
        }

        int idUsuario = (int) session.getAttribute("id_usuario");
        int idProducto = Integer.parseInt(request.getParameter("id_producto"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        try (Connection conn = Conexion.conectar()) {
            CarritoDAO carritoDAO = new CarritoDAOImpl(conn);
            carritoDAO.agregarProducto(idUsuario, idProducto, cantidad);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("carrito.html");
    }
}
*/