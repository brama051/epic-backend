package main.models;

/**
 * Created by brama on 1/11/17.
 */
public class AuthenticationResponse {
    private String body;
    private String status;

    public AuthenticationResponse(String body, String status) {
        this.body = body;
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
