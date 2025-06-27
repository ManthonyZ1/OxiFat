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
        String rolIngresado = request.getParameter("rol"); // Cliente o Vendedor

        try {
            Connection conn = Conexion.conectar();
            String sql = "SELECT * FROM usuarios WHERE email = ? AND contraseña = ? AND rol = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, contraseña);
            stmt.setString(3, rolIngresado);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Inicio de sesión exitoso
                HttpSession session = request.getSession();
                session.setAttribute("id_usuario", rs.getInt("id_usuario"));
                session.setAttribute("nombre", rs.getString("nombre"));
                session.setAttribute("rol", rs.getString("rol"));

                if ("vendedor".equalsIgnoreCase(rs.getString("rol"))) {
                    response.sendRedirect("index.html?login=vendedor");
                } else {
                    response.sendRedirect("index.html?login=exito");
                }
            } else {
                // Usuario no encontrado o credenciales incorrectas
                response.sendRedirect("login.html?error=1");
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("login.html?error=2");
        }
    }

   
}
