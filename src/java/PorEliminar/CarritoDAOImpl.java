package PorEliminar;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class CarritoDAOImpl implements CarritoDAO {
    
    private final Connection conn;

    public CarritoDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void agregarProducto(int idUsuario, int idProducto, int cantidad) {
        try {
            String sql = "INSERT INTO carrito (id_usuario, id_producto, cantidad) " +
                         "VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE cantidad = cantidad + ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ps.setInt(2, idProducto);
            ps.setInt(3, cantidad);
            ps.setInt(4, cantidad);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Carrito> obtenerCarritoPorUsuario(int idUsuario) {
        List<Carrito> carrito = new ArrayList<>();
        try {
            String sql = "SELECT id_producto, cantidad FROM carrito WHERE id_usuario = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                carrito.add(new Carrito(rs.getInt("id_producto"), rs.getInt("cantidad")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carrito;
    }
    @Override
    public void eliminarCarritoUsuario(int idUsuario) {
        String sql = "DELETE FROM carrito WHERE id_usuario = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }
}
