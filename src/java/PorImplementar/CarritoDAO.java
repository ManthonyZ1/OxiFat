package PorImplementar;




import java.util.List;

public interface CarritoDAO {
    void agregarProducto(int idUsuario, int idProducto, int cantidad);
    List<Carrito> obtenerCarritoPorUsuario(int idUsuario);  // ← aquí estaba el error
    void eliminarCarritoUsuario(int idUsuario);
}
