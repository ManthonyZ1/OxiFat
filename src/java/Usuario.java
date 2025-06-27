public class Usuario {
    private int id;
    private String email;
    private String contraseña;

    public Usuario() {}

    public Usuario(int id, String email, String contraseña) {
        this.id = id;
        this.email = email;
        this.contraseña = contraseña;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }
}
