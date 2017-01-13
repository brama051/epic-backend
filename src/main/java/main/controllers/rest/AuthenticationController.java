package main.controllers.rest;

import main.db.UserAuthentication;
import main.models.AuthenticationResponse;
import main.models.Token;
import main.models.User;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AuthenticationController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AuthenticationResponse login(@RequestBody User user) {
        String username = user.username;
        String password = user.password;

        System.out.println("_" + username + "_");
        System.out.println("_" + password + "_");

        if (username == "" || password == "") {
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
    public AuthenticationResponse logout(@RequestBody Token token) {
        System.out.println("Token to delete: " + token.getToken());
        UserAuthentication userAuthentication = new UserAuthentication();
        if (userAuthentication.logoutUser(token.getToken()) > -1) {
            System.out.println("Successful logout");
            userAuthentication.close();
            return new AuthenticationResponse("Uspješna odjava", "Success: Logout");
        }

        return new AuthenticationResponse("", "Error");
    }


}
