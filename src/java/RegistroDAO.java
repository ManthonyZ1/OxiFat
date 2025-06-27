import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RegistroDAO {
    private final String url = "jdbc:mysql://localhost:3306/oxifatt";
    private final String usuario = "root";
    private final String clave = "n0m3l0"; // tu contraseña real

    public boolean insertar(Registro r) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, usuario, clave);

            String sql = "INSERT INTO usuarios (nombre, email, contraseña, rol) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, r.getNombre());
            ps.setString(2, r.getCorreo());
            ps.setString(3, r.getPassword());
            ps.setString(4, r.getRol());

            int filas = ps.executeUpdate(); // ← comprueba si insertó algo
            con.close();
            return filas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
