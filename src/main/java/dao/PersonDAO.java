package dao;

import model.Person;

import java.util.List;

public interface PersonDAO {
    void addPerson(Person person);
    void getPersonByLibId(int id);
    List<Person> getAllPerson();
    void updatePerson();
    void deletePerson(Person person);

}
