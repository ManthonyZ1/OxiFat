import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/RegistroServlet")
public class RegistroServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("email");
        String password = request.getParameter("contraseña");
        String rol = request.getParameter("rol");

        Registro nuevo = new Registro(nombre, correo, password, rol);
        RegistroDAO dao = new RegistroDAO();
        boolean exito = dao.insertar(nuevo);

        if (exito) {
            response.sendRedirect("login.html?exito=1");
        } else {
            response.sendRedirect("registro.html?error=1");
        }
    }
}
