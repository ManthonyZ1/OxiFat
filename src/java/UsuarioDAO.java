import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean actualizarContraseña(String email, String nuevaContraseña) {
        boolean actualizado = false;
        try (Connection conn = Conexion.conectar()) {
            String sql = "UPDATE usuarios SET contraseña = ? WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nuevaContraseña);  // Idealmente usar hash
            stmt.setString(2, email);
            actualizado = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actualizado;
    }

    public boolean verificarUsuario(String email, String contraseña) {
        try (Connection conn = Conexion.conectar()) {
            String sql = "SELECT * FROM usuarios WHERE email = ? AND contraseña = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, contraseña);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
