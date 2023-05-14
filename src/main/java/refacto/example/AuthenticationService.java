package refacto.example;

public class AuthenticationService {
    private User connectedUser;

    public boolean isClient() {
        return false;
    }

    public User getConnectedUser() {
        return connectedUser;
    }

    public void setConnectedUser(User connectedUser) {
        this.connectedUser = connectedUser;
    }
}
