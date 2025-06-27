import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/CambioContraseñaServlet")
public class CContrasena extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String actual = request.getParameter("contraseñaActual");
        String nueva = request.getParameter("nuevaContraseña");

        UsuarioDAO dao = new UsuarioDAO();

        if (dao.verificarUsuario(email, actual)) {
            if (dao.actualizarContraseña(email, nueva)) {
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().println("<script>");
                response.getWriter().println("alert('Contraseña actualizada correctamente');");
                response.getWriter().println("window.location.href = 'login.html';");
                response.getWriter().println("</script>");

            } else {
                response.sendRedirect("cambiarContraseña.jsp?error=actualizacion");
            }
        } else {
            response.sendRedirect("cambiarContraseña.jsp?error=credenciales");
        }
    }
}
