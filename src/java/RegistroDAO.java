import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegistroDAO {

    public boolean insertar(Registro r) {
        try {
            Connection con = Conexion.conectar(); // 🔁 Usamos la clase Conexion

            String sql = "INSERT INTO usuarios (nombre, email, contraseña, rol) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, r.getNombre());
            ps.setString(2, r.getCorreo());
            ps.setString(3, r.getPassword());
            ps.setString(4, r.getRol());

            int filas = ps.executeUpdate();
            con.close();
            return filas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
