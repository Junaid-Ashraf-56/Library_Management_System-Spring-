package service;

import dao.PersonDAO;
import model.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonDAO personDAO;

    public PersonService(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public void addPerson(Person person){
        if (person.getName() == null || person.getName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (person.getEmail() == null || person.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        Person existing = personDAO.getPersonByEmail(person.getEmail());
        if (existing!=null){
            System.out.println("Email already register");
        }

        int uniqueLibId = generateUniqueLibraryId();
        person.setLibraryId(uniqueLibId);

        personDAO.addPerson(person);
    }

    public Person getPersonByLibId(int id){
        return personDAO.getPersonByLibId(id);
    }

    public List<Person> getAllPerson(){
        return personDAO.getAllPerson();
    }

    public void updatePerson(Person person){
        personDAO.updatePerson(person);
    }

    public void deletePerson(int id){
        personDAO.deletePerson(id);
    }

    private int generateUniqueLibraryId() {
        return (int)(System.currentTimeMillis() % 100000);
    }
    public int getLibId(String email){
        return personDAO.getPersonLibByEmail(email);
    }
}
