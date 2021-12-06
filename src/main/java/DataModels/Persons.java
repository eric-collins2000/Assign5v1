package DataModels;



import java.util.ArrayList;




public class Persons {

    private String firstName;
    public ArrayList<Person> Persons;
    
    public Persons() {
        Persons = new ArrayList<Person>();
    }
    // Leaving this in, it will be important later I believe.
    public void addPersons(Person person) {
        Persons.add(person);
        System.out.println("Added person -> " + person.getFirstName() + " " + person.getId() + " <-Good");
    }

    public String printPersons(Persons persons) {
        System.out.println("Starting print");
        String output = " ";
        for(Person personiter : Persons)
        {
            if(personiter != null) {
                output+= "\n" + "-" + personiter;
            }
        }
        return output;
    }

}