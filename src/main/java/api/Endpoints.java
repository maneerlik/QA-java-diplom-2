package api;

/**
 * @author  smirnov sergey
 * @since   24.04.2023
 */
public enum Endpoints {

    BASE_URI("https://stellarburgers.nomoreparties.site/"),
    REGISTER_USER("/api/auth/register"),
    LOGIN_USER("/api/auth/login"),
    DELETE_USER("/api/auth/user"),
    UPDATE_USER("/api/auth/user");

    private String endpoint;

    Endpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return this.endpoint;
    }

}