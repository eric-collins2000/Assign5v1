package DataModels;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import java.time.LocalDate;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private int age;

/*    public Person(int id, String firstName, String lastName) {
    }*/

    public Person(int id, String firstName, String lastName, LocalDate dateOfBirth, int age) {
        //Person person = new Person();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        //return person;
    }

    public static Person fromJSONObject(JSONObject json) {
        logger.info("\nWorking on JSON (Person)\n");
        try {
            logger.info("INSIDE TRY CATCH");
            String birthDay = json.getString("dateOfBirth");
            LocalDate birthDaytoDate = LocalDate.parse(birthDay);
            Person person = new Person(json.getInt("id"), json.getString("firstName"), json.getString("lastName"), birthDaytoDate, json.getInt("age"));
            logger.info(person.firstName + " " + person.lastName);
            return person;
        } catch(Exception e) {
            throw new IllegalArgumentException("Unable to parse person from provided json: " + json.toString());
        }
    }

    //We do this elsewhere
    public String toString() {
        return getFirstName();
    }
    // accessors
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLastName(){ return lastName;}

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    } //Keep an eye on this one
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public static String getDateOfBirthString(LocalDate dateOfBirth) {
        String BirthDayString = dateOfBirth.toString();
        return BirthDayString;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    private static final Logger logger = LogManager.getLogger();
}
