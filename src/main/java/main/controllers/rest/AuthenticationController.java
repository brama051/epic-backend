package main.controllers.rest;

import main.db.UserAuthentication;
import main.models.AuthenticationResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AuthenticationResponse login(@RequestParam(value = "username", defaultValue = "") String username, @RequestParam(value = "password", defaultValue = "") String password) {
        if (username.length() == 0 || password.length() == 0) {
            return new AuthenticationResponse("Lozinka i korisničko ime su obavezna polja.", "Error: Missing fields");
        }

        UserAuthentication userAuthentication = new UserAuthentication();
        if (userAuthentication.validateUser(username, password) == 1) {
            String token = "";
            token = userAuthentication.createToken(username);
            userAuthentication.close();
            if (token != "") {
                return new AuthenticationResponse(token, "Success: Login");
            }
        }

        return new AuthenticationResponse("Neispravna lozinka ili korisničko ime", "Error: Incorrect username or password");
    }


    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public AuthenticationResponse logout(@RequestParam(value = "token", defaultValue = "dummy") String token) {
        UserAuthentication userAuthentication = new UserAuthentication();
        if (userAuthentication.logoutUser(token) == 1) {
            return new AuthenticationResponse("Uspješna odjava", "Success: Logout");
        }

        return new AuthenticationResponse("", "Error");
    }


}
