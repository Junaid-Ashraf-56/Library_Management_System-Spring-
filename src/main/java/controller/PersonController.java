package controller;

import model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public String addPerson(@RequestBody Person person){
        personService.addPerson(person);
        return "Person added successfully";
    }

    @PostMapping
    public List<Person> getAllPerson(){
        return personService.getAllPerson();
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable int libId){
        return personService.getPersonByLibId(libId);
    }

    @PutMapping("/{id}")
    public String updatePerson(@PathVariable int id, @RequestBody Person person) {
        person.setLibraryId(id);
        personService.updatePerson(person);
        return "Person updated successfully!";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable int id) {
        personService.deletePerson(id);
        return "Person deleted successfully!";
    }
    @GetMapping("/libId")
    public int getLibId(String email){
        return personService.getLibId(email);
    }
}
