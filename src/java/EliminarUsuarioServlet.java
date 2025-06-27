import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.*;

@WebServlet("/EliminarUsuarioServlet") // ← ESTA ES LA LÍNEA CLAVE
public class EliminarUsuarioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("contraseña");

        String jdbcURL = "jdbc:mysql://localhost:3306/oxifatt";
        String dbUser = "root";
        String dbPassword = "n0m3l0";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            String selectSql = "SELECT * FROM usuarios WHERE email = ? AND contraseña = ?";
            PreparedStatement selectStmt = conn.prepareStatement(selectSql);
            selectStmt.setString(1, email);
            selectStmt.setString(2, password);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                // Usuario válido, proceder a eliminar
                String deleteSql = "DELETE FROM usuarios WHERE email = ?";
                PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
                deleteStmt.setString(1, email);
                deleteStmt.executeUpdate();

                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().println("<script>");
                response.getWriter().println("alert('Cuenta eliminada correctamente');");
                response.getWriter().println("window.location.href = 'login.html';");
                response.getWriter().println("</script>");

            } else {
                // Usuario no encontrado
                response.getWriter().println("<script>alert('Credenciales incorrectas'); window.history.back();</script>");
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error al conectar con la base de datos.");
        }
    }
}
