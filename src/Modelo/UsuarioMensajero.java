package Modelo;

public class UsuarioMensajero {
    private String login;
    private String password;
    private Mensajero mensajero;

    public UsuarioMensajero(String login, String password, Mensajero mensajero) {
        this.login = login;
        this.password = password;
        this.mensajero = mensajero;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Mensajero getMensajero() {
        return mensajero;
    }

    public void setMensajero(Mensajero mensajero) {
        this.mensajero = mensajero;
    }
}
