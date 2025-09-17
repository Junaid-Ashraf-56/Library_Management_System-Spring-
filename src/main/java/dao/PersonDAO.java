package dao;

import model.Person;

import java.util.List;

public interface PersonDAO {
    void addPerson(Person person);
    Person getPersonByLibId(int id);
    List<Person> getAllPerson();
    void updatePerson(Person person);
    void deletePerson(int id);
    Person getPersonByEmail(String email);
}
