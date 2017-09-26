package moe.touko.SimpleChat.Server.User;

public class UserEntity {
    private String username;
    private String token;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }
}
