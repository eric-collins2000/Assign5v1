package com.example.Main;



import com.example.Main.DocRobsStuff.DocRobsHash;
import com.example.Main.Models.Person;
import com.example.Main.Models.Session;
import com.example.Main.Models.User;
import com.example.Main.Repo.PersonRepository;
import com.example.Main.Repo.SessionRepository;
import com.example.Main.Repo.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.annotation.Commit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller // This means that this class is a Controller
public class MainController {
    static String userID = null;
    Session session = new Session();
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;

    @Commit
    @PostMapping(path="/addUser") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String login
            , @RequestParam String password) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User();
        n.setLogin(login);
        n.setPassword(password);
        userRepository.save(n);
        return "Saved";
    }
    @GetMapping(path="/allUsers")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }
    
    @Autowired
    private PersonRepository personRepository;
    @Commit
    @PostMapping(path="/people") // Map ONLY POST Requests
    public @ResponseBody String addNewPerson (@RequestParam String firstName
            , @RequestParam String lastName, @RequestParam String dob) throws ParseException {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-dd-MM");
        Date date = formatter.parse(dob);
        LocalDateTime now = LocalDateTime.now();
        Date today = formatter.parse(now.toString());
        if(firstName.length() < 1 || firstName.length() > 100){
            System.out.println("First name must be 1 > and < 100");
        }
        if(lastName.length() < 1 || lastName.length() > 100){
            System.out.println("Last name must be 1 > and < 100");
        }
        if(date.after(today)){
            System.out.println("Lier");
        }
        Person p = new Person();
        p.setFirstName(firstName);
        p.setLastName(lastName);
        p.setDob(dob);
        personRepository.save(p);
        return "Saved";
    }


    @GetMapping(path="/people")
    public @ResponseBody
    String getAllPeople(@RequestParam String sessionToken) {
        // This returns a JSON or XML with the users
        //return personRepository.findAll();
        JSONObject response = new JSONObject();
        try {
            String localToken = sessionRepository.findByToken(sessionToken).getToken();
        if(localToken.matches(sessionToken)) {
            JSONObject obj = new JSONObject();
            obj.put("people", personRepository.findAll());
            return obj.toString();
        }
        else{
            response.put("code", 401);
            response.put("message", "Login Required");
            System.out.println(response.toString());
            return response.toString();
        }
        }
        catch (NullPointerException e) {
            System.out.print("NullPointerException caught");
            response.put("code", 401);
            response.put("message", "Token not found");
            return response.toString();
        }
    }
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepositoryL;
    @Autowired
    private SessionRepository sessionRepository;
    @Commit
    @PostMapping(path="/login")
    public @ResponseBody
    String getUser(@RequestParam String login, @RequestParam String password) {
        User userobj = userRepositoryL.findByLogin(login);
        JSONObject obj  = userRepositoryL.findByLogin(login).toJSON();
        String inPassword = obj.get("password").toString();
        password = DocRobsHash.getCryptoHash(password, "SHA-256");

        if(inPassword.matches(password)){
            String inLogin = obj.get("id").toString();

            LocalDateTime time = LocalDateTime.now();
            String createdToken = login.concat(time.toString());
            createdToken = DocRobsHash.getCryptoHash(createdToken, "SHA-256");
            session.setToken(createdToken);
            session.setUser(userobj);
            sessionRepository.save(session);
            JSONObject authorized = new JSONObject();
            authorized.put("code", 200);
            authorized.put("token", createdToken);
            System.out.println(authorized.toString());
            return authorized.toString();
        }
        else {
            JSONObject unauthorized = new JSONObject();
            unauthorized.put("code", 401);
            unauthorized.put("message", "Login Required");
            System.out.println(unauthorized.toString());
            return unauthorized.toString();
        }
    }
}