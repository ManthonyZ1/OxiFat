import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String contraseña = request.getParameter("contraseña");
        String rolIngresado = request.getParameter("rol");

        try {
            Connection conn = Conexion.conectar();
            String sql = "SELECT * FROM usuarios WHERE email = ? AND contraseña = ? AND rol = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, contraseña);
            stmt.setString(3, rolIngresado);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Datos del usuario
                String nombre = rs.getString("nombre");
                String rol = rs.getString("rol");

                // Guardar en sesión backend
                HttpSession session = request.getSession();
                session.setAttribute("id_usuario", rs.getInt("id_usuario"));
                session.setAttribute("nombre", nombre);
                session.setAttribute("rol", rol);

                // Respuesta HTML con JavaScript para sessionStorage y redirección
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().println("<script>");
                response.getWriter().println("sessionStorage.setItem('nombreUsuario', '" + nombre + "');");
                if ("vendedor".equalsIgnoreCase(rol)) {
                    response.getWriter().println("window.location.href = 'index.html?login=vendedor';");
                } else {
                    response.getWriter().println("window.location.href = 'index.html?login=cliente';");
                }
                response.getWriter().println("</script>");
            } else {
                response.sendRedirect("login.html?error=1");
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("login.html?error=2");
        }
    }
}
