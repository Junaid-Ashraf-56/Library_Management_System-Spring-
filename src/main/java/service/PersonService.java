package service;

import dao.PersonDAO;
import model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonDAO personDAO;

    @Autowired
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
        int raw = (int)(System.currentTimeMillis() % 1000000);
        return raw < 100000 ? raw + 100000 : raw;
    }

    public Person getLibId(String email){
        return personDAO.getPersonLibByEmail(email);
    }
}
