public class Registro {
    private String nombre;
    private String correo;
    private String contrasena;
    private String rol;

    public Registro(String nombre, String email, String contrasena, String rol) {
        this.nombre = nombre;
        this.correo = email;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public String getPassword() { return contrasena; }
    public String getRol() { return rol; }

    // Setters (opcional si no necesitas modificarlos después de crear el objeto)
}
