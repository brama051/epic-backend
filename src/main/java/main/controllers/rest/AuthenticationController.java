package main.controllers.rest;

import main.db.MySQLAccess;
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
        /**
         * Todo: Verify user and return an appropriate response
         */
        if (username.length() == 0 || password.length() == 0) {
            return new AuthenticationResponse("Lozinka i korisničko ime su obavezna polja.", "Error: Missing fields");
        }

        UserAuthentication userAuthentication = new UserAuthentication();
        userAuthentication.validateUser(username, password);
        userAuthentication.close();


        /*MySQLAccess dao = new MySQLAccess();
        try {
            dao.readDataBase();

        }catch (Exception e){

        }*/

        return new AuthenticationResponse("", "");
    }


    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public AuthenticationResponse logout(@RequestParam(value = "token", defaultValue = "dummy") String token) {
        /**
         * Todo: Destroy user token
         */
        /*MySQLAccess dao = new MySQLAccess();
        try {
            dao.readDataBase();

        }catch (Exception e){

        }*/

        return new AuthenticationResponse("", "");
    }


}
