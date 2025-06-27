import java.sql.*;

public class LoginDAO {
    private final String URL = "jdbc:mysql://localhost:3306/oxifatt?useSSL=false";
    private final String USER = "root";
    private final String PASSWORD = "n0m310"; // usa tu contraseña real

    public String validarUsuario(Login login) {
        String rol = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT rol FROM usuarios WHERE email = ? AND contraseña = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, login.getEmail());
            stmt.setString(2, login.getContraseña());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                rol = rs.getString("rol");
            }

            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return rol;
    }
}
