package domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerLoginData {
    private String username;

    private String password;


    @JsonProperty("username")
    public String getUsername() {
        return username;
    }


    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
