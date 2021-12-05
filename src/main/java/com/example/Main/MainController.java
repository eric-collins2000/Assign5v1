package com.example.Main;



import com.example.Main.DocRobsStuff.DocRobsHash;
import com.example.Main.Models.Person;
import com.example.Main.Models.Session;
import com.example.Main.Models.User;
import com.example.Main.Repo.PersonRepository;
import com.example.Main.Repo.SessionRepository;
import com.example.Main.Repo.UserRepository;
import org.hibernate.mapping.Map;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.annotation.Commit;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller // This means that this class is a Controller
public class MainController {
    static String userID = null;
    Session session = new Session();
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Commit
    @PostMapping(path = "/people") // Map ONLY POST Requests
    public @ResponseBody String insertPerson(@RequestHeader(name = "X-Authorization") String sessionToken, @RequestParam String firstName
            , @RequestParam String lastName, @RequestParam String dob) throws ParseException {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        JSONObject response = new JSONObject();
        try {
            String localToken = sessionRepository.findByToken(sessionToken).getToken();
            if (localToken.matches(sessionToken)) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-dd-MM");
                Date date = formatter.parse(dob);
                LocalDateTime now = LocalDateTime.now();
                Date today = formatter.parse(now.toString());
                if (firstName.length() < 1 || firstName.length() > 100 || lastName.length() < 1 || lastName.length() > 100 || date.after(today)) {
                    response.put("error", "First and Last name must be 1 > and < 100" +
                            "and DOB must be later then today");
                } else {
                    Person p = new Person();
                    p.setFirstName(firstName);
                    p.setLastName(lastName);
                    p.setDob(dob);
                    personRepository.save(p);
                    response.put("code", 200);
                }
            } else {
                response.put("code", 401);
            }
        } catch (NullPointerException e) {
            System.out.print("NullPointerException caught");
            response.put("code", 401);
        }
        return response.toString();
    }

    @RequestMapping(value="/people/{id}", method = RequestMethod.PUT) // Map ONLY POST Requests
    public @ResponseBody String updatePerson(@RequestHeader(name = "X-Authorization") String sessionToken,
        @PathVariable String id, @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName, @RequestParam(required = false) String dob) throws ParseException {
        JSONObject response = new JSONObject();
        int checkId = Integer.parseInt(id);
        try {
            String localToken = sessionRepository.findByToken(sessionToken).getToken();
            if (localToken.matches(sessionToken)) {
                if (personRepository.existsById(checkId)) {
                        Person p = personRepository.findByIdEquals(checkId);
                        if(firstName != null){
                            if(firstName.length() < 1 || firstName.length() > 100) {
                                p.setFirstName(firstName);
                                System.out.print("first name set");
                            }
                        }
                        if(lastName != null ){
                            if(lastName.length() < 1 || lastName.length() > 100) {
                                p.setLastName(lastName);
                                System.out.print("last name set");
                            }
                        }
                        if(dob != null){
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");
                            //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-dd-MM");
                            Date date = formatter.parse(dob);
                            LocalDateTime now = LocalDateTime.now();
                            Date today = formatter.parse(now.toString());
                            if(date.after(today)) {
                                p.setDob(dob);
                                System.out.print("dob name set");
                            }
                        }
                        personRepository.save(p);
                        response.put("code", 200);
                } else {
                    response.put("code", 401);
                }
            } else {
                //Could not find ID
                System.out.println("Id not found");
            }
        }
                catch (NullPointerException e) {
                    System.out.println("Null Token was: " + sessionToken);
                    System.out.print("NullPointerException caught on updatePerson \n");
                    response.put("code", 401);

            }
        return response.toString();
    }

    @RequestMapping(value="/people/{id}", method = RequestMethod.DELETE) // Map ONLY POST Requests
    public @ResponseBody String deletePerson(@RequestHeader(name = "X-Authorization") String sessionToken,
    @PathVariable String id) {
        JSONObject response = new JSONObject();
        System.out.println("Attempting delete");
        int checkId = Integer.parseInt(id);
        try {
            String localToken = sessionRepository.findByToken(sessionToken).getToken();
            if (localToken.matches(sessionToken)) {
                Person p = personRepository.findByIdEquals(checkId);
                personRepository.delete(p);
                response.put("code", 200);
                    }
            else {
                //Could not find ID
                response.put("code", 404);
            }
        }
        catch (NullPointerException e) {
            System.out.println("Null Token was: " + sessionToken);
            System.out.print("NullPointerException caught on updatePerson \n");
            response.put("code", 401);

        }
        return response.toString();
    }

    @RequestMapping(value="/people/{id}", method = RequestMethod.GET) // Map ONLY POST Requests
    public @ResponseBody String fetchPerson(@RequestHeader(name = "X-Authorization") String sessionToken,
    @PathVariable String id) {
        JSONObject response = new JSONObject();
        int checkId = Integer.parseInt(id);
        try {
            String localToken = sessionRepository.findByToken(sessionToken).getToken();
            if (localToken.matches(sessionToken)) {
                Person p = personRepository.findByIdEquals(checkId);
                response.put("body", p.toJSON()); // FIX THIS
                response.put("code", 200);
            }
            else {
                //Could not find ID
                response.put("code", 404);
            }
        }
        catch (NullPointerException e) {
            System.out.println("Null Token was: " + sessionToken);
            System.out.print("NullPointerException caught on updatePerson \n");
            response.put("code", 401);

        }
        return response.toString();
    }

    @GetMapping(path="/people")
    public @ResponseBody
    String fetchPeople (@RequestHeader(name = "X-Authorization") String sessionToken)  {
        // This returns a JSON or XML with the users
        JSONObject response = new JSONObject();
        try {
            String localToken = sessionRepository.findByToken(sessionToken).getToken();
            System.out.println("Fetch token is: " + localToken);
        if(localToken.matches(sessionToken)) {
            response.put("people", personRepository.findAll());
            response.put("code", 200);
        }
        else{
            response.put("code", 401);
        }
            return response.toString();
        }
        catch (NullPointerException e) {
            System.out.print("NullPointerException caught fetch");
            response.put("code", 401);
            return response.toString();
        }
    }

    @Commit
    @PostMapping(path="/login")
    public @ResponseBody
    String getUser(@RequestParam String login, @RequestParam String password) {
        JSONObject response = new JSONObject();
        try {
            User userobj = userRepository.findByLogin(login);
            JSONObject obj = userRepository.findByLogin(login).toJSON();
            String inPassword = obj.get("password").toString();
            password = DocRobsHash.getCryptoHash(password, "SHA-256");
            if (inPassword.matches(password)) {
                LocalDateTime time = LocalDateTime.now();
                String createdToken = login.concat(time.toString());
                createdToken = DocRobsHash.getCryptoHash(createdToken, "SHA-256");
                session.setToken(createdToken);
                session.setUser(userobj);
                sessionRepository.save(session);
                response.put("code", 200);
                response.put("X-Authorization", createdToken);
                return response.toString();
            } else {
                response.put("code", 401);
            }
        } catch (NullPointerException e) {
            response.put("code", 401);
            return response.toString();
        }
        return response.toString();
    }
}