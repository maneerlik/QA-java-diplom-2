package model.pojo;

/**
 * @author  smirnov sergey
 * @since   24.04.2023
 */
public class User {

    private String email;
    private String password;
    private String name;

    public User() {
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

}